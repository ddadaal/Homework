package viccrubs.appautotesting.crawlers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import viccrubs.appautotesting.log.Logger;
import viccrubs.appautotesting.models.UTG;
import viccrubs.appautotesting.models.UTGNode;
import viccrubs.appautotesting.utils.AppiumUtils;

public class DFSCrawler extends Crawler {

    private @Getter UTG utg;

    public DFSCrawler(AndroidDriver<AndroidElement> driver) {
        super(driver);
        utg = new UTG(UTGNode.create(driver));
    }

    void dfs(UTG utg) {
        val currentActivity = driver.currentActivity();
        UTGNode node = UTGNode.create(driver);
        if (utg.addNode(node)) {
            // 第一次访问这个activity
            verbose("New UTGNode added. activity: %s; hashCode: %s ", node.getUi().getActivityName(), node.hashCode());
            // 找所有叶元素

            for (val leaf : node.getUi().getLeafElements()) {
                try {
                    verbose("Click %s in activity %s.", leaf.getXPath(), leaf.getUi().getActivityName());
                    driver.findElementByXPath(leaf.getXPath()).click();
                    AppiumUtils.sleep(500);

                    dfs(utg);
                } catch (Exception e) {
                    error("Error occurred.\n" + e.toString());
                }
            }
        }
    }

    @Override
    public void run() {
        dfs(utg);
    }
}
