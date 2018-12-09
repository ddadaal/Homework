import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class FloatTrueValueTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"011110000", 4, 4, "+Inf"},
        		{"111110010", 4, 4, "NaN"},
        		{"000000000", 4, 4, "0.0"},
        		{"101100000", 4, 4, "-0.5"},
        		{"00111111100000000000", 8, 11, "1.0"},
        		{"1000001110010", 4, 8, "-0.0069580078125"},
        });
    }

    private ALU alu = new ALU();
    private String operand;
    private int eLength;
    private int sLength;
    private String expected;

    public FloatTrueValueTest(String operand, int eLength, int sLength, String expected) {
    	this.operand = operand;
    	this.eLength = eLength;
    	this.sLength = sLength;
    	this.expected = expected;
    }

	@Test
	public void test() {
		assertEquals(expected, alu.floatTrueValue(operand,eLength,sLength));
	}

}
