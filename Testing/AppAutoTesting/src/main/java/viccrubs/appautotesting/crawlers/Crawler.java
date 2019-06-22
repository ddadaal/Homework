package viccrubs.appautotesting.crawlers;

import io.appium.java_client.AppiumDriver;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import viccrubs.appautotesting.config.Config;
import viccrubs.appautotesting.log.Logger;

import java.util.Timer;
import java.util.TimerTask;

@AllArgsConstructor
public abstract class Crawler implements Logger {

    protected AppiumDriver driver;

    @SneakyThrows
    public void sleep(int ms) {
        verbose(String.format("Sleep for %d ms", ms));
        Thread.sleep(ms);
    }

    public void doReport() {

    }

    public void startReport() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                doReport();
            }
        }, 0, Config.REPORT_INTERVAL_MS);
    }

    public void run() {
        startReport();
        try {
            doCrawl();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public abstract void doCrawl();
}
