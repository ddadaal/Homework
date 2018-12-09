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
public class ClaAdderTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"0000", "0000", '0', "00000"},
        		{"0000", "0000", '1', "00001"},
        		{"1000", "1000", '0', "10000"},
        		{"1001", "0001", '1', "01011"}
        });
    }

    private ALU alu = new ALU();
    private String operand1;
    private String operand2;
    private char c;
    private String expected;

    public ClaAdderTest(String operand1, String operand2, char c, String expected) {
    	this.operand1 = operand1;
    	this.operand2 = operand2;
    	this.c = c;
    	this.expected = expected;
    }

	@Test
	public void test() {
		assertEquals(expected, alu.claAdder(operand1, operand2, c));
	}

}
