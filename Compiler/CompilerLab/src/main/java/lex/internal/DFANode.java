package lex.internal;

import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

public class DFANode {
    private int state;
    private Map<Character, DFANode> edges;

    public DFANode(int state) {
        this.state = state;
        this.edges = new HashMap<>();
    }

    @Nullable
    public DFANode move(char c) {
        return edges.get(c);
    }

    public int getState() {
        return state;
    }

    public Map<Character, DFANode> getEdges() {
        return edges;
    }
}
