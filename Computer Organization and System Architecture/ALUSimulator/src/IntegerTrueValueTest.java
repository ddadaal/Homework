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
public class IntegerTrueValueTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);
	
	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"0001", "1"},
        		{"1111", "-1"},
        		{"00001001", "9"},
        		{"11110111", "-9"},
        		{"00000000", "0"},
        });
    }
    
    private ALU alu = new ALU();
    private String operand;
    private String expected;
    
    public IntegerTrueValueTest(String operand, String expected) {
    	this.operand = operand;
    	this.expected = expected;
    }
    
	@Test
	public void test() {
		assertEquals(expected, alu.integerTrueValue(operand));
	}

}
