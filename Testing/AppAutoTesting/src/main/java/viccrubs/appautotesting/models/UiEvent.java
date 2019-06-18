package viccrubs.appautotesting.models;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.Data;
import lombok.val;
import viccrubs.appautotesting.log.Logger;
import viccrubs.appautotesting.utils.AppiumUtils;

import javax.annotation.Nullable;

@Data
public class UiEvent implements Logger {

    private final Type type;
    private final @Nullable String inputText;
    private final UiElement element;

    public void perform(AndroidDriver<AndroidElement> driver) {
        verbose("Find element %s. XPath: %s", element, element.getXPath());
        val androidElement = driver.findElementByXPath(element.getXPath());

        if (type == Type.CLICK) {
            verbose("Click the found element.");
            androidElement.click();
        }

        AppiumUtils.sleep(500);
    }

    public enum Type {
        CLICK,
    }

}
