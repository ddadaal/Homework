package viccrubs.appautotesting;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Executors;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.SneakyThrows;
import lombok.val;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import lombok.var;
import viccrubs.appautotesting.models.UTG;
import viccrubs.appautotesting.models.UTGNode;
import viccrubs.appautotesting.utils.AppiumUtils;
import viccrubs.appautotesting.utils.LaunchArgsUtils;

/**
 * Hello world!
 */
public final class App {

    private App(String[] args) {
        launch(args);
    }

    @SneakyThrows
    private void launch(String[] args) {
        // start

        var launchProps = LaunchArgsUtils.parseArgs(args);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", launchProps.getDeviceUdid());
        capabilities.setCapability("appPackage", launchProps.getAppPackage());
        capabilities.setCapability("appActivity", launchProps.getMainActivity());
        capabilities.setCapability("noSign", "true");
        capabilities.setCapability("autoGrantPermissions", "true");
        capabilities.setCapability("automationName", "UiAutomator1");
        capabilities.setCapability("app", launchProps.getAppPath());

        //设置使用unicode键盘，支持输入中文和特殊字符
        capabilities.setCapability("unicodeKeyboard", "true");
        //设置用例执行完成后重置键盘
        capabilities.setCapability("resetKeyboard", "true");

        val driver = new AndroidDriver<AndroidElement>(new URL(String.format("http://127.0.0.1:%s/wd/hub", launchProps.getPort())), capabilities);

        // 最长运行时间timer
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                Thread.sleep(launchProps.getMaxRuntime() * 1000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 等待应用启动完成
        AppiumUtils.sleep(7000);

        // 以目前的activity创建UTG以及其第一个节点
//        val utg = new UTG(UTGNode.createCurrent(driver));

        // 先随便试试，直接DFS

        val accessedActivities = new HashSet<String>();

        dfs(driver, accessedActivities);
    }

    void dfs(AndroidDriver<AndroidElement> driver, HashSet<String> accessed) {
        val currentActivity = driver.currentActivity();
        if (accessed.add(currentActivity)) {
            // 第一次访问这个activity
            val leaves = AppiumUtils.findAllLeafElements(driver);

            for (val leaf: leaves) {
                try {
                    leaf.click();
                    AppiumUtils.sleep(2000);
                    if (!driver.currentActivity().equals(currentActivity)) {
                        dfs(driver, accessed);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            driver.navigate().back();
        }

    }

    public static void main(String[] args) throws Exception {
        new App(args);
    }
}
