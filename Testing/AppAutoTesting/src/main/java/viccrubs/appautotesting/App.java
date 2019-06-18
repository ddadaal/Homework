package viccrubs.appautotesting;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.SneakyThrows;
import lombok.val;
import org.openqa.selenium.remote.DesiredCapabilities;

import lombok.var;
import viccrubs.appautotesting.crawlers.DFSCrawler;
import viccrubs.appautotesting.log.Logger;
import viccrubs.appautotesting.models.UTG;
import viccrubs.appautotesting.models.UTGNode;
import viccrubs.appautotesting.utils.AppiumUtils;
import viccrubs.appautotesting.utils.LaunchArgsUtils;

/**
 * Hello world!
 */
public final class App implements Logger {

    private ExecutorService terminationThread;

    private App(String[] args) {
        launch(args);
    }

    @SneakyThrows
    private void launch(String[] args) {
        // start

        var launchProps = LaunchArgsUtils.parseArgs(args);

        var capabilities = new DesiredCapabilities();
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
        capabilities.setCapability("newCommandTimeout", 100000);

        val driver = new AndroidDriver<AndroidElement>(new URL(String.format("http://127.0.0.1:%s/wd/hub", launchProps.getPort())), capabilities);

        AppiumUtils.setDriver(driver);

        verbose("Driver initialized.");


        // 最长运行时间timer
        startTerminationTimer(launchProps.getMaxRuntime());

        // 等待应用启动完成

        verbose("Wait for app launch...");

        AppiumUtils.sleep(5000);

        verbose("App launched.");



        // 开始遍历
        new DFSCrawler(driver).run();

        verbose("App completed. Shutting down");
        terminationThread.shutdown();

    }



    private void startTerminationTimer(int maxRuntimeSeconds) {
        terminationThread = Executors.newSingleThreadExecutor();

        terminationThread.submit(() -> {
            try {
                Thread.sleep(maxRuntimeSeconds * 1000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        verbose(String.format("Program will terminate after %d seconds", maxRuntimeSeconds));
    }

    public static void main(String[] args) throws Exception {
        new App(args);
    }
}
