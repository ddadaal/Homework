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
public class IntegerRepresentationTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);
	
	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"1", 4, "0001"},
        		{"-1", 4, "1111"},
        		{"9", 8, "00001001"},
        		{"-9", 8, "11110111"},
        		{"0", 8, "00000000"},
        });
    }
    
    private ALU alu = new ALU();
    private String number;
    private int length;
    private String expected;
    
    public IntegerRepresentationTest(String number, int length, String expected) {
    	this.number = number;
    	this.length = length;
    	this.expected = expected;
    }
    
	@Test
	public void test() {
		assertEquals(expected, alu.integerRepresentation(number,length));
	}

}
