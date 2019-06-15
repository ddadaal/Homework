package viccrubs.appautotesting.models;

import lombok.Data;

@Data
public class UTGEdge {
    private final UTGNode start;
    private final UTGNode end;
    private final UiEvent event;
}
