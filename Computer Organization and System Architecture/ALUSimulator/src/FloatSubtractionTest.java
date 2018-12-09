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
public class FloatSubtractionTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"001100000", "000000000", 4, 4, 4, "0001100000"},
        		{"100000000", "001100000", 4, 4, 4, "0101100000"},
        		{"001100000", "001100000", 4, 4, 4, "0000000000"},
        		{"00111111010100000", "00111111001000000", 8, 8, 8, "000111110010000000"},
        });
    }

    private ALU alu = new ALU();
    private String operand1;
    private String operand2;
    private int eLength;
    private int sLength;
    private int gLength;
    private String expected;

    public FloatSubtractionTest(String operand1, String operand2, int eLength, int sLength, int gLength, String expected) {
    	this.operand1 = operand1;
    	this.operand2 = operand2;
    	this.eLength = eLength;
    	this.sLength = sLength;
    	this.gLength = gLength;
    	this.expected = expected;
    }

	@Test
	public void test() {
		assertEquals(expected, alu.floatSubtraction(operand1, operand2, eLength, sLength, gLength));
	}

}
