package syntax.internal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import util.Constants;

import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode
@AllArgsConstructor
public class Production {
    @Getter private Symbol left;
    @Getter private List<Symbol> right;

    public int rightSize() {
        return right.size();
    }

    public Production(Symbol left, Symbol... right) {
        this(left, Arrays.asList(right));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(left.toString());
        sb.append(" ->");

        if (rightSize() == 0) {
            sb.append(" ").append(Constants.EPSILON);
        } else {
            for (Symbol rightSymbol: right) {
                sb.append(" ");
                sb.append(rightSymbol);
            }
        }
        return sb.toString();

    }
}
