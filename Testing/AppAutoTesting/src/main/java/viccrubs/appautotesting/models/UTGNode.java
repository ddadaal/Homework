package viccrubs.appautotesting.models;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import lombok.var;
import viccrubs.appautotesting.utils.Lazy;

import java.util.*;
import java.util.stream.Collectors;

public class UTGNode {

    // special node type
    public enum Type {
        NORMAL,
        CRASH, // 挂了的节点
        SHARE, // 分享面板
        EXTERNAL_APP // 正常点击去的另一个界面，比如浏览器
    }

    private @Getter @Setter int id;
    private @Getter @Setter Ui ui;
    private @Getter @Setter Type type = Type.NORMAL;
    private @Getter Map<UiAction, UTGNode> outEdges = new HashMap<>();

    private UTGNode(Ui ui) {
        this.ui = ui;
    }

    public List<UTGEdge> getEdges() {
        return outEdges.entrySet().stream()
            .map(edge -> new UTGEdge(this, edge.getValue(), edge.getKey()))
            .collect(Collectors.toList());
    }

    public boolean completed() {
        // 如果是特殊节点，就返回这个界面已经完成，防止再次进入这个界面
        return type != Type.NORMAL || ui.completed();
    }

    public static UTGNode create(AppiumDriver driver) {
        return new UTGNode(Ui.create(driver));
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

    public Lazy<Integer> hashCode = new Lazy<>(() -> Objects.hash(getUi()));

    @Override
    public int hashCode() {
        return hashCode.get();
    }

    public void setOutEdge(UTGNode end, UiAction action) {
        outEdges.put(action, end);
    }
}
