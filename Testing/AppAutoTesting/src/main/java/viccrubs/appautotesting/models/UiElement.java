package viccrubs.appautotesting.models;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.var;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UiElement {
    private @Getter
    final List<UiHierarchyLevel> hierarchy;
    private @Getter final Ui ui;

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

    public UiHierarchyLevel getSelfLevel() {
        return hierarchy.get(hierarchy.size() - 1);
    }

    public String getTagName() {
        return getSelfLevel().getTagName();
    }

    public static UiElement create(List<UiHierarchyLevel> parentHierarchy, Ui ui, String tagName, int index) {
        var selfHierarchy = new ArrayList<UiHierarchyLevel>(parentHierarchy);
        selfHierarchy.add(new UiHierarchyLevel(tagName, index));
        return new UiElement(selfHierarchy, ui);
    }

//    public void tap() {
//        xpath.click();
//    }
}
