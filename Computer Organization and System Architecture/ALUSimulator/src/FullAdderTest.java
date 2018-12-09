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
public class FullAdderTest {

	//@Rule
	//public TestLogger tl = new TestLogger();

	@Rule
    public Timeout globalTimeout = Timeout.seconds(10);

	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
        		{'0', '0', '0', "00"},
        		{'0', '0', '1', "01"},
        		{'0', '1', '0', "01"},
        		{'1', '1', '1', "11"}
        });
    }

    private ALU alu = new ALU();
    private char x;
    private char y;
    private char c;
    private String expected;

    public FullAdderTest(char x, char y, char c, String expected) {
    	this.x = x;
    	this.y = y;
    	this.c = c;
    	this.expected = expected;
    }

	@Test
	public void test() {
		assertEquals(expected, alu.fullAdder(x, y, c));
	}

}
