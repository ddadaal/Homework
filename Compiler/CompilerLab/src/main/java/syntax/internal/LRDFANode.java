package syntax.internal;

import lombok.Getter;
import lombok.experimental.ExtensionMethod;
import util.CollectionExtensions;

import java.util.*;

public class LRDFANode {

    @Getter private List<LRItem> kernel;
    @Getter private List<LRItem> lrItems;
    @Getter private Map<Symbol, LRDFANode> edges;

    public LRDFANode() {
        this.edges = new HashMap<>();
    }

    public LRDFANode(List<LRItem> kernel, List<LRItem> lrItems) {
        this();
        this.kernel = kernel;
        this.lrItems = lrItems;
    }

    public Set<Symbol> getMovableSymbols() {
        Set<Symbol> result = new HashSet<>();
        for (LRItem lrItem : lrItems) {
            Symbol symbol = lrItem.getSymbolAfterDot();
            if (symbol != null) {
                result.add(symbol);
            }
        }

        return result;
    }

    public void setEdge(Symbol symbol, LRDFANode node) {
        edges.put(symbol, node);
    }

    public boolean isEndState() {
        return lrItems.stream().anyMatch(LRItem::isReducible);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LRDFANode)) return false;
        LRDFANode lrdfaNode = (LRDFANode) o;

        return CollectionExtensions.containsSameElements(kernel, lrdfaNode.kernel);
    }


    @Override
    public int hashCode() {
        return Objects.hash(lrItems);
    }



}
