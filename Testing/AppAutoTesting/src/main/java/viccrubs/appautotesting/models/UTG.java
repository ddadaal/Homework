package viccrubs.appautotesting.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class UTG {

    private UTGNode startNode;
    private List<UTGNode> nodes;

    public UTG(UTGNode startNode) {
        this.startNode = startNode;
        nodes = new ArrayList<>();
        nodes.add(startNode);
    }
}
