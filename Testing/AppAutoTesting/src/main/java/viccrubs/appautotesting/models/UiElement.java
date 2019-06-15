package viccrubs.appautotesting.models;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.Data;

@Data
public class UiElement {
    private AndroidElement xpath;
    private String activity;

    public void tap() {
        xpath.click();
    }
}
