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
public class FloatRepresentationTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{"+Inf", 4, 4, "011110000"},
        		{"-0.0", 4, 4, "100000000"},
        		{"0.5", 4, 4, "001100000"},
        		{"-11.375", 8, 11, "11000001001101100000"},
        		{"0.0069580078125", 4, 8, "0000001110010"},
        		{"-0.0069580078125", 4, 8, "1000001110010"},
        });
    }

    private ALU alu = new ALU();
    private String number;
    private int eLength;
    private int sLength;
    private String expected;

    public FloatRepresentationTest(String number, int eLength, int sLength, String expected) {
    	this.number = number;
    	this.eLength = eLength;
    	this.sLength = sLength;
    	this.expected = expected;
    }

	@Test
	public void test() {
		assertEquals(expected, alu.floatRepresentation(number,eLength,sLength));
	}

}
