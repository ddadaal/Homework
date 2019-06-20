package viccrubs.appautotesting;

import io.appium.java_client.AppiumDriver;
import lombok.SneakyThrows;
import lombok.val;
import lombok.var;
import org.openqa.selenium.remote.DesiredCapabilities;
import viccrubs.appautotesting.crawlers.DFSCrawler;
import viccrubs.appautotesting.log.Logger;
import viccrubs.appautotesting.utils.AppiumUtils;
import viccrubs.appautotesting.utils.LaunchArgsUtils;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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

        var props = LaunchArgsUtils.parseArgs(args);

        var capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", props.getDeviceUdid());
        capabilities.setCapability("appPackage", props.getAppPackage());
        capabilities.setCapability("appActivity", props.getMainActivity());
        if (!props.getDeviceUdid().equals("Android Emulator")) {
            capabilities.setCapability("udid", props.getDeviceUdid());
        }
        capabilities.setCapability("noSign", "true");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("automationName", "UiAutomator1");
        capabilities.setCapability("app", props.getAppPath());

        //设置使用unicode键盘，支持输入中文和特殊字符
        capabilities.setCapability("unicodeKeyboard", "true");
        //设置用例执行完成后重置键盘
        capabilities.setCapability("resetKeyboard", "true");
        capabilities.setCapability("newCommandTimeout", 100000);

        val driver = new AppiumDriver(new URL(String.format("http://127.0.0.1:%s/wd/hub", props.getPort())), capabilities);

        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

        verbose("Driver initialized.");


        // 最长运行时间timer
        startTerminationTimer(props.getMaxRuntime());

        // 等待应用启动完成

        verbose("Wait for app launch...");

        AppiumUtils.sleep(5000);

        verbose("App launched.");



        // 开始遍历
        new DFSCrawler(driver, props.getAppPackage()).run();

        verbose("App completed. Shutting down");
        terminationThread.shutdown();

        System.exit(0);

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
