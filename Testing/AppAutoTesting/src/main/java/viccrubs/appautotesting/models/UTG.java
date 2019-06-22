package viccrubs.appautotesting.models;

import lombok.Getter;
import lombok.val;

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

    public List<UTGEdge> getEdges() {
        val edges = new ArrayList<UTGEdge>();
        for (val node: nodes) {
            edges.addAll(node.getEdges());
        }
        return edges;
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

    public List<UTGEdge> findPath(UTGNode startNode, UTGNode endNode) {
        val path = new ArrayList<UTGEdge>();
        findPathRec(startNode, endNode, path, new HashSet<>());
        return path;
    }

    private boolean findPathRec(UTGNode node, UTGNode target, List<UTGEdge> result, Set<UTGNode> intermediateNodes) {
        if (node.equals(target)) {
            // reached target. return
            return true;
        }

        // 看从这个点的下一个的哪一点可以到目标
        for (val edge: node.getEdges()) {

            // 中间点
            val intermediateNote = edge.getEndNode();

            // 看是否成环
            if (intermediateNodes.contains(intermediateNote)) {
                continue;
            }

            // 尝试增加这条边和中间点
            result.add(edge);
            intermediateNodes.add(intermediateNote);

            // 继续
            if (findPathRec(intermediateNote, target, result, intermediateNodes)) {
                // 从这个edge可以到，返回true
                return true;
            }

            // 不能到，删掉这个edge和中间点
            result.remove(result.size()-1);
            intermediateNodes.remove(intermediateNote);
        }

        // 这个点并不能到目标
        return false;

    }
}
