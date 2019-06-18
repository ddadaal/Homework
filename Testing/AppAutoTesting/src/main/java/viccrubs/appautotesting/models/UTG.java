package viccrubs.appautotesting.models;

import lombok.Data;
import lombok.Getter;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
