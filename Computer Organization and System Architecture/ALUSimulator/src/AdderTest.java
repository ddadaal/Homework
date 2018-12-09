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
public class AdderTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"0100", "0011", '0', 8, "000000111"},
        		{"1111", "0001", '0', 12, "0000000000000"},
        		{"011111111111", "0001", '0', 12, "1100000000000"}
        });
    }

    private ALU alu = new ALU();
    private String operand1;
    private String operand2;
    private char c;
    private int length;
    private String expected;

    public AdderTest(String operand1, String operand2, char c, int length, String expected) {
    	this.operand1 = operand1;
    	this.operand2 = operand2;
    	this.c = c;
    	this.length = length;
    	this.expected = expected;
    }

	@Test
	public void test() {
		assertEquals(expected, alu.adder(operand1, operand2, c, length));
	}

}
