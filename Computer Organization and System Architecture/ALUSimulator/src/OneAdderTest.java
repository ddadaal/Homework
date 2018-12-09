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
public class OneAdderTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);
	
	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"0000", "00001"},
        		{"0010", "00011"},
        		{"1000", "01001"},
        		{"1111", "00000"},
        		{"0111", "11000"},
        });
    }
    
    private ALU alu = new ALU();
    private String operand;
    private String expected;
    
    public OneAdderTest(String operand, String expected) {
    	this.operand = operand;
    	this.expected = expected;
    }
    
	@Test
	public void test() {
		assertEquals(expected, alu.oneAdder(operand));
	}

}
