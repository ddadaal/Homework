package viccrubs.appautotesting.crawlers;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.val;
import lombok.var;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import viccrubs.appautotesting.config.Config;
import viccrubs.appautotesting.models.*;

import java.util.HashMap;
import java.util.HashSet;

public class DFSCrawler extends Crawler {

    private int id = 0;

    private @Getter
    UTG utg;

    private UTGNode currentNode;

    private final String appPackage;

    public void doReport() {
        report("UI detected %d, completed %d, running time in sec: %d",
            utg.getNodes().size(),
            utg.getNodes().stream().filter(UTGNode::completed).count(),
            getRunningTime() / 1000
        );
    }

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
        currentNode.setOutEdge(newNode, action);
        currentNode = newNode;
    }

    private UiElement getNextUnaccessedElement() {
        val accessed = accessedElementMap.computeIfAbsent(currentNode.getUi().getActivityName(), k -> new HashSet<>());
        for (val element : currentNode.getUi().getLeafElements()) {
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
            nowNode.setId(++id);
            verbose("On new node %s", nowNode);
            utg.addNode(nowNode);
            return nowNode;
        }
    }

    private boolean isOutOfApp(UTGNode node) {
        return !node.getUi().getCurrentPackage().equals(appPackage);
    }


    @Override
    public void doCrawl() {

        main:
        while (true) {

            // 如果意外退出了应用，就依次处理
            if (isOutOfApp(currentNode)) {
                val currentPackage = currentNode.getUi().getCurrentPackage();
                val currentActivity = currentNode.getUi().getActivityName();

                verbose("On package %s activity %s.", currentPackage, currentActivity);

                // 看是不是分享界面
                if (currentPackage.equals("android") && currentActivity.equals("com.android.internal.app.ChooserActivity")) {
                    verbose("On share panel. Back.");

                    // 是分享界面，设置Node的类型，点击返回，设置新的当前Node
                    currentNode.setType(UTGNode.Type.SHARE);
                    UiAction.BACK.perform(driver, currentNode.getUi());
                    currentNode = getCurrentNode();
                    continue;
                }

                // 看能不能返回回来
                verbose("Try back to app.");
                UiAction.BACK.perform(driver, currentNode.getUi());
                val nowNode = getCurrentNode();
                if (!isOutOfApp(nowNode)) {
                    verbose("Back to app. Continue");
                    // 设置Node类型
                    currentNode.setType(UTGNode.Type.EXTERNAL_APP);
                    currentNode = nowNode;
                    continue;
                }

                // 都不能返回回来，那应该是挂了，重启试试
                verbose("Can not back to app. App might have crashed. Relaunch it and set back to the start node.");
                currentNode.setType(UTGNode.Type.CRASH);
                driver.closeApp();
                driver.launchApp();

                // 等待启动
                long startTime = System.currentTimeMillis();
                new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) d ->
//                    driver.currentActivity().equals(utg.getStartNode().getUi().getActivityName())
                    getCurrentNode().equals(utg.getStartNode())
                );

                currentNode = getCurrentNode();
                verbose("App relaunched. Took %d ms", System.currentTimeMillis() - startTime);
            }

            // 处理登录界面
            val activityName = currentNode.getUi().getActivityName();
            if (Config.LOGIN_INFO_MAP.keySet().contains(activityName)) {
                verbose("Login activity '%s' detected. Inputting credentials.", activityName);
                val loginInfo = Config.LOGIN_INFO_MAP.get(activityName);
                // 查找各个元素并输入内容
                currentNode.getUi().findConfigElement(loginInfo.getUsernameField())
                    .ifPresent(usernameField -> {
                        new UiAction(UiAction.Type.INPUT, loginInfo.getUsername(), usernameField).perform(driver, currentNode.getUi());
                    });

                currentNode.getUi().findConfigElement(loginInfo.getPasswordField())
                    .ifPresent(passwordField -> {
                        new UiAction(UiAction.Type.INPUT, loginInfo.getPassword(), passwordField).perform(driver, currentNode.getUi());
                    });

                currentNode.getUi().findConfigElement(loginInfo.getLoginBtn())
                    .ifPresent(loginBtn -> {
                        new UiAction(UiAction.Type.CLICK, null, loginBtn).perform(driver, currentNode.getUi());
                    });

                // 等待登录成功，activity改变即认为登录完成
                new WebDriverWait(driver, 10).until((ExpectedCondition<Boolean>) d ->
                    !driver.currentActivity().equals(utg.getStartNode().getUi().getActivityName())
                );

                // 修改当前node的UI为现在的UI
                currentNode.setUi(Ui.create(driver));
            }

            UiElement element;

            while ((element = getNextUnaccessedElement()) != null) {
                setAccessed(element);
                val action = getAction(element);

                action.perform(driver, currentNode.getUi());

                UTGNode newNode = getCurrentNode();

                // 如果发现UI变了，就加一条新的边到图中，表明在之前的界面发生什么事件能够触发UI转移
                // 并进行进入新界面继续搜索
                if (!currentNode.equals(newNode)) {
                    verbose("New transition added: %s to %s by %s", currentNode, newNode, action);
                    handleNodeChange(newNode, action);
                    continue main;
                }

            }

            // 一个UI已经遍历结束了，尝试去其他没有遍历过的界面继续

            // 先找一步就能到的
            verbose("On completed UI %s. Find direct jump to another UI.", currentNode);

            for (val edge : currentNode.getOutEdges().entrySet()) {
                if (!edge.getValue().completed()) {
                    verbose("To uncompleted UI %s with action %s", edge.getValue().getUi(), edge.getKey());
                    edge.getKey().perform(driver, currentNode.getUi());

                    // 路径已经改变了，修改路径
                    val nowNode = getCurrentNode();
                    if (!nowNode.equals(edge.getValue())) {
                        verbose("Edge changed, now to UI %s", currentNode);
                        currentNode.setOutEdge(nowNode, edge.getKey());
                    }
                    currentNode = nowNode;
                    continue main;
                }
            }

            // 这个界面能通过一步到的界面都结束了。
            // 尝试找一下从另一个界面能跳到未完成的界面
            verbose("No direct jump to uncompleted UI. Try indirect jump to uncompleted UI.");

            for (val node : utg.getNodes()) {
                if (!node.completed()) {
                    // 有一个未完成的界面，找一条路径过去
//                    verbose("Finding a way to UI %s", node);

                    try {

                        val path = utg.findPath(currentNode, node);

                        if (path.isEmpty()) {
//                            verbose("Can not find such a way. Retry another UI.");
                            // 找不到过去的路，找其他没完成的UI。继续找
                            continue;
                        }

                        verbose("Found a way to uncompleted node %s. Going to it.", node);

                        var nowNode = currentNode;
                        // 依次进行每一步
                        for (val edge : path) {
                            edge.getAction().perform(driver, nowNode.getUi());
                            nowNode = getCurrentNode();
                            if (!nowNode.equals(edge.getEndNode())) {
                                // 路径已经改变了，修改记录值，并不要继续操作了，重新开始
                                edge.getStartNode().setOutEdge(nowNode, edge.getAction());
                                currentNode = nowNode;
                                continue main;
                            }
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }

                    // 继续
                    currentNode = getCurrentNode();
                    continue main;
                }
            }

            // 现在这个节点能到的所有界面都已经完成了，尝试用其他方法去其他界面
            // TODO 使用滑动等尝试继续遍历
            verbose("Can not go to uncompleted node. Try state break");

            // 尝试右滑
//            verbose("Try right swipe");
//            driver.

            // 返回
            if (!currentNode.getOutEdges().containsKey(UiAction.BACK)) {
                // 如果没试过返回，就试试返回
                verbose("Try back。");
                UiAction.BACK.perform(driver, currentNode.getUi());
                var newNode = getCurrentNode();
                if (newNode.equals(currentNode)) {
                    verbose("Back not working.");
                } else {
                    verbose("Back to UI %s", newNode.getUi());
                    handleNodeChange(newNode, UiAction.BACK);
                    continue;
                }
            }


            // 两次返回
            if (!currentNode.getOutEdges().containsKey(UiAction.DOUBLE_BACK)) {
                // 刚点了一次，先休息一会再点两次
                sleep(1000);
                verbose("Try double back.");
                UiAction.DOUBLE_BACK.perform(driver, currentNode.getUi());
                var newNode = getCurrentNode();
                if (newNode.equals(currentNode)) {
                    verbose("Double back not working.");
                } else {
                    verbose("Double back to UI %s", newNode.getUi());
                    handleNodeChange(newNode, UiAction.DOUBLE_BACK);
                    continue;
                }
            }

            verbose("State break failed. Exiting program");
            break;
        }

    }

}
