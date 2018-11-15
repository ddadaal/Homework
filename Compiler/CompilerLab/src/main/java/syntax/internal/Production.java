package syntax.internal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
public class Production {
    @Getter private ProductionSymbol left;
    @Getter private List<ProductionSymbol> right;

    public int rightSize() {
        return right.size();
    }

    public Production(ProductionSymbol left, ProductionSymbol... right) {
        this(left, Arrays.asList(right));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(left.toString());
        sb.append(" -> ");
        for (ProductionSymbol rightSymbol: right) {
            sb.append(rightSymbol.toString());
        }

        return sb.toString();

    }
}
