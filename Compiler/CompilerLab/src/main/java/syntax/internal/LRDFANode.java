package syntax.internal;

import lombok.Getter;
import lombok.experimental.ExtensionMethod;
import util.CollectionExtensions;

import java.util.*;

@ExtensionMethod(CollectionExtensions.class)
public class LRDFANode {

    @Getter private List<LRItem> lrItems;
    @Getter private Map<ProductionSymbol, LRDFANode> edges;

    public LRDFANode() {
        this.edges = new HashMap<>();
    }

    public LRDFANode(List<LRItem> lrItems) {
        this();
        this.lrItems = lrItems;
    }

    public Set<ProductionSymbol> getMovableSymbols() {
        Set<ProductionSymbol> result = new HashSet<>();
        for (LRItem lrItem : lrItems) {
            ProductionSymbol symbol = lrItem.getSymbolAfterDot();
            if (symbol != null) {
                result.add(symbol);
            }
        }

        return result;
    }

    public void setEdge(ProductionSymbol symbol, LRDFANode node) {
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

        return CollectionExtensions.containsSameElements(lrItems, lrdfaNode.lrItems);
    }


    @Override
    public int hashCode() {
        return Objects.hash(lrItems);
    }



}
