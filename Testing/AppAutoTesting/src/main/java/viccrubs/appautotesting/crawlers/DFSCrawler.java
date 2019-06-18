package viccrubs.appautotesting.crawlers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.Getter;
import lombok.val;
import viccrubs.appautotesting.models.UTG;
import viccrubs.appautotesting.models.UTGNode;
import viccrubs.appautotesting.models.UiElement;
import viccrubs.appautotesting.models.UiEvent;
import viccrubs.appautotesting.utils.AppiumUtils;

public class DFSCrawler extends Crawler {

    private @Getter
    UTG utg;

    public DFSCrawler(AndroidDriver<AndroidElement> driver) {
        super(driver);
        utg = new UTG(UTGNode.create(driver));
        verbose("UTG initialized. ");
    }

    private void dfs(UTGNode node) {
        // 如果已经遍历过这个界面，就拿到这个原来的界面继续；否则就用新的
        UTGNode currentNode = node;
        if (utg.hasNode(currentNode)) {
            verbose("Back to UI %s.", node.getUi());
            currentNode = utg.getNode(currentNode);
        } else {
            verbose("First time to UI %s.", node.getUi());
            utg.addNode(currentNode);
        }

        UiElement element;

        while ((element = currentNode.getUi().getNextUnaccessedElement()) != null) {
            try {
                element.setAccessed(true);

                val event = new UiEvent(UiEvent.Type.CLICK, null, element);

                event.perform(driver);

                UTGNode newNode = UTGNode.create(driver);

                // 如果发现UI变了，就加一条新的边到图中，表明在之前的界面发生什么事件能够触发UI转移
                // 并进行进入新界面继续DFS
                if (!currentNode.equals(newNode)) {
                    verbose("New edge added: %s to %s by %s", currentNode, newNode, event);
                    currentNode.addOutEdge(newNode, event);
                    dfs(newNode);
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
                edge.getEvent().perform(driver);
                dfs(edge.getEnd());
                break;
            }
        }

        // 这个界面能到的界面都结束了。

        // TODO 使用滑动等尝试继续遍历

        verbose("All UI completed. Exiting program");

    }

    @Override
    public void run() {
        dfs(utg.getStartNode());
    }
}
