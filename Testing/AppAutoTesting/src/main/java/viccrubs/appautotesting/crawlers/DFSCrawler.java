package viccrubs.appautotesting.crawlers;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.val;
import lombok.var;
import org.openqa.selenium.support.ui.WebDriverWait;
import viccrubs.appautotesting.config.Config;
import viccrubs.appautotesting.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DFSCrawler extends Crawler {

    private int id = 0;

    private @Getter UTG utg;

    private UTGNode currentNode;

    private final String appPackage;


    // 上次的操作


    public DFSCrawler(AppiumDriver driver, String appPackage) {
        super(driver);
        utg = new UTG(UTGNode.create(driver));
        verbose("UTG initialized. ");
        currentNode = utg.getStartNode();

        this.appPackage = appPackage;
    }

    private HashMap<String, HashSet<UiElement>> accessedElementMap = new HashMap<>();

    private void handleNodeChange(UTGNode newNode, UiAction action) {
        currentNode.addOutEdge(newNode, action);
        currentNode = newNode;
    }

    private UiElement getNextUnaccessedElement() {
        val accessed = accessedElementMap.computeIfAbsent(currentNode.getUi().getActivityName(), k -> new HashSet<>());
        for (val element: currentNode.getUi().getLeafElements()) {
            if (!element.isAccessed()) {
                if (accessed.contains(element)) {
                    element.setAccessed(true);
                } else {
                    return element;
                }
            }
        }
        return null;
    }

    private void setAccessed(UiElement element) {
        val accessed = accessedElementMap.computeIfAbsent(currentNode.getUi().getActivityName(), k -> new HashSet<>());

        accessed.add(element);
        element.setAccessed(true);
    }

    private UiAction getAction(UiElement element) {
        if (element.getInputable()) {
            return new UiAction(UiAction.Type.INPUT, Config.INPUTS.get(0), element);
        }

        return new UiAction(UiAction.Type.CLICK, null, element);
    }

    private UTGNode getCurrentNode() {
        // 获得现在的界面
        val nowNode = UTGNode.create(driver);

        if (utg.hasNode(nowNode)) {
            // 之前到过这个界面，返回之前的界面node
            val oldNode = utg.getNode(nowNode);
            verbose("On old node %s", oldNode);
            return oldNode;
        } else {
            // 第一次到这个界面
            nowNode.setId(id++);
            verbose("On new node %s", nowNode);
            utg.addNode(nowNode);
            return nowNode;
        }
    }


    @Override
    public void run() {
        main: while (true) {

            // 如果意外退出了应用，就依次处理
            // TODO 处理分享请求
            if (!currentNode.getUi().getCurrentPackage().equals(appPackage)) {
                val currentPackage = currentNode.getUi().getCurrentPackage();
                val currentActivity = currentNode.getUi().getActivityName();

                verbose("Unexpectedly go to package %s activity %s.", currentPackage, currentActivity);

                // 看是不是分享界面
                if (currentPackage.equals("android") && currentActivity.equals("com.android.internal.app.ChooserActivity")) {
                    verbose("On share panel. Back.");

                    // 是分享界面，设置Node的类型，点击返回，设置新的当前Node
                    currentNode.setType(UTGNode.Type.SHARE);
                    UiAction.BACK.perform(driver);
                    currentNode = getCurrentNode();
                    continue;
                }

                // 看是不是crash了
                if (currentActivity.equals(".Launcher")) {
                    verbose("On launcher. App might have crashed. Relaunch it and set back to the start node.");
                    currentNode.setType(UTGNode.Type.CRASH);
                    driver.closeApp();
                    driver.launchApp();
                    currentNode = utg.getStartNode();

                    // 等待启动
//                    new WebDriverWait(driver, 5000).until((AppiumDriver driver) -> driver.currentActivity().equals(utg.getStartNode().getUi().getActivityName()));
                    sleep(3000);
                    continue;
                }


                break;
            }

            // 处理登录界面
            val activityName = currentNode.getUi().getActivityName();
            if (Config.LOGIN_INFO_MAP.keySet().contains(activityName)) {
                verbose("Login activity '%s' detected. Inputting credentials.", activityName);
                val loginInfo = Config.LOGIN_INFO_MAP.get(activityName);
                // 查找各个元素并输入内容
                currentNode.getUi().findConfigElement(loginInfo.getUsernameField())
                    .ifPresent(usernameField -> {
                        new UiAction(UiAction.Type.INPUT, loginInfo.getUsername(), usernameField).perform(driver);
                    });

                currentNode.getUi().findConfigElement(loginInfo.getPasswordField())
                    .ifPresent(passwordField -> {
                        new UiAction(UiAction.Type.INPUT, loginInfo.getPassword(), passwordField).perform(driver);
                    });

                currentNode.getUi().findConfigElement(loginInfo.getLoginBtn())
                    .ifPresent(loginBtn -> {
                        new UiAction(UiAction.Type.CLICK, null, loginBtn).perform(driver);
                    });

                // 等待登录成功
                sleep(3000);

                // 修改当前node的UI为现在的UI
                currentNode.setUi(Ui.create(driver));
            }

            UiElement element;

            while ((element = getNextUnaccessedElement()) != null) {
                setAccessed(element);
                try {

                    val action = getAction(element);

                    action.perform(driver);

                    UTGNode newNode = getCurrentNode();

                    // 如果发现UI变了，就加一条新的边到图中，表明在之前的界面发生什么事件能够触发UI转移
                    // 并进行进入新界面继续搜索
                    if (!currentNode.equals(newNode)) {
                        verbose("New edge added: %s to %s by %s", currentNode, newNode, action);
                        handleNodeChange(newNode, action);
                        continue main;
                    }

                } catch (Exception e) {
                    error("Error occurred.\n%s", e.toString());
                }
            }

            // 一个UI已经遍历结束了，尝试去其他没有遍历过的界面继续

            // 先找一步就能到的
            verbose("Ui %s completed. Find direct jump to another UI.", currentNode);

            for (val edge: currentNode.getOutEdges().entrySet()) {
                if (!edge.getValue().completed()) {
                    verbose("To uncompleted UI %s with action %s", edge.getValue().getUi(), edge.getKey());
                    edge.getKey().perform(driver);

                    val nowNode = getCurrentNode();
                    if (!nowNode.equals(edge.getValue())) {
                        verbose("Edge changed, now to UI %s", currentNode);
                        currentNode.addOutEdge(nowNode, edge.getKey());
                    }
                    currentNode = nowNode;
                    continue main;
                }
            }

            // 这个界面能通过一步到的界面都结束了。
            // 尝试找一下从另一个界面能跳到未完成的界面
            verbose("No direct jump to uncompleted UI possible. Try to indirect jump to uncompleted UI.");

            for (val node: utg.getNodes()) {
                if (!node.completed()) {
                    // 有一个未完成的界面，找一条路径过去
                    verbose("Found an uncompleted node %s. Going to it.", node);
                    val path = utg.findPath(currentNode, node);

                    if (path.isEmpty()) {
                        // 找不到过去的路，说明还有其他UI。继续找
                        continue;
                    }

                    // TODO 可以只进行第一步
                    // 依次进行每一步
                    for (val edge: path) {
                        edge.getAction().perform(driver);
                    }

                    // 继续
                    currentNode = getCurrentNode();
                    continue main;
                }
            }

            // 现在这个节点能到的所有界面都已经完成了，尝试用其他方法去其他界面
            // TODO 使用滑动等尝试继续遍历
            verbose("Can not to uncompleted node. Try state break");

            // 尝试右滑
//            verbose("Try right swipe");
//            driver.

            // 返回
            verbose("Try back。");
            UiAction.BACK.perform(driver);
            var newNode = UTGNode.create(driver);
            if (newNode.equals(currentNode)) {
                // 再次返回
                verbose("Back not working. Try back again.");
                UiAction.BACK.perform(driver);
                newNode = UTGNode.create(driver);
                if (newNode.equals(currentNode)) {
                    // 再次返回无效，算了算了
                    verbose("Double back not working.");
                } else {
                    verbose("Double back to UI %s", newNode.getUi());
                    handleNodeChange(newNode, UiAction.DOUBLE_BACK);
                    continue;
                }
            } else {
                verbose("Backed to UI %s", newNode.getUi());
                handleNodeChange(newNode, UiAction.BACK);
                continue;
            }


            verbose("State break failed. Exiting program");
            break;
        }
    }


}
