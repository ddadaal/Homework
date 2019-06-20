package viccrubs.appautotesting.models;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UTGNode {

    private @Getter @Setter int id;
    private @Getter @Setter Ui ui;
    private @Getter Map<UiAction, UTGNode> outEdges = new HashMap<>();

    public static UTGNode create(Ui ui) {
        var node = new UTGNode();
        node.ui = ui;
        return node;
    }

    public static UTGNode create(AppiumDriver driver) {
        return create(Ui.create(driver));
    }

    @Override
    public String toString() {
        return String.format("id: %d, ui: %s", id, getUi());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UTGNode)) return false;
        UTGNode utgNode = (UTGNode) o;
        return getUi().equals(utgNode.getUi());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUi());
    }

    public void addOutEdge(UTGNode end, UiAction action) {
        outEdges.put(action, end);
    }
}
