package syntax.internal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.Constants;

/**
 * It might be LR(0) item or LALR(1) item according to the presence of lookahead symbol
 */

@EqualsAndHashCode
@AllArgsConstructor
public class LRItem {
    @Getter private Production production;
    @Getter private int dotPosition;
    @Getter private Symbol lookaheadSymbol;


    public LRItem(Production production, int dotPosition) {
        this.production = production;
        this.dotPosition = dotPosition;
    }

    public boolean isLALR1Item() {
        return lookaheadSymbol != null;
    }

    public boolean isReducible() {
        return dotPosition == production.rightSize();
    }

    public LRItem shift() {
        if (isReducible()) {
            return this;
        }
        return new LRItem(production, dotPosition +1);
    }

    public Symbol getSymbolAfterDot() {
        if (isReducible()) {
            return null;
        }

        return production.getRight().get(dotPosition);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(production.getLeft());
        sb.append(" -> ");

        for (int i =0; i<production.rightSize();i++) {
            if (dotPosition == i) {
                sb.append(Constants.DOT);
            }
            sb.append(" ");
            sb.append(production.getRight().get(i));
        }

        if (isReducible()) {
            sb.append(Constants.DOT);
        }

        return sb.toString();
    }


}
