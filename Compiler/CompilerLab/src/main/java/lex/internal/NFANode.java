package lex.internal;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import lex.token.TokenType;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


public class NFANode {
    @Getter private Map<Character, List<NFANode>> edges;

    @Getter @Setter private TokenType endStateToken;

    public NFANode() {
        this.edges = new HashMap<>();
    }

    public void addEdges(char c, NFANode... targets) {
        List<NFANode> list = edges.get(c);
        if (list == null) {
            edges.put(c, new ArrayList<>(Arrays.asList(targets)));
        } else {
            list.addAll(Arrays.asList(targets));
        }
    }

    @NotNull
    public List<NFANode> move(char c) {
        List<NFANode> r = edges.get(c);
        if (r == null) {
            return new ArrayList<>();
        } else {
            return r;
        }
    }
}
