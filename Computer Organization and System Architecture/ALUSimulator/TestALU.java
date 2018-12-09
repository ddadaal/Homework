import static org.junit.Assert.*;

import org.junit.Test;

public class TestALU {

	ALU ALU = new ALU();
	
	@Test
	public void testNegation() {
		assertEquals("0110001",ALU.negation("1001110"));
	}
	
	@Test
	public void testLeftShift() {
		assertEquals("11101000",ALU.leftShift("01011101", 3));
	
	}
	
	@Test
	public void testLogRightShift(){
		assertEquals("00011101",ALU.logRightShift("11101001", 3));
		
	}
	
	@Test
	public void testAriRightShift(){
		assertEquals("11110011",ALU.ariRightShift("10011011", 3));
	}
	
	@Test
	public void testFullAdder1(){
		assertEquals("10",ALU.fullAdder('1', '0', '1'));
	}
	
	@Test
	public void testFullAdder2(){
		assertEquals("11",ALU.fullAdder('1', '1', '1'));
	}
	
	@Test
	public void testFullAdder3(){
		assertEquals("01",ALU.fullAdder('0', '0', '1'));
	}

	@Test
	public void testCLAAdder1(){
		assertEquals("01111",ALU.claAdder("1001", "0101", '1'));
	}
	
	@Test
	public void testCLAAdder2(){
		assertEquals("10110",ALU.claAdder("1001", "1101", '0'));
	}
	
	@Test
	public void testOneAdder1(){
		assertEquals("010011011",ALU.oneAdder("10011010"));
	}
	
	@Test
	public void testOneAdder2(){
		assertEquals("000000000",ALU.oneAdder("11111111"));
	}
	
	@Test
	public void testAdder1(){
		assertEquals("000001000",ALU.adder("0100", "0011", '1', 8));
	}
	
	@Test
	public void testAdder2(){
		assertEquals("000000000000000000000000001000111",ALU.adder("01100100", "11100011", '0', 32));
	}
	
	@Test
	public void testAdder3(){
		assertEquals("11110",ALU.adder("0110", "0111", '1', 4));
	}
	
	@Test
	public void testAdder4(){
		assertEquals("10001",ALU.adder("1000", "1000", '1', 4));
	}
	
	@Test
	public void testIntegerSubtration1(){
		assertEquals("01111",ALU.integerSubtraction("0100", "0101", 4));
	}
	
	@Test
	public void testIntegerSubtration2(){
		assertEquals("000000001",ALU.integerSubtraction("0100", "0011", 8));
	}

	@Test
	public void testIntegerSubtration3(){
		assertEquals("00110",ALU.integerSubtraction("0001", "1011", 4));
	}
	
	@Test
	public void testIntegerSubtration4(){
		assertEquals("00110",ALU.integerSubtraction("1110", "1000", 4));
	}
	
	@Test
	public void testIntegerSubtration5(){
		assertEquals("11110",ALU.integerSubtraction("0110", "1000", 4));
	}
	
	@Test
	public void testSignedAddition1(){
		assertEquals("001000",ALU.signedAddition("10011", "01011", 4));
	}
	
	@Test
	public void testSignedAddition2(){
		assertEquals("101001",ALU.signedAddition("01111", "01010", 4));
	}
	
	@Test
	public void testSignedAddition3(){
		assertEquals("0000001000",ALU.signedAddition("10011", "01011", 8));
	}
	
	@Test
	public void testSignedAddition4(){
		assertEquals("010011",ALU.signedAddition("0011", "1110", 4));
	}
	
	@Test
	public void testIntegerMultiplication1(){
		assertEquals("10000",ALU.integerMultiplication("1000", "0010", 4));
	}
	
	@Test
	public void testIntegerMultiplication2(){
		assertEquals("11110",ALU.integerMultiplication("1101", "0110", 4));
	}
	
	@Test
	public void testFloatRepresentation1(){
		assertEquals("00000000000000",ALU.floatRepresentation("0", 4, 9));
	}
	
	@Test
	public void testFloatRepresentation2(){
		assertEquals("100000000",ALU.floatRepresentation("-0.00048828125", 4, 4));
	}
	
	@Test
	public void testFloatRepresentation3(){
		assertEquals("0011010011001",ALU.floatRepresentation("0.3", 5, 7));
	}
	
	@Test
	public void testFloatRepresentation4(){
		assertEquals("00000100000000",ALU.floatRepresentation("0.0078125", 4, 9));
	}
	
	@Test
	public void testFloatRepresentation5(){
		assertEquals("000000001",ALU.floatRepresentation("0.0009765625", 4, 4));
	}
	
	@Test
	public void testFloatRepresentation6(){
		assertEquals("010110111",ALU.floatRepresentation("23.0", 4, 4));
	}
	
	@Test
	public void testFloatTrueValue1(){
		assertEquals("+Inf",ALU.floatTrueValue("011110000000000", 4, 10));
	}
	
	@Test
	public void testFloatTrueValue2(){
		assertEquals("0.0009765625",ALU.floatTrueValue("000000001", 4, 4));
	}
	
	
	@Test
	public void testFloatAddition1(){
		assertEquals("0001011011",ALU.floatAddition("000100110", "001011001", 4, 4, 4));
	}
	
	@Test
	public void testFloatAddition2(){
		assertEquals("0001010101",ALU.floatAddition("100101110", "001011001", 4, 4, 4));
	}
	
	@Test
	public void testFloatAddition3(){
		assertEquals("0001011100",ALU.floatAddition("000101110", "001011001", 4, 4, 4));
	}

	@Test
	public void testFloatAddition4(){
		assertEquals("0010001000",ALU.floatAddition("010101010", "110100100", 4, 4, 4));
	}
	
	@Test
	public void testFloatAddition5(){
		assertEquals("0111010100",ALU.floatAddition("111010110", "010011101", 4, 4, 4));
	}
	
	
//	@Test
//	public void testIntegerTrueFormMultiplication(){
//		assertEquals("0111010100000000",ALU.integerTrueFormMultiplication("10010", "11010", 8));
//	}
	
	@Test
	public void testFloatMultiplication1(){
		assertEquals("0100000011",ALU.floatMultiplication("111000110", "010101101", 4, 4));
	}
	
	@Test
	public void testIntegerDivision1(){
		assertEquals("100000000",ALU.integerDivision("1000", "11", 4));
	}
	
	@Test
	public void testIntegerDivision2(){
		assertEquals("NaN",ALU.integerDivision("1000", "000", 4));
	}
	
	@Test
	public void testIntegerDivision3(){
		assertEquals("000001111",ALU.integerDivision("1111", "0110", 4));
	}
	
	@Test
	public void testIntegerDivision4(){
		assertEquals("011101111",ALU.integerDivision("1001", "0011", 4));
	}
	
	@Test
	public void testIntegerTrueFormDivision(){
		assertEquals("01101100001000000",ALU.integerTrueFormDivision("1011","1101",8));
	}
	
	
	
}












