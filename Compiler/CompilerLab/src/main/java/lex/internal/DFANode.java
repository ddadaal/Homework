package lex.internal;

import com.sun.istack.internal.Nullable;
import lex.token.TokenType;
import lombok.Getter;
import lombok.Setter;
import util.Constants;

import java.util.*;

public class DFANode {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DFANode)) return false;
        DFANode dfaNode = (DFANode) o;

        // if two DFA note contains the same NFA nodes, then they are equal
        if (dfaNode.nfaNodes.size() != this.nfaNodes.size()) {
            return false;
        }

        return dfaNode.nfaNodes.containsAll(this.nfaNodes)
            && this.nfaNodes.containsAll(dfaNode.nfaNodes)
            ;
    }

    @Getter private List<NFANode> nfaNodes;

    @Getter private Map<Character, DFANode> edges;

    @Getter @Setter
    private List<TokenType> endStateTokenTypes;

    public boolean isEndState() {
        return endStateTokenTypes != null && endStateTokenTypes.size() > 0;
    }

    public DFANode() {
        this.edges = new HashMap<>();
    }

    public DFANode(List<NFANode> nfaNodes) {
        this();
        this.nfaNodes = nfaNodes;
    }

    public Set<Character> getEdgeCharset() {
        Set<Character> result = new HashSet<>();

        for (NFANode node: nfaNodes) {
            for (char c : node.getEdges().keySet()) {
                if (c != Constants.EPSILON) {
                    result.add(c);
                }
            }
        }

        return result;
    }



    @Nullable
    public DFANode getTargetOfEdge(char c) {
        return edges.get(c);
    }

    public void setTarget(char c, DFANode target) {
        edges.put(c, target);
    }
}
