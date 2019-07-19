package viccrubs.appautotesting.utils;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.var;
import viccrubs.appautotesting.log.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

@UtilityClass
public class LaunchArgsUtils  {


    private static final boolean MOCK = true;

    private static final String AAPT_TOOL_PATH = MOCK
//        ? "assets/aapt"
        ? "C:\\Users\\viccrubs\\AppData\\Local\\Android\\Sdk\\build-tools\\29.0.0\\aapt.exe"
        : "assets/aapt"
        ;


    public LaunchArgs parseArgs(String[] args) {

        var apkPath = new File(MOCK ? "assets/Jiandou.apk" : args[0]).getAbsolutePath();

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
            return new LaunchArgs(true, apkPath, appPackage, mainActivity, "Android Emulator", "4723", 5);
        } else {
            return new LaunchArgs(false, apkPath, appPackage, mainActivity, args[1], args[2], Integer.parseInt(args[3]));
        }
    }

    @Data
    public class LaunchArgs {
        private final boolean mock;
        private final String appPath;
        private final String appPackage;
        private final String mainActivity;
        private final String deviceUdid;
        private final String port;
        private final int maxRuntime;
    }

    @SneakyThrows
    private ArrayList<String> getAaptDumpOutput(String apkPath) {
        Process process = Runtime.getRuntime().exec(new String[] {
                AAPT_TOOL_PATH, "dump", "badging", apkPath
            }, null, new File("."));

        var output = new ArrayList<String>();

        var stdOut = new BufferedReader(new InputStreamReader(process.getInputStream()));

        var stdError = new BufferedReader(new
            InputStreamReader(process.getErrorStream()));

        String line;
        while ((line = stdOut.readLine()) != null) {
            output.add(line);
        }

        var errOut = new ArrayList<String>();
        while ((line = stdError.readLine()) != null) {
            errOut.add(line);
        }

        int exitVal = process.waitFor();
        if (exitVal == 0) {
            return output;
        }
        throw new RuntimeException("aapt execution failed. stderr: \n" + String.join("\n", String.join(System.lineSeparator(), errOut)));
    }


}
