package lex.internal;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NFANode {
    private int state;
    private Map<Character, List<DFANode>> edges;

    public NFANode(int state) {
        this.state = state;
        this.edges = new HashMap<>();
    }

    @NotNull
    public List<DFANode> move(char c) {
        List<DFANode> r = edges.get(c);
        if (r == null) {
            List<DFANode> r1 = new ArrayList<>();
            edges.put(c, r1);
            return r1;
        } else {
            return r;
        }
    }
}
