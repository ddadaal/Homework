package util;

import lombok.SneakyThrows;
import lombok.var;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.ListIterator;

public class FileUtil {
    @SneakyThrows
    public static String getContentsOfResource(String filePath) {
        URI sourceFile = FileUtil.class.getResource(filePath).toURI();
        return String.join("\n", Files.readAllLines(Paths.get(sourceFile)));
    }

}
