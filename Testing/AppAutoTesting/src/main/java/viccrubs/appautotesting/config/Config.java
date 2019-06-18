package viccrubs.appautotesting.config;


import lombok.Data;
import viccrubs.appautotesting.models.UiElement;

import java.util.Arrays;
import java.util.List;

public class Config {

    @Data
    public static class Element {
        private final String key;
        private final String value;

        public boolean match(UiElement uiElement) {
            return uiElement.getAttr(key).equals(value);
        }
    }

    public static final List<Element> IGNORED_ELEMENTS = Arrays.asList(
        new Element("resource-id", "android:id/statusBarBackground"),
        new Element("resource-id", "android:id/navigationBarBackground"),
        new Element("class", "android.webkit.WebView")
    );
}
