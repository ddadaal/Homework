package viccrubs.appautotesting.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import lombok.var;
import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import viccrubs.appautotesting.log.Logger;
import viccrubs.appautotesting.models.Ui;
import viccrubs.appautotesting.models.UiElement;
import viccrubs.appautotesting.models.UiHierarchyLevel;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@UtilityClass
public class AppiumUtils {

    private AndroidDriver<AndroidElement> driver;

    private Logger logger = Logger.getInstance("AppiumUtils");

    public AndroidDriver<AndroidElement> getDriver() {
        return driver;
    }

    public void setDriver(AndroidDriver<AndroidElement> driver) {
        AppiumUtils.driver = driver;
    }


    @SneakyThrows
    public void sleep(int ms) {
        logger.verbose(String.format("Sleep for %d ms", ms));
        Thread.sleep(ms);
    }
}

