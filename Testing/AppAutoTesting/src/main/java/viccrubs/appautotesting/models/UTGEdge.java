package viccrubs.appautotesting.models;

import lombok.Data;

@Data
public class UTGEdge {
    private final UTGNode startNode;
    private final UTGNode endNode;
    private final UiAction action;
}
