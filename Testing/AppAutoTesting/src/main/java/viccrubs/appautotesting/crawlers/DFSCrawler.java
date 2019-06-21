package viccrubs.appautotesting.crawlers;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.val;
import lombok.var;
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
            verbose("Currently on previous node %s", oldNode);
            return oldNode;
        } else {
            // 第一次到这个界面
            nowNode.setId(id++);
            verbose("Currently on new node %s", nowNode);
            utg.addNode(nowNode);
            return nowNode;
        }
    }


    @Override
    public void run() {
        main: while (true) {

            // 如果意外退出了应用，就结束程序
            // TODO 处理分享请求
            if (!currentNode.getUi().getCurrentPackage().equals(appPackage)) {
                verbose("Unexpectedly go to activity %s. Exiting program.", currentNode.getUi().getActivityName());
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
            verbose("Ui %s completed.", currentNode);

            for (val edge: currentNode.getOutEdges().entrySet()) {
                if (!edge.getValue().getUi().completed()) {
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

            // 这个界面能到的界面都结束了。
            verbose("No pre-found jump-out to uncompleted UI possible. Try state breaking.");

            // TODO 使用滑动等尝试继续遍历

            // 尝试右滑
//            verbose("Try right swipe");
//            driver.

            // 返回
            verbose("Try back");
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
