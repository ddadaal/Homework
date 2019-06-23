package viccrubs.appautotesting.log;


import java.time.LocalDateTime;

public interface Logger {
    default String getLoggerTag() {
        return getClass().getSimpleName();
    }

    default void log(String message, LogSeverity severity) {
        System.out.println(String.format("[%s] [%s] [%s] %s",
            LocalDateTime.now().toString(), getLoggerTag(), severity, message)
        );
    }

    default void verbose(String message, Object... params) {
        log(String.format(message, params), LogSeverity.VERBOSE);
    }

    default void warning(String message) {
        log(message, LogSeverity.WARNING);
    }

    default void error(String message, Object... params) {
        log(String.format(message, params), LogSeverity.ERROR);
    }

    default void report(String message, Object... params) {
        log(String.format(message, params), LogSeverity.REPORT);
    }

    public static Logger getInstance(String tag) {
        return new Logger() {
            @Override
            public String getLoggerTag() {
                return tag;
            }
        };
    }
}
