import static org.junit.Assert.*;

import org.junit.Test;

public class ALUTest2 {
	
     ALU alu = new ALU();
	//

	
	@Test
	public void test2_1() {
		String s;
		s=alu.integerRepresentation("-1", 1);
		assertEquals("1",s);
	}
	@Test
	public void test2_2(){
		String s=alu.integerRepresentation("-1",2);
		assertEquals("11", s);
	}
	@Test
	public void test2_3(){
		String s=alu.integerRepresentation("-1",3);
		assertEquals("111", s);
	}
	@Test
	public void test2_4(){
		String s=alu.integerRepresentation("-8", 4);
		assertEquals("1000", s);
	}
	@Test
	public void test2_5(){
		assertEquals("1000000000000000", alu.integerRepresentation("-32768", 16));
	}
	@Test
	public void test2_6(){
		assertEquals("1000000000000001", alu.integerRepresentation("-32767", 16));
	}
	@Test
	public void test2_7(){
		assertEquals("0111111111111111", alu.integerRepresentation("32767", 16));
	}
	@Test
	public void test2_8(){
		assertEquals("10000000000000000000000000000000", alu.integerRepresentation("-2147483648",32));
	}
	@Test
	public void test2_9(){
		assertEquals("01111111111111111111111111111111", alu.integerRepresentation("2147483647",32));
	}
	@Test
	public void test2_10(){
		//TODO Unknown ways
		assertEquals("1000000000000000000000000000000000000000000000000000000000000000",
				alu.integerRepresentation("-9223372036854775808", 64));
	}
	@Test
	public void test2_11(){
		assertEquals("1111111111111111111", alu.integerRepresentation("-1", 19));
	}
	
}
