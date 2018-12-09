package util;

import lombok.experimental.ExtensionMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CollectionUtil {
    public static <T> boolean containsSameElements(Collection<T> c1, Collection<T> c2) {
        return c1.containsAll(c2) && c2.containsAll(c1);
    }



}
