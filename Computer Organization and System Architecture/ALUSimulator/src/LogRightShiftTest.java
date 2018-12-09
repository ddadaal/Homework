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
public class LogRightShiftTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"00000000", 0, "00000000"},
        		{"00001001", 2, "00000010"},
        		{"11001001", 2, "00110010"},
        		{"11111111", 2, "00111111"},
        		{"11111111", 9, "00000000"},
        });
    }

    private ALU alu = new ALU();
    private String operand;
    private int n;
    private String expected;

    public LogRightShiftTest(String operand, int n, String expected) {
    	this.operand = operand;
    	this.n = n;
    	this.expected = expected;
    }

	@Test
	public void test() {
		assertEquals(expected, alu.logRightShift(operand,n));
	}

}
