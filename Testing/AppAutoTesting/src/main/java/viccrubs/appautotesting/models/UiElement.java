package viccrubs.appautotesting.models;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.w3c.dom.Node;
import viccrubs.appautotesting.config.Config;
import viccrubs.appautotesting.utils.Lazy;

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
    }

    public String getXPath() {
        return hierarchy.getXPath();
    }

    public boolean matchConfigElement(Config.Element configElement) {
        return getAttr(configElement.getKey()).equals(configElement.getValue());
    }

    public boolean matchConfigElement(List<Config.Element> configElements) {
        return configElements.stream().anyMatch(this::matchConfigElement);
    }

    public int getIndex() {
        return hierarchy.getSelf().getIndex();
    }

    public String getTagName() {
        return hierarchy.getSelf().getTagName();
    }

    public boolean getClickable() {
        return getBooleanAttr("clickable");
    }

    public boolean getScrollable() {
        return getBooleanAttr("scrollable");
    }

    public boolean getEnabled() {
        return getBooleanAttr("enabled");
    }

    public boolean getLongClickable() {
        return getBooleanAttr("longClickable");
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

    private boolean getBooleanAttr(String key) {
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

    // activity和path一样，就说明是同样的元素
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UiElement)) return false;
        UiElement uiElement = (UiElement) o;
        return Objects.equals(getUi().getActivityName(), uiElement.getUi().getActivityName()) &&
            Objects.equals(hierarchy.getShortenedPath(), uiElement.hierarchy.getShortenedPath());
    }

    private Lazy<Integer> hashCode = new Lazy<>(() ->
        Objects.hash(getUi().getActivityName(), getHierarchy().getShortenedPath()));

    @Override
    public int hashCode() {
        return hashCode.get();
    }
}
