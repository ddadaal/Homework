package util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorUtil {
    public static <T> List<T> iterateAll(Iterator<T> iterator) {
        List<T> list = new ArrayList<>();
        iterator.forEachRemaining(list::add);
        return list;
    }

    public static ListIterator<Character> strToIterator(String str) {
        return new BufferedIterator<>(str.chars().mapToObj(x -> (char)x).iterator());
    }
}
