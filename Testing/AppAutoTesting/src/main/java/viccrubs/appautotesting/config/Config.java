package viccrubs.appautotesting.config;


import lombok.Data;

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

    @Data
    public static class PermissionConfig {
        private final String packageName;
        private final String activityName;
        private final Element btnLogin;
    }

    public static final List<Element> IGNORED_ELEMENTS = Arrays.asList(
        // 状态栏和导航栏
        new Element("resource-id", "android:id/statusBarBackground"),
        new Element("resource-id", "android:id/navigationBarBackground"),
        new Element("class", "android.webkit.WebView"),

        // 输入框的清除按钮
        new Element("resource-id", "name.gudong.translate:id/tv_clear"),
        new Element("resource-id", "com.danmo.ithouse:id/iv_search_clear"),

        // hack: Jiandou标签分类
        new Element("resource-id", "com.lhr.jiandou:id/pager_f_labelmovie")

        // 回到上一级的按钮，不能忽略，有的app把划出侧边栏的也用Navigate up当做content-desc
//        new Element("content-desc", "Navigate up")
    );

    // 回到上一级的按钮，把他和当前界面最后一个元素对调
    public static final List<Element> NAVIGATE_UP_ELEMENTS = Arrays.asList(
        new Element("content-desc", "Navigate up")
//        new Element("resource-id", "com.danmo.ithouse:id/iv_search_back")
    );

    public static final List<String> INPUTS = Arrays.asList(
        "Hello",
        "你好"
    );

    public static final long REPORT_INTERVAL_MS = 20 * 1000;

    public static final Map<String, LoginInfo> LOGIN_INFO_MAP = new HashMap<>();

    public static final List<PermissionConfig> PERMISSION_CONFIGS = Arrays.asList(
        new PermissionConfig("com.android.packageinstaller", ".permission.ui.GrantPermissionsActivity",
            new Element("resource-id", "com.android.packageinstaller:id/permission_allow_button")
        )
    );

    static {
        // bilibili
        LOGIN_INFO_MAP.put("com.hotbitmapgg.bilibili.module.common.LoginActivity", new LoginInfo(
            "1", new Element("resource-id", "com.hotbitmapgg.ohmybilibili:id/et_username"),
            "1", new Element("resource-id", "com.hotbitmapgg.ohmybilibili:id/et_password"),
            new Element("resource-id", "com.hotbitmapgg.ohmybilibili:id/btn_login")
        ));
    }
}
