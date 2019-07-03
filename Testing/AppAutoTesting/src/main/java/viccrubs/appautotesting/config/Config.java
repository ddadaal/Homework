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
        private final Element btnGrant;
    }

    @Data
    public static class LimitedChildrenElement {
        private final Element element;
        private final int childLimit;

        public LimitedChildrenElement(String key, String value, int childLimit) {
            this.element = new Element(key, value);
            this.childLimit = childLimit;
        }
    }

    @Data
    public static class Activity {
        private final String packageName;
        private final String activityName;
    }


    public static final List<Activity> DOWN_SCROLLABLE_ACTIVITIES = Arrays.asList(
        new Activity("name.gudong.translate", ".ui.activitys.SettingActivity")
    );

    // 不取这些元素的子元素
    public static final List<Element> NO_CHILDREN_ELEMENTS = Arrays.asList(
        new Element("resource-id", "com.danmo.ithouse:id/item_container")
    );

    // 这些元素只取其前n个子元素
    public static final List<LimitedChildrenElement> LIMITED_CHILDREN_ELEMENTS = Arrays.asList(

        // IT之家社区列表和文章和广告列表
        new LimitedChildrenElement("resource-id", "com.danmo.ithouse:id/recycler_view", 2),
        new LimitedChildrenElement("resource-id", "com.danmo.ithouse:id/recycler_view_rank", 2),


        // GeedNews
        new LimitedChildrenElement("resource-id", "com.codeest.geeknews:id/rv_content", 1),
        new LimitedChildrenElement("resource-id", "com.codeest.geeknews:id/rv_tech_content", 1),
        new LimitedChildrenElement("resource-id", "com.codeest.geeknews:id/rv_comment_list", 2),

        new LimitedChildrenElement("resource-id", "com.white.bihudaily:id/rv_stories", 3),

        new LimitedChildrenElement("resource-id", "com.white.bihudaily:id/rv_comment", 4),


        // Bilibili 视频主页
        new LimitedChildrenElement("resource-id", "com.hotbitmapgg.ohmybilibili:id/recycle", 3)




    );

    public static final List<Element> IGNORED_ELEMENTS = Arrays.asList(
        // 状态栏和导航栏
        new Element("resource-id", "android:id/statusBarBackground"),
        new Element("resource-id", "android:id/navigationBarBackground"),
        new Element("class", "android.webkit.WebView"),

        // 输入框的清除按钮
        new Element("resource-id", "name.gudong.translate:id/tv_clear"),
        new Element("resource-id", "com.danmo.ithouse:id/iv_search_clear"),
        new Element("resource-id", "com.hotbitmapgg.ohmybilibili:id/action_empty_btn"),
        new Element("resource-id", "com.hotbitmapgg.ohmybilibili:id/search_text_clear"),
        new Element("resource-id", "com.codeest.geeknews:id/action_empty_btn"),

        // hack: Jiandou标签分类
        new Element("resource-id", "com.lhr.jiandou:id/pager_f_labelmovie"),

        // IT之家畅谈界面，忽略掉除了头像之外的的所有可用来进入文章界面的元素
        new Element("resource-id", "com.danmo.ithouse:id/community_item_c"),
        new Element("resource-id", "com.danmo.ithouse:id/community_item_t"),
        new Element("resource-id", "com.danmo.ithouse:id/community_item_un"),
        new Element("resource-id", "com.danmo.ithouse:id/community_item_pt"),
        new Element("resource-id", "com.danmo.ithouse:id/community_item_rn"),
        new Element("resource-id", "com.danmo.ithouse:id/community_item_rt"),
        new Element("resource-id", "com.danmo.ithouse:id/community_item_cn"),
        new Element("resource-id", "com.danmo.ithouse:id/community_item_vc"),
        new Element("resource-id", "com.danmo.ithouse:id/community_item_rc"),


        // GeekNews 微信列表，只保留一个入口
        new Element("resource-id", "com.codeest.geeknews:id/tv_wechat_item_title"),
        new Element("resource-id", "com.codeest.geeknews:id/tv_wechat_item_from"),
        new Element("resource-id", "com.codeest.geeknews:id/tv_wechat_item_time")


        // 回到上一级的按钮，不能忽略，有的app把划出侧边栏的也用Navigate up当做content-desc
//        new Element("content-desc", "Navigate up")
    );

    // 把这里的元素和当前界面最后一个元素对调
    public static final List<Element> DELAYED_ELEMENTS = Arrays.asList(
        new Element("content-desc", "Navigate up"),
        new Element("resource-id", "com.codeest.geeknews:id/action_up_btn"),
        new Element("content-desc", "打开"),

        // IT之家栏目自定义按钮
        new Element("resource-id", "com.danmo.ithouse:id/icon_toolbar_custom")

        );

    public static final List<String> INPUTS = Arrays.asList(
        "Hello",
        "你好"
    );

    public static final long REPORT_INTERVAL_MS = 20 * 1000;

    public static final int LAUNCH_WAIT_MS = 7000;

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
