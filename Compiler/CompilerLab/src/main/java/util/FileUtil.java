package util;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {
    public static String getContentsOfResource(String filePath) {
        try {
            URI sourceFile = FileUtil.class.getResource(filePath).toURI();
            return String.join("\n", Files.readAllLines(Paths.get(sourceFile)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
