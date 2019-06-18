package viccrubs.appautotesting.models;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.Getter;
import lombok.var;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UTGNode {
    private @Getter Ui ui;
    private @Getter List<UTGEdge> outEdges = new ArrayList<>();

    public static UTGNode create(Ui ui) {
        var node = new UTGNode();
        node.ui = ui;
        return node;
    }

    public static UTGNode create(AndroidDriver<AndroidElement> driver) {
        return create(Ui.create(driver));
    }

    @Override
    public String toString() {
        return getUi().toString();
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
        outEdges.add(new UTGEdge(this, end, action));
    }
}
