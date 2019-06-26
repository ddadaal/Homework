package viccrubs.appautotesting.crawlers;

import io.appium.java_client.AppiumDriver;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import viccrubs.appautotesting.config.Config;
import viccrubs.appautotesting.log.Logger;

import java.util.Timer;
import java.util.TimerTask;

@RequiredArgsConstructor
public abstract class Crawler implements Logger {

    protected final AppiumDriver driver;



    protected @Getter long startTime;

    @SneakyThrows
    public void sleep(int ms) {
        verbose(String.format("Sleep for %d ms", ms));
        Thread.sleep(ms);
    }

    public void doReport() {

    }

    protected long getRunningTime() {
        return System.currentTimeMillis() - startTime;
    }

    public void startReport() {
        startTime = System.currentTimeMillis();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                doReport();
            }
        }, 0, Config.REPORT_INTERVAL_MS);
    }

    public void run() {
        startReport();

        while (true) {
            try {
                doCrawl();
            } catch (Throwable e) {
                error("Error occurred. " + e.getMessage());
            }
        }

    }

    public abstract void doCrawl();
}
