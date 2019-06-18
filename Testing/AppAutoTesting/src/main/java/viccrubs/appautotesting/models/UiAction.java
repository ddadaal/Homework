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
    private final @Nullable String inputText;
    private final UiElement element;

    public void perform(AndroidDriver<AndroidElement> driver) {

        if (type == Type.BACK) {
            verbose("Execute back.");
            driver.navigate().back();
        } else {
            verbose("Find element %s.", element, element.getXPath());
            val androidElement = driver.findElementByXPath(element.getXPath());

            if (type == Type.CLICK) {
                verbose("Click the found element.");
                androidElement.click();
            }
        }

        AppiumUtils.sleep(500);
    }

    public enum Type {
        CLICK,
        BACK
    }

    public static UiAction BACK = new UiAction(Type.BACK, null, null);

}
