package viccrubs.appautotesting.utils;

import java.util.function.Supplier;

public class Lazy<T> {

    private boolean evaluated = false;
    private T value = null;
    private Supplier<T> supplier;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        if (!evaluated) {
            value = supplier.get();
            evaluated = true;
        }
        return value;
    }
}
