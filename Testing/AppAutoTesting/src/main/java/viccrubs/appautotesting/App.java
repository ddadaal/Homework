package viccrubs.appautotesting;

import io.appium.java_client.AppiumDriver;
import lombok.SneakyThrows;
import lombok.val;
import lombok.var;
import org.openqa.selenium.remote.DesiredCapabilities;
import viccrubs.appautotesting.config.Config;
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

    private Thread terminationThread;

    private App(String[] args) {
        launch(args);
    }

    @SneakyThrows
    private void launch(String[] args) {
        // start

        var props = LaunchArgsUtils.parseArgs(args);

        verbose("Parsed launch args: %s", props);

        var capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", props.getDeviceUdid());
        capabilities.setCapability("appPackage", props.getAppPackage());
        capabilities.setCapability("appActivity", props.getMainActivity());
        if (props.isMock()) {
            capabilities.setCapability("avd", "Pixel_3_API_23");
        } else {
            capabilities.setCapability("udid", props.getDeviceUdid());
        }
        capabilities.setCapability("noSign", "true");
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("automationName", "UiAutomator1");
        capabilities.setCapability("app", props.getAppPath());
//        capabilities.setCapability("androidCoverage", "com.android.emulator.smoketests/android.support.test.runner.AndroidJUnitRunner");

        //设置使用unicode键盘，支持输入中文和特殊字符
        capabilities.setCapability("unicodeKeyboard", "true");
        //设置用例执行完成后重置键盘
        capabilities.setCapability("resetKeyboard", "true");
        capabilities.setCapability("newCommandTimeout", 100000);

        val driver = new AppiumDriver(new URL(String.format("http://127.0.0.1:%s/wd/hub", props.getPort())), capabilities);

        driver.manage().timeouts().implicitlyWait(6000, TimeUnit.MILLISECONDS);

        verbose("Driver initialized.");


        // 最长运行时间timer
        startTerminationTimer(props.getMaxRuntime());

        // 等待应用启动完成

        verbose("Wait for app launch...");

        AppiumUtils.sleep(Config.LAUNCH_WAIT_MS);

        verbose("App launched.");


        //  resource-id: com.xiecc.seeWeather:id/item_city

        // 开始遍历
        new DFSCrawler(driver, props.getAppPackage()).run();

        verbose("App completed. Shutting down");

        new Thread().setDaemon(true);


    }



    private void startTerminationTimer(int maxRuntimeSeconds) {
        terminationThread = new Thread(() -> {
            try {
                Thread.sleep(maxRuntimeSeconds * 1000);
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        terminationThread.setDaemon(true);
        terminationThread.start();

        verbose(String.format("Program will terminate after %d seconds", maxRuntimeSeconds));
    }

    public static void main(String[] args) throws Exception {
        new App(args);
    }
}
