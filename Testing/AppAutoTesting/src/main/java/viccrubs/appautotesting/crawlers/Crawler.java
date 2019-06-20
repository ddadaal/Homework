package viccrubs.appautotesting.crawlers;

import io.appium.java_client.AppiumDriver;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import viccrubs.appautotesting.log.Logger;

@AllArgsConstructor
public abstract class Crawler implements Logger {

    protected AppiumDriver driver;

    @SneakyThrows
    public void sleep(int ms) {
        verbose(String.format("Sleep for %d ms", ms));
        Thread.sleep(ms);
    }

    public abstract void run();
}
