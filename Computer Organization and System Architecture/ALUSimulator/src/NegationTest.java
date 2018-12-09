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
public class NegationTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"0", "1"},
        		{"1", "0"},
        		{"01", "10"},
        		{"00000000", "11111111"},
        		{"11111111", "00000000"},
        		{"00000001", "11111110"},
        		{"01010101", "10101010"}
        });
    }

    private ALU alu = new ALU();
    private String operand;
    private String expected;

    public NegationTest(String operand, String expected) {
    	this.operand = operand;
    	this.expected = expected;
    }

	@Test
	public void test() {
		assertEquals(expected, alu.negation(operand));
	}

}
