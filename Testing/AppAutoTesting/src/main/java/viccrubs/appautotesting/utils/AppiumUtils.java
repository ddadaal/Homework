package viccrubs.appautotesting.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import viccrubs.appautotesting.log.Logger;

@UtilityClass
public class AppiumUtils {


    private Logger logger = Logger.getInstance("AppiumUtils");


    @SneakyThrows
    public void sleep(int ms) {
        logger.verbose(String.format("Sleep for %d ms", ms));
        Thread.sleep(ms);
    }
}

