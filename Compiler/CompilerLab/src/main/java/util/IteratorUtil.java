package util;

import lombok.var;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class IteratorUtil {
    public static <T> List<T> iterateAll(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        return list;
    }

    public static <T> ListIterator<Character> strToIterator(String str) {
        return str.chars()
            .mapToObj(i -> (char) i)
            .collect(Collectors.toList())
            .listIterator();
    }
}
