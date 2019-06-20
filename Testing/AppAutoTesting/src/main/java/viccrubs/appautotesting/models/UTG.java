package viccrubs.appautotesting.models;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class UTG {

    private @Getter UTGNode startNode;
    private @Getter Set<UTGNode> nodes;

    public UTG(UTGNode startNode) {
        this.startNode = startNode;
        nodes = new HashSet<>();
        nodes.add(startNode);
    }

    public UTGNode getNode(UTGNode node) {
        for (UTGNode existingNode: nodes) {
            if (existingNode.equals(node)) {
                return existingNode;
            }
        }
        return null;
    }

    public void addNode(UTGNode node) {
        nodes.add(node);
    }

    public boolean hasNode(UTGNode node) {
        return nodes.contains(node);
    }
}
