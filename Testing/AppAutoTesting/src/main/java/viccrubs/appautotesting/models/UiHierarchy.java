package viccrubs.appautotesting.models;

import lombok.val;
import lombok.var;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UiHierarchy {
    private List<UiHierarchyLevel> levels;

    public UiHierarchyLevel getSelf() {
        return levels.get(levels.size()-1);
    }

    public String getXPath() {
        var sb = new StringBuilder("/hierarchy");
        for (var hierarchyLevel : levels) {
            sb.append("/").append(
                hierarchyLevel.toString()
                    .replace("$", "\\$") // escape
            );
        }
        return sb.toString();
    }

    public String getShortenedPath() {
        var path = new StringBuilder();
        for (int i=1;i<levels.size();i++) {
            val item = levels.get(i);
            var tagName = item.getTagName();
            if (tagName.startsWith("android.widget.")) {
                tagName = tagName.substring("android.widget.".length());
            }
            path.append(tagName);
            if (item.getIndex() != 1) {
                path.append('[').append(item.getIndex()).append(']');
            }
            path.append("/");
        }

        return path.toString();
    }

    public UiHierarchy(UiHierarchy parent, String tagName, int index) {
        levels = new ArrayList<>(parent.levels);
        levels.add(new UiHierarchyLevel(tagName, index));
    }

    public UiHierarchy() {
        levels = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UiHierarchy)) return false;
        UiHierarchy that = (UiHierarchy) o;
        return Objects.equals(levels, that.levels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(levels);
    }
}
