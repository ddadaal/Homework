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
public class SignedAdditionTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);
	
	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"0001", "0010", 4, "000011"},
        		{"0001", "1001", 4, "000000"},
        		{"1001", "0001", 4, "010000"},
        		{"00111", "01001", 4, "100000"},
        		{"10111", "11001", 4, "110000"},
        		{"1100", "1011", 8, "0100000111"},
        });
    }
    
    private ALU alu = new ALU();
    private String operand1;
    private String operand2;
    private int length;
    private String expected;
    
    public SignedAdditionTest(String operand1, String operand2, int length, String expected) {
    	this.operand1 = operand1;
    	this.operand2 = operand2;
    	this.length = length;
    	this.expected = expected;
    }
    
	@Test
	public void test() {
		assertEquals(expected, alu.signedAddition(operand1, operand2, length));
	}

}
