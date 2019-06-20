package viccrubs.appautotesting.crawlers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.Getter;
import lombok.val;
import lombok.var;
import viccrubs.appautotesting.config.Config;
import viccrubs.appautotesting.models.UTG;
import viccrubs.appautotesting.models.UTGNode;
import viccrubs.appautotesting.models.UiElement;
import viccrubs.appautotesting.models.UiAction;

import java.util.HashMap;
import java.util.HashSet;

public class DFSCrawler extends Crawler {

    private @Getter
    UTG utg;

    private UTGNode currentNode;

    private final String appPackage;

    public DFSCrawler(AndroidDriver<AndroidElement> driver, String appPackage) {
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



    @Override
    public void run() {
        main: while (true) {

            // 如果意外退出了应用，就结束程序
            if (!currentNode.getUi().getCurrentPackage().equals(appPackage)) {
                verbose("Unexpectedly go to package %s. Exiting program.", currentNode.getUi().getCurrentPackage());
                break;
            }

            // 如果已经遍历过这个界面，就拿到这个原来的界面继续；否则就用新的
            if (utg.hasNode(currentNode)) {
                currentNode = utg.getNode(currentNode);
                verbose("Back to UI %s. Access rate: %d/%d", currentNode.getUi(),
                    currentNode.getUi().getLeafElements().stream().filter(UiElement::isAccessed).count(),
                    currentNode.getUi().getLeafElements().size()
                    );
            } else {
                verbose("First time to UI %s.", currentNode.getUi());
                utg.addNode(currentNode);
            }

            UiElement element;

            while ((element = getNextUnaccessedElement()) != null) {
                setAccessed(element);
                try {

                    val action = getAction(element);

                    action.perform(driver);

                    UTGNode newNode = UTGNode.create(driver);

                    // 如果发现UI变了，就加一条新的边到图中，表明在之前的界面发生什么事件能够触发UI转移
                    // 并进行进入新界面继续DFS
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
                    currentNode = UTGNode.create(driver);
                    if (!currentNode.equals(edge.getValue())) {
                        verbose("Edge changed, now to UI %s", currentNode);
                        currentNode.addOutEdge(currentNode, edge.getKey());
                    }
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
