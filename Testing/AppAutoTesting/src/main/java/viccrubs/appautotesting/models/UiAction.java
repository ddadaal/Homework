package viccrubs.appautotesting.models;

import io.appium.java_client.AppiumDriver;
import lombok.Data;
import lombok.val;
import lombok.var;
import viccrubs.appautotesting.log.Logger;
import viccrubs.appautotesting.utils.AppiumUtils;

@Data
public class UiAction implements Logger {

    private final Type type;
    private final String inputText;
    private final UiElement element;

    private void waitForStable(AppiumDriver driver, Ui currentUi) {
        // 等1s，或者界面改变
//        new WebDriverWait(driver, 1000).until((ExpectedCondition) driver -> {
//
//        });
//    AppiumUtils.sleep(500);

        AppiumUtils.sleep(100);
        // 每次等300ms，如果之间界面改变，就继续等；直到某个200ms内界面不变或者3s超时
        long startTime = System.currentTimeMillis();
        verbose("Wait for stable UI");
        val interval = 300;
        val timeout = 3000;
        var trialTime = 0;

        trialLoop: while (System.currentTimeMillis() - startTime < timeout) {
            val baseTime = System.currentTimeMillis();

            while (System.currentTimeMillis() - baseTime < interval) {
                val nowUi = Ui.create(driver);
                if (!nowUi.equals(currentUi)) {
                    // 界面变化，继续循环
                    trialTime++;
                    currentUi = nowUi;
                    continue trialLoop;
                }
            }

            // interval内都没有变，退出循环，继续
            break;
        }
        verbose("Ui stabilized. Change time: %d, wait time: %d", trialTime, System.currentTimeMillis() - startTime);
    }

    // baseUi是作为判断界面有没有变化的标准
    public void perform(AppiumDriver driver, Ui baseUi) {

        if (type == Type.DOUBLE_BACK) {
            verbose("Execute double back.");
            driver.navigate().back();
            driver.navigate().back();
        } else if (type == Type.BACK) {
            verbose("Execute back.");
            driver.navigate().back();
        } else if (type == Type.SCROLL_DOWN) {
            verbose("Scroll down.");
            val dimension = driver.manage().window().getSize();
            driver.swipe(dimension.getWidth() / 2,
                dimension.getHeight() /2,
                dimension.getWidth() / 2,
                dimension.getHeight() /4,
                500);
        } else {
            verbose("Find element %s.", element, element.getXPath());
            val androidElement = driver.findElementByXPath(element.getXPath());

            if (!androidElement.isEnabled()) {
                verbose("Element not enabled. Perform no action.");
                return;
            }

            if (type == Type.CLICK) {
                verbose("Click the found element.");
                androidElement.click();
            } else if (type == Type.INPUT) {
                verbose("Input the found element with text %s", inputText);
                androidElement.click();
                androidElement.sendKeys(inputText);
            }
        }

        waitForStable(driver, baseUi);
    }

    public enum Type {
        CLICK,
        BACK,
        DOUBLE_BACK,
        INPUT,
        SCROLL_DOWN
    }

    public static UiAction BACK = new UiAction(Type.BACK, null, null);

    public static UiAction DOUBLE_BACK = new UiAction(Type.DOUBLE_BACK, null, null);

    public static UiAction SCROLL_DOWN = new UiAction(Type.SCROLL_DOWN, null, null);

}
