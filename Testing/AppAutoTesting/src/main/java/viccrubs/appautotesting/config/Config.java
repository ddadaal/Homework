package viccrubs.appautotesting.config;


import com.google.common.collect.Maps;
import lombok.Data;
import viccrubs.appautotesting.models.UiElement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {

    @Data
    public static class Element {
        private final String key;
        private final String value;
    }

    @Data
    public static class LoginInfo {
        private final String username;
        private final Element usernameField;
        private final String password;
        private final Element passwordField;
        private final Element loginBtn;
    }

    public static final List<Element> IGNORED_ELEMENTS = Arrays.asList(
        new Element("resource-id", "android:id/statusBarBackground"),
        new Element("resource-id", "android:id/navigationBarBackground"),
        new Element("class", "android.webkit.WebView")
    );

    public static final List<String> INPUTS = Arrays.asList(
        "Hello",
        "你好"
    );

    public static final Map<String, LoginInfo> LOGIN_INFO_MAP = new HashMap<>();

    static {
        // bilibili
        LOGIN_INFO_MAP.put("com.hotbitmapgg.bilibili.module.common.LoginActivity", new LoginInfo(
            "1", new Element("resource-id", "com.hotbitmapgg.ohmybilibili:id/et_username"),
            "1", new Element("resource-id", "com.hotbitmapgg.ohmybilibili:id/et_password"),
            new Element("resource-id", "com.hotbitmapgg.ohmybilibili:id/btn_login")
        ));
    }
}
