package viccrubs.appautotesting;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import viccrubs.appautotesting.models.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws Exception {
        // parse args
        System.out.println("Hello world!");

        //设置自动化相关参数
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "com.danmo.ithouse");
        capabilities.setCapability("appActivity", ".activity.MainActivity");
        capabilities.setCapability("noSign", "true");
        capabilities.setCapability("autoGrantPermissions", "true");

        //设置使用unicode键盘，支持输入中文和特殊字符
        capabilities.setCapability("unicodeKeyboard","true");
        //设置用例执行完成后重置键盘
        capabilities.setCapability("resetKeyboard","true");

        AppiumDriver<MobileElement> driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


        Model m = new Model("123");
        System.out.println(m.getString());
    }
}
