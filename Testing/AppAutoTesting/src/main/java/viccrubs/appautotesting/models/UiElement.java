package viccrubs.appautotesting.models;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UiElement {
    private @Getter
    final List<UiHierarchyLevel> hierarchy;

    private @Getter final Ui ui;

    private @Getter final Node node;

    public String getXPath() {
        var sb = new StringBuilder("/hierarchy");
        for (var hierarchyLevel : hierarchy) {
            sb.append("/").append(
                hierarchyLevel.toString()
                    .replace("$", "\\$") // escape
            );
        }
        return sb.toString();
    }

    private @Getter @Setter boolean accessed;

    public UiHierarchyLevel getSelfLevel() {
        return hierarchy.get(hierarchy.size() - 1);
    }

    public String getTagName() {
        return getSelfLevel().getTagName();
    }

    public static UiElement create(
        List<UiHierarchyLevel> parentHierarchy, Ui ui, String tagName, int index,
        Node node
    ) {
        var selfHierarchy = new ArrayList<UiHierarchyLevel>(parentHierarchy);
        selfHierarchy.add(new UiHierarchyLevel(tagName, index));
        return new UiElement(selfHierarchy, ui, node, false);
    }

    public boolean getClickable() {
        return getAttrBoolean("clickable");
    }

    public boolean getScrollable() {
        return getAttrBoolean("scrollable");
    }

    public boolean getEnabled() {
        return getAttrBoolean("enabled");
    }

    public boolean getLongClickable() {
        return getAttrBoolean("longClickable");
    }

    public String getResourceId() {
        return getAttr("resource-id");
    }

    public String getText() {
        return getAttr("text");
    }

    private boolean getAttrBoolean(String key) {
        return Boolean.getBoolean(getAttr(key));
    }

    public String getAttr(String key) {
        return node.getAttributes().getNamedItem(key).getNodeValue();
    }

    @Override
    public String toString() {
        return String.format("tag: %s, resource-id: %s, text: %s", getTagName(), getResourceId(), getText());

    }
}
