package viccrubs.appautotesting.models;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.Data;
import lombok.val;
import viccrubs.appautotesting.log.Logger;
import viccrubs.appautotesting.utils.AppiumUtils;

import javax.annotation.Nullable;

@Data
public class UiAction implements Logger {

    private final Type type;
    private final String inputText;
    private final UiElement element;

    private void sleep() {
        AppiumUtils.sleep(500);
    }

    public void perform(AndroidDriver<AndroidElement> driver) {

        if (type == Type.DOUBLE_BACK) {
            verbose("Execute double back.");
            driver.navigate().back();
            sleep();
            driver.navigate().back();
        }
        if (type == Type.BACK) {
            verbose("Execute back.");
            driver.navigate().back();
        } else {
            verbose("Find element %s.", element, element.getXPath());
            val androidElement = driver.findElementByXPath(element.getXPath());

            if (type == Type.CLICK) {
                verbose("Click the found element.");
                androidElement.click();
            } else if (type == Type.INPUT) {
                verbose("Input the found element with text %s", inputText);
                androidElement.click();
                androidElement.sendKeys(inputText);
            }
        }

        sleep();
    }

    public enum Type {
        CLICK,
        BACK,
        DOUBLE_BACK,
        INPUT,
        SCROLL
    }

    public static UiAction BACK = new UiAction(Type.BACK, null, null);

    public static UiAction DOUBLE_BACK = new UiAction(Type.DOUBLE_BACK, null, null);

}
