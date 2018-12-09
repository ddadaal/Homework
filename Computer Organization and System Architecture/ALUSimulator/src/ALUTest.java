import static org.junit.Assert.*;

import org.junit.Test;

public class ALUTest {

		
	
	@Test
	public void testIntegerRepresentation() {
		ALU alu = new ALU();
		assertEquals("00001001", alu.integerRepresentation("9", 8));
	}

	@Test
	public void testFloatRepresentation() {
		ALU alu = new ALU();
		assertEquals("01000001001101100000", alu.floatRepresentation("11.375", 8, 11));
	}

	@Test
	public void testIeee754() {
		ALU alu = new ALU();
		assertEquals("01000001001101100000000000000000", alu.ieee754("11.375", 32));
	}

	@Test
	public void testIntegerTrueValue() {
		ALU alu = new ALU();
		assertEquals("9", alu.integerTrueValue("00001001"));
	}

	@Test
	public void testFloatTrueValue() {
		ALU alu = new ALU();
		assertEquals("11.375", alu.floatTrueValue("01000001001101100000", 8, 11));
	}

	@Test
	public void testNegation() {
		ALU alu = new ALU();
		assertEquals("11110110", alu.negation("00001001"));
	}

	@Test
	public void testLeftShift() {
		ALU alu = new ALU();
		assertEquals("00100100", alu.leftShift("00001001", 2));
	}

	@Test
	public void testLogRightShift() {
		ALU alu = new ALU();
		assertEquals("00111101", alu.logRightShift("11110110", 2));
	}

	@Test
	public void testAriRightShift() {
		ALU alu = new ALU();
		assertEquals("11111101", alu.ariRightShift("11110110", 2));
	}

	@Test
	public void testFullAdder() {
		ALU alu = new ALU();
		assertEquals("10", alu.fullAdder('1', '1', '0'));
	}

	@Test
	public void testClaAdder() {
		ALU alu = new ALU();
		assertEquals("01011", alu.claAdder("1001", "0001", '1'));
	}

	@Test
	public void testOneAdder() {
		ALU alu = new ALU();
		assertEquals("000001010", alu.oneAdder("00001001"));
	}

	@Test
	public void testAdder() {
		ALU alu = new ALU();
		assertEquals("000000111", alu.adder("0100", "0011", '0', 8));
	}

	@Test
	public void testIntegerAddition() {
		ALU alu = new ALU();
		assertEquals("000000111", alu.integerAddition("0100", "0011", 8));
	}

	@Test
	public void testIntegerSubtraction() {
		ALU alu = new ALU();
		assertEquals("000000001", alu.integerSubtraction("0100", "0011", 8));
	}

	@Test
	public void testIntegerMultiplication() {
		ALU alu = new ALU();
		assertEquals("000001100", alu.integerMultiplication("0100", "0011", 8));
	}

	@Test
	public void testIntegerDivision() {
		ALU alu = new ALU();
		assertEquals("00000000100000001", alu.integerDivision("0100", "0011", 8));
	}

	@Test
	public void testSignedAddition() {
		ALU alu = new ALU();
		assertEquals("0100000111", alu.signedAddition("1100", "1011", 8));
	}

	@Test
	public void testFloatAddition() {
		ALU alu = new ALU();
		assertEquals("000111111101110000", alu.floatAddition("00111111010100000", "00111111001000000", 8, 8, 4));
	}

	@Test
	public void testFloatSubtraction() {
		ALU alu = new ALU();
		assertEquals("000111110010000000", alu.floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 4));
	}

	@Test
	public void testFloatMultiplication() {
		ALU alu = new ALU();
		assertEquals("000111110011000000", alu.floatMultiplication("00111110111000000", "00111111000000000", 8, 8));
	}

	@Test
	public void testFloatDivision() {
		ALU alu = new ALU();
		assertEquals("000111111011000000", alu.floatDivision("00111110111000000", "00111111000000000", 8, 8));
	}

}
