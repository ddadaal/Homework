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
public class IntegerAdditionTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"00000000", "0000", 8, "000000000"},
        		{"0100", "0011", 8, "000000111"},
        		{"1100", "0011", 8, "011111111"},
        		{"0111", "0001", 4, "11000"},
        		{"1001", "1111", 4, "01000"},
        });
    }

    private ALU alu = new ALU();
    private String operand1;
    private String operand2;
    private int length;
    private String expected;

    public IntegerAdditionTest(String operand1, String operand2, int length, String expected) {
    	this.operand1 = operand1;
    	this.operand2 = operand2;
    	this.length = length;
    	this.expected = expected;
    }

	@Test
	public void test() {
		assertEquals(expected, alu.integerAddition(operand1, operand2, length));
	}

}
