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

    @SneakyThrows
    public static Iterator<String> getLineIterator(String filePath) {
        URI sourceFile = FileUtil.class.getResource(filePath).toURI();
        return Files.newBufferedReader(Paths.get(sourceFile)).lines().iterator();
    }

    @SneakyThrows
    public static Iterator<Character> getCharIterator(String filePath) {
        URI sourceFile = FileUtil.class.getResource(filePath).toURI();
        var bufferedReader = Files.newBufferedReader(Paths.get(sourceFile));

        return new Iterator<Character>() {
            @Override
            @SneakyThrows
            public boolean hasNext() {
                return bufferedReader.ready();
            }

            @Override
            @SneakyThrows
            public Character next() {
                return (char)bufferedReader.read();
            }
        };

    }

}
