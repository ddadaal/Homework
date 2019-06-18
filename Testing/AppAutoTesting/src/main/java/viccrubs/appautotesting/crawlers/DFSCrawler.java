package viccrubs.appautotesting.crawlers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.Getter;
import lombok.val;
import lombok.var;
import viccrubs.appautotesting.models.UTG;
import viccrubs.appautotesting.models.UTGNode;
import viccrubs.appautotesting.models.UiElement;
import viccrubs.appautotesting.models.UiAction;

public class DFSCrawler extends Crawler {

    private @Getter
    UTG utg;

    public DFSCrawler(AndroidDriver<AndroidElement> driver) {
        super(driver);
        utg = new UTG(UTGNode.create(driver));
        verbose("UTG initialized. ");
    }

    @Override
    public void run() {
        UTGNode currentNode = utg.getStartNode();

        main: while (true) {
            // 如果已经遍历过这个界面，就拿到这个原来的界面继续；否则就用新的
            if (utg.hasNode(currentNode)) {
                currentNode = utg.getNode(currentNode);
                verbose("Back to UI %s.", currentNode.getUi());
            } else {
                verbose("First time to UI %s.", currentNode.getUi());
                utg.addNode(currentNode);
            }

            UiElement element;

            while ((element = currentNode.getUi().getNextUnaccessedElement()) != null) {
                try {
                    element.setAccessed(true);

                    val event = new UiAction(UiAction.Type.CLICK, null, element);

                    event.perform(driver);

                    UTGNode newNode = UTGNode.create(driver);

                    // 如果发现UI变了，就加一条新的边到图中，表明在之前的界面发生什么事件能够触发UI转移
                    // 并进行进入新界面继续DFS
                    if (!currentNode.equals(newNode)) {
                        verbose("New edge added: %s to %s by %s", currentNode, newNode, event);
                        currentNode.addOutEdge(newNode, event);
                        currentNode = newNode;
                        continue main;
                    }

                } catch (Exception e) {
                    error("Error occurred.\n%s", e.toString());
                }
            }

            // 一个UI已经遍历结束了，尝试去其他没有遍历过的界面继续
            verbose("Ui %s completed", currentNode);

            for (val edge: currentNode.getOutEdges()) {
                if (!edge.getEnd().getUi().completed()) {
                    verbose("To uncompleted UI %s with event %s", edge.getEnd().getUi(), edge.getEnd());
                    edge.getAction().perform(driver);

                    currentNode = edge.getEnd();
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
                    currentNode = newNode;
                    continue;
                }
            } else {
                verbose("Backed to UI %s", newNode.getUi());
                currentNode = newNode;
                continue;
            }


            verbose("State break failed. Exiting program");
            break;
        }
    }
}
