package viccrubs.appautotesting.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.var;
import lombok.experimental.UtilityClass;
import viccrubs.appautotesting.log.Logger;

@UtilityClass
public class LaunchArgsUtils implements Logger {


    private static final boolean MOCK = true;

    private static final String AAPT_TOOL_PATH = MOCK
        ? "C:\\Users\\viccrubs\\AppData\\Local\\Android\\Sdk\\build-tools\\29.0.0\\aapt.exe"
        : "assets/aapt"
        ;


    public LaunchArgs parseArgs(String[] args) {

        var apkPath = new File(MOCK ? "assets/Bihudaily.apk" : args[0]).getAbsolutePath();

        // parse output
        String appPackage = null;
        String mainActivity = null;

        for (String line: getAaptDumpOutput(apkPath)) {
            if (line.startsWith("package")) {
                appPackage = line.split("'")[1];
            } else if (line.startsWith("launchable-activity")) {
                mainActivity = line.split("'")[1];
            }
        }

        if (MOCK) {
            return new LaunchArgs(apkPath, appPackage, mainActivity, "Android Emulator", "4723", 3600);
        } else {
            return new LaunchArgs(apkPath, appPackage, mainActivity, args[1], args[2], Integer.parseInt(args[3]));
        }
    }

    @Data
    public class LaunchArgs {
        private final String appPath;
        private final String appPackage;
        private final String mainActivity;
        private final String deviceUdid;
        private final String port;
        private final int maxRuntime;
    }

    @SneakyThrows
    private ArrayList<String> getAaptDumpOutput(String apkPath) {
        Process process = Runtime.getRuntime().exec(String.format("\"%s\" dump badging \"%s\"", AAPT_TOOL_PATH, apkPath),
                null, new File("."));

        var output = new ArrayList<String>();

        var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            output.add(line);
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            return output;
        }
        throw new RuntimeException("aapt execution failed\n" + String.join("\n", output));
    }


}
