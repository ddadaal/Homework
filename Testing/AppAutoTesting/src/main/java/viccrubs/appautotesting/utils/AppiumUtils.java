package viccrubs.appautotesting.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AppiumUtils {

    public List<AndroidElement> findAllLeafElements(AndroidDriver<AndroidElement> driver) {
        return driver.findElementsByXPath("//*[not(child::*)]");
    }


    @SneakyThrows
    public void sleep(int ms) {
        Thread.sleep(ms);
    }
}

