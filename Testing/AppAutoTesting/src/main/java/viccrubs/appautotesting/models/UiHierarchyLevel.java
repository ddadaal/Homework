package viccrubs.appautotesting.models;

import lombok.Data;
import lombok.Getter;

@Data
public class UiHierarchyLevel {
    @Getter
    private final String tagName;

    @Getter
    private final int index;

    @Override
    public String toString() {
        return String.format("%s[%d]", tagName, index);
    }


}
