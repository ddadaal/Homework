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
public class IntegerDivisionTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"00000000", "0000", 8, "NaN"},
        		{"0100", "0011", 12, "0000000000001000000000001"},
        		{"0111", "0011", 4, "000100001"},
        		{"0111", "1101", 4, "011100001"},
        		{"0110", "1101", 4, "011100000"},
        		{"1010", "0011", 4, "011100000"},
        });
    }

    private ALU alu = new ALU();
    private String operand1;
    private String operand2;
    private int length;
    private String expected;

    public IntegerDivisionTest(String operand1, String operand2, int length, String expected) {
    	this.operand1 = operand1;
    	this.operand2 = operand2;
    	this.length = length;
    	this.expected = expected;
    }

	@Test
	public void test() {
		assertEquals(expected, alu.integerDivision(operand1, operand2, length));
	}

}
