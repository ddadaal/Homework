package viccrubs.appautotesting.models;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UiElement {
    private @Getter final UiHierarchy hierarchy;

    private @Getter final Ui ui;

    private @Getter final Node node;

    private @Getter @Setter boolean accessed = false;

    public UiElement(UiHierarchy parentHierarchy, String tagName, int index, Ui ui, Node node) {
        hierarchy = new UiHierarchy(parentHierarchy, tagName, index);
        this.ui = ui;
        this.node = node;

        this.hashCode = Objects.hash(getUi().getActivityName(), getNode());
    }

    public String getXPath() {
        return hierarchy.getXPath();
    }



    public int getIndex() {
        return hierarchy.getSelf().getIndex();
    }

    public String getTagName() {
        return hierarchy.getSelf().getTagName();
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

    public String getPackage() {
        return getAttr("package");
    }

    public boolean getInputable() {
        val tagNames = Arrays.asList("EditText", "ExtractEditText", "AutoCompleteTextView");
        return tagNames.stream().anyMatch(x -> getTagName().endsWith(x));
    }

    private boolean getAttrBoolean(String key) {
        return Boolean.getBoolean(getAttr(key));
    }

    public String getAttr(String key) {
        return node.getAttributes().getNamedItem(key).getNodeValue();
    }

    @Override
    public String toString() {
        return String.format("tag: %s, resource-id: %s, text: %s, path: %s",
            getTagName(), getResourceId(), getText(), hierarchy.getShortenedPath());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UiElement)) return false;
        UiElement uiElement = (UiElement) o;
        return Objects.equals(getUi().getActivityName(), uiElement.getUi().getActivityName()) &&
            Objects.equals(getNode(), uiElement.getNode());
    }

    private int hashCode;

    @Override
    public int hashCode() {
        return hashCode;
    }
}
