package syntax.internal;

import lombok.Getter;
import lombok.Setter;
import util.CollectionUtil;

import java.util.*;
import java.util.stream.Collectors;

public class LRDFANode {

    @Getter @Setter
    private List<LRItem> kernel;
    @Getter @Setter private List<LRItem> lrItems;
    @Getter private Map<Symbol, LRDFANode> edges;

    public LRDFANode() {
        this.edges = new HashMap<>();
    }

    public LRDFANode(List<LRItem> kernel, List<LRItem> lrItems) {
        this();
        this.kernel = kernel;
        this.lrItems = lrItems;
    }

    public List<Symbol> getMovableSymbols() {
        return lrItems.stream().map(LRItem::getSymbolAfterDot).distinct().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public LRDFANode goTo(Symbol symbol) {
        return edges.get(symbol);
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

        return CollectionUtil.containsSameElements(kernel, lrdfaNode.kernel);
    }


    @Override
    public int hashCode() {
        return Objects.hash(lrItems);
    }



}
