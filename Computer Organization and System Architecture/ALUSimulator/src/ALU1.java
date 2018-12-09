import static org.junit.Assert.*;

import org.junit.Test;




	public class ALU1 {
		ALU alu = new ALU();
		
		@Test
		public void test2() {
			assertEquals("00000",alu.integerRepresentation("0", 5));
			assertEquals("11111",alu.integerRepresentation("-1", 5));
			assertEquals("00101",alu.integerRepresentation("5", 5));
			assertEquals("11111001",alu.integerRepresentation("-7", 8));
		}
		@Test
		public void test5() {
			assertEquals("0",alu.integerTrueValue("0"));
		    assertEquals("17",alu.integerTrueValue("010001"));
			assertEquals("-7",alu.integerTrueValue("11111001"));
			assertEquals("3",alu.integerTrueValue("0011"));
			assertEquals("-1",alu.integerTrueValue("1"));
			assertEquals("-1",alu.integerTrueValue("111111"));
		}
		
		
		@Test
		public void test7() {
			assertEquals("00000",alu.negation("11111"));
			assertEquals("11111",alu.negation("00000"));
			assertEquals("00101",alu.negation("11010"));
			assertEquals("11111001",alu.negation("00000110"));
		}
		@Test
		public void test8() {
			assertEquals("00000",alu.leftShift("01111", 5));
			assertEquals("11111",alu.leftShift("11111", 0));
			assertEquals("00100",alu.leftShift("00010", 1));
			assertEquals("11111000",alu.leftShift("01011111", 3));
		}
		@Test
		public void test9() {
			assertEquals("110111001",alu.ariRightShift("101110011", 1));
			assertEquals("11111",alu.ariRightShift("11111", 0));
			assertEquals("00001",alu.ariRightShift("00010", 1));
			assertEquals("00000",alu.ariRightShift("00001", 3));
		}
		@Test
		public void test10() {
			assertEquals("010111001",alu.logRightShift("101110011", 1));
			assertEquals("11111",alu.logRightShift("11111", 0));
			assertEquals("00001",alu.logRightShift("00010", 1));
			assertEquals("00000",alu.logRightShift("00001", 3));
			assertEquals("00010",alu.logRightShift("10001", 3));
		}
		@Test
		public void test11() {
			assertEquals("00",alu.fullAdder('0', '0', '0'));
			assertEquals("01",alu.fullAdder('1', '0', '0'));
			assertEquals("10",alu.fullAdder('1', '1', '0'));
			assertEquals("01",alu.fullAdder('0', '0', '1'));
			assertEquals("10",alu.fullAdder('1', '0', '1'));
			assertEquals("11",alu.fullAdder('1', '1', '1'));
		}
		/*@Test
		 * 瑕佹祴claAdder鐨勮瘽瑕佽嚜宸卞啓4浣嶇殑娴嬭瘯鐢ㄤ緥
		public void test12() {
			assertEquals("0001010010",alu.claAdder("00001100", "00011100", '1'));
			assertEquals("0001101000",alu.claAdder("00100001","00010010",'1'));
			assertEquals("1000000001",alu.claAdder("10000000","10000000",'0'));
		}*/
		@Test
		public void test13() {
			assertEquals("11010",alu.integerAddition("0111","0011",4));
			assertEquals("00100",alu.integerAddition("0100","0000",4));
			assertEquals("10101",alu.integerAddition("1000","1101",4));
		}
		@Test
		public void test14() {
			assertEquals("11011",alu.integerSubtraction("0101","1010",4));
			assertEquals("000001011",alu.integerSubtraction("0101","1010",8));
			assertEquals("00100",alu.integerSubtraction("0111","0011",4));
			assertEquals("00100",alu.integerSubtraction("0100","0000",4));
			assertEquals("01011",alu.integerSubtraction("1000","1101",4));
			assertEquals("10011",alu.integerSubtraction("1001","0110",4));
		}
		@Test
		public void test15() {
			assertEquals("00110",alu.integerMultiplication("0011", "0010", 4));
			assertEquals("01000",alu.integerMultiplication("1110", "0100", 4));
			assertEquals("11000",alu.integerMultiplication("0100", "0010", 4));
			assertEquals("00110",alu.integerMultiplication("1101", "1110", 4));
		}
		@Test
		public void test16() {
			assertEquals("000100001",alu.integerDivision("0111", "0011", 4));
			assertEquals("011100001",alu.integerDivision("0111", "1101", 4));
			assertEquals("011101111",alu.integerDivision("1001", "0011", 4));
			assertEquals("000101111",alu.integerDivision("1001", "1101", 4));
			assertEquals("000110000",alu.integerDivision("0110", "0010", 4));
		  /*assertEquals("000110000",alu.integerDivision("1010", "1110", 4));
			assertEquals("011010000",alu.integerDivision("0110", "1110", 4));
			assertEquals("011010000",alu.integerDivision("1010", "0010", 4));*/
		}
		@Test
		public void test3() {
			assertEquals("11000000111000000000000000000000",alu.floatRepresentation("-7",8,23));
			assertEquals("10111111110000000000000000000000",alu.floatRepresentation("-1.5", 8, 23));
		    assertEquals("01000011110000000001000010100011",alu.floatRepresentation("384.13", 8,23));          
			assertEquals("00111111010000000000000000000000",alu.floatRepresentation("0.75", 8,23));
			assertEquals("11000010100000101000000000000000",alu.floatRepresentation("-65.25", 8,23));
			assertEquals("0100110101100101",alu.floatRepresentation("217.36", 6,9));
			assertEquals("0100010000110010",alu.floatRepresentation("8.79", 6,9));
			assertEquals("0100101010101101",alu.floatRepresentation("85.643", 6,9));
			assertEquals("0011101011110101",alu.floatRepresentation("0.37", 6,9));
			assertEquals("0100100100111010",alu.floatRepresentation("51.658", 6,9));
			assertEquals("0100100100111001",alu.floatRepresentation("51.607", 6,9));
			assertEquals("0000000000000000",alu.floatRepresentation("0", 6,9));
			assertEquals("00000000000000000000000000000000",alu.floatRepresentation("0", 8,23));
			assertEquals("01111111100000000000000000000000",alu.floatRepresentation(Double.MAX_VALUE+"", 8,23));
			assertEquals("01111111100000000000000000000000",alu.floatRepresentation(Double.MAX_VALUE/2+"", 8,23));
			assertEquals("01111111100000000000000000000000",alu.floatRepresentation(Double.MAX_VALUE/100+"", 8,23));
			assertEquals("11111111100000000000000000000000",alu.floatRepresentation(-Double.MAX_VALUE+"",8,23));
			assertEquals("01111111100000000000000000000001",alu.floatRepresentation("NaN", 8,23));
			assertEquals("00000000100000000000000000000000",alu.floatRepresentation(Float.MIN_NORMAL+"", 8,23));
			assertEquals("00000000000000000000000000000010",alu.floatRepresentation(Float.MIN_VALUE*2+"", 8,23));
		}///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//@Test
		public void test4() {
//			assertEquals("0"
//					+ "11111111111"
//					+ "0000000000000000000000000000000000000000000000000001",
//					alu.ieee754("NaN", 64));
			//assertEquals("0"
				//+ "11111111110"
					//+ "1111111111111111111111111111111111111111111111111111",
					//alu.ieee754((Double.MAX_VALUE-1)+"", 64));
			//assertEquals("0"
				//	+ "00000000001"
					//+ "0000000000000000000000000000000000000000000000000000",
					//alu.ieee754(Double.MIN_NORMAL+"", 64));
			//assertEquals("0"
				//	+ "00000000000"
					//+ "0000000000000000000000000000000000000000000000000001",
			//		alu.ieee754(Double.MIN_VALUE+"", 64));
		}
		@Test
	public void test6() {
			assertEquals("0",alu.floatTrueValue(
					"0"
					+ "00000000"
					+ "00000000000000000000000", 8, 23));
			assertEquals("0",alu.floatTrueValue(
					"1"
					+ "00000000"
					+ "00000000000000000000000", 8, 23));
			assertEquals("+Inf",alu.floatTrueValue(
					"0"
					+ "11111111"
					+ "00000000000000000000000", 8, 23));
			assertEquals("-Inf",alu.floatTrueValue(
					"1"
					+ "11111111"
					+ "00000000000000000000000", 8, 23));
			assertEquals("NaN",alu.floatTrueValue(
					"0"
					+ "11111111"
					+ "00000000000000000000100",  8, 23));
			assertEquals("NaN",alu.floatTrueValue(
					"1"
					+ "11111111"
					+ "00000001111000000000001",  8, 23));
			assertEquals("384.1300048828125",alu.floatTrueValue(
					"0"
					+ "10000111"
					+ "10000000001000010100100", 8, 23));
			assertEquals("85.625",alu.floatTrueValue(
							"0"
							+ "100101"
							+ "010101101",6,9));
			assertEquals("-65.25",alu.floatTrueValue(
					"1"
					+ "10000101"
					+ "00000101000000000000000", 8, 23));
		}
		@Test
		public void test17() {
			//缂佸牅绨�瑰本鍨氭禍鍡礉婢额亜绱戣箛鍐х啊o(*閿燂絺鏌﹂敓锟�*)閵夛拷
//			assertEquals(
//					"0"+alu.floatRepresentation("0",8,23),
//					alu.floatAddition(
//							alu.floatRepresentation("-5",8,23), 
//							alu.floatRepresentation("5",8,23)
//							,8,23, 0));
//			assertEquals(
//					"0"+alu.floatRepresentation("6",6,9),
//					alu.floatAddition(
//							alu.floatRepresentation("2.25",6,9), 
//							alu.floatRepresentation("3.75",6,9)
//							, 6,9, 0));
//			assertEquals(
//					"0"+alu.floatRepresentation("1.4",8,23),
//					alu.floatAddition(
//							alu.floatRepresentation("1.1",8,23), 
//							alu.floatRepresentation("0.3",8,23)
//							,8,23,4));
//			assertEquals(
//					alu.floatRepresentation("7.368", 8, 23)+"0",
//					alu.floatAddition(
//							alu.floatRepresentation("1.256", 8, 23), 
//							alu.floatRepresentation("6.112", 8, 23)
//							, 8, 23, 0));
//			assertEquals(
//					alu.floatRepresentation("-7.368", 8, 23)+"0",
//					alu.floatAddition(
//							alu.floatRepresentation("-1.256", 8, 23), 
//							alu.floatRepresentation("-6.112", 8, 23)
//							, 8, 23, 0));
//			assertEquals(
//					"0"+alu.floatRepresentation("-64.5",8,23),
//					alu.floatAddition(
//							alu.floatRepresentation("0.75 ",8,23), 
//							alu.floatRepresentation("-65.25",8,23)
//							,8,23,0));
//			assertEquals(
//					"0"+alu.floatRepresentation("0.9375",8,23),
//					alu.floatAddition(
//							alu.floatRepresentation("0.5 ",8,23), 
//							alu.floatRepresentation("0.4375",8,23)
//							,8,23, 0));
//			assertEquals(
//					"000111111101110000",
//					alu.floatAddition(
//							"00111111010100000", 
//							"00111111001000000"
//							,8,8, 0));
			/*assertEquals(
					"0"+alu.floatRepresentation("0.0625",8,23),
					alu.floatAddition(
							alu.floatRepresentation("0.5 ",8,23), 
							alu.floatRepresentation("-0.4375",8,23)
							,8,23,0));*/
			assertEquals(
					"0"+alu.floatRepresentation("-63.5", 8, 23),
					alu.floatAddition(
							alu.floatRepresentation("1.75", 8, 23), 
							alu.floatRepresentation("-65.25", 8, 23)
							, 8, 23, 0));
			assertEquals(
					"0"+alu.floatRepresentation("85.875",6,9),
					alu.floatAddition(
							alu.floatRepresentation("85.643", 6,9), 
							alu.floatRepresentation("0.37",6,9)
							,6,9, 0));
			assertEquals(
					"0"+alu.floatRepresentation("226",6,9),
					alu.floatAddition(
							alu.floatRepresentation("217.36", 6,9), 
							alu.floatRepresentation("8.79", 6,9)
							,6,9, 6));
			assertEquals(
					"0"+alu.floatRepresentation("208.5", 6,9),
					alu.floatAddition(
							alu.floatRepresentation("217.36", 6,9), 
							alu.floatRepresentation("-8.79", 6,9)
							, 6,9, 0));
			assertEquals(
					"0"+alu.floatRepresentation("208.49", 6,9),
					alu.floatAddition(
							alu.floatRepresentation("217.36", 6,9), 
							alu.floatRepresentation("-8.79", 6,9)
							, 6,9, 20));
			assertEquals(
					"0"+alu.floatRepresentation("0.0625", 6,9),
					alu.floatAddition(
							alu.floatRepresentation("51.658", 6,9), 
							alu.floatRepresentation("-51.607", 6,9)
							, 6,9, 0));
			/*
			assertEquals(
					alu.floatRepresentation(Float.MAX_VALUE+"", 8, 23)+"0",
					alu.floatAddition(
							alu.floatRepresentation(Float.MAX_VALUE+"", 8, 23), 
							alu.floatRepresentation("-51.607", 8, 23)
							, 8, 23,0));
			assertEquals(
					alu.floatRepresentation(Float.MAX_VALUE+"", 8, 23)+"0",
					alu.floatAddition(
							alu.floatRepresentation(Float.MAX_VALUE+"", 8, 23), 
							alu.floatRepresentation("51.607", 8, 23)
							, 8, 23,0));
			assertEquals(
					"0"	+ "00000000"
							+ "000"
							+ "0000"
							+ "0000"
							+ "0000"
							+ "0000"
							+ "0001"+"1",
					alu.floatAddition(
							alu.floatRepresentation(Float.MIN_VALUE*2+"", 8, 23), 
							"1"	+ "00000000"
									+ "000"
									+ "0000"
									+ "0000"
									+ "0000"
									+ "0000"
									+ "0001"
							, 8, 23,0));*/
							
		}
		
		@Test
		public void lian(){
			assertEquals("000010000010100", alu.floatAddition("00001111110000", "00000011100000", 5, 8, 6));
		}
		@Test
		public void test18() {
			assertEquals(
					"0"+alu.floatRepresentation("3", 8,23),
					alu.floatSubtraction(
							alu.floatRepresentation("1",8,23), 
							alu.floatRepresentation("-2",8,23)
							,8,23, 0));
			assertEquals(
					"0"+alu.floatRepresentation("0.9375",8,23),
					alu.floatSubtraction(
							alu.floatRepresentation("0.5 ",8,23), 
							alu.floatRepresentation("-0.4375",8,23)
							,8,23, 0));
			assertEquals(
					"0"+alu.floatRepresentation("0.0625",8,23),
					alu.floatSubtraction(
							alu.floatRepresentation("0.5 ",8,23), 
							alu.floatRepresentation("0.4375",8,23)
							,8,23,0));
			assertEquals(
					"0"+alu.floatRepresentation("-63.5", 8,23),
					alu.floatSubtraction(
							alu.floatRepresentation("1.75 ",8,23), 
							alu.floatRepresentation("65.25",8,23)
							,8,23, 0));
			/*assertEquals(
					"0"+alu.floatRepresentation("1.4", 8,23),
					alu.floatSubtraction(
							alu.floatRepresentation("1.1", 8,23), 
							alu.floatRepresentation("-0.3",8,23)
							,8,23, 0));*/
			assertEquals(
					"0"+alu.floatRepresentation("7.368", 8, 23),
					alu.floatSubtraction(
							alu.floatRepresentation("1.256", 8, 23), 
							alu.floatRepresentation("-6.112", 8, 23)
							, 8, 23, 0));
			assertEquals(
					"0"+alu.floatRepresentation("-7.368", 8, 23),
					alu.floatSubtraction(
							alu.floatRepresentation("-1.256", 8, 23), 
							alu.floatRepresentation("6.112", 8, 23)
							, 8, 23, 0));
			assertEquals(
					"0"+alu.floatRepresentation("-64.5", 8, 23),
					alu.floatSubtraction(
							alu.floatRepresentation("0.75 ", 8, 23), 
							alu.floatRepresentation("65.25", 8, 23)
							, 8, 23, 0));
			
			assertEquals(
					"0"+alu.floatRepresentation("85.875", 9, 6),
					alu.floatSubtraction(
							alu.floatRepresentation("85.643", 9, 6), 
							alu.floatRepresentation("-0.37", 9, 6)
							, 9, 6, 0));
			assertEquals(
					"0"+alu.floatRepresentation("85.825", 9, 6),
					alu.floatSubtraction(
							alu.floatRepresentation("85.643", 9, 6), 
							alu.floatRepresentation("-0.37", 9, 6)
							, 9, 6, 6));
			/*assertEquals(
					"0"+alu.floatRepresentation("226", 9, 6),
					alu.floatSubtraction(
							alu.floatRepresentation("217.36", 9, 6), 
							alu.floatRepresentation("-8.79", 9, 6)
							, 9, 6, 20));*/
			/*assertEquals(
					alu.floatRepresentation("226", 9, 6)+"0",
					alu.floatSubtraction(
							alu.floatRepresentation("217.36", 9, 6), 
							alu.floatRepresentation("-8.79", 9, 6)
							, 9, 6, 6));
			assertEquals(
					alu.floatRepresentation("208.5", 9, 6)+"0",
					alu.floatSubtraction(
							alu.floatRepresentation("217.36", 9, 6), 
							alu.floatRepresentation("8.79", 9, 6)
							, 9, 6, 0));
			assertEquals(
					alu.floatRepresentation("208.5", 9, 6)+"0",
					alu.floatSubtraction(
							alu.floatRepresentation("217.36", 9, 6), 
							alu.floatRepresentation("8.79", 9, 6)
							, 9, 6, 6));
			assertEquals(
					alu.floatRepresentation("0.0625", 9, 6)+"0",
					alu.floatSubtraction(
							alu.floatRepresentation("51.658", 9, 6), 
							alu.floatRepresentation("51.607", 9, 6)
							, 9, 6, 0));
			assertEquals(
					alu.floatRepresentation("0.0625", 9, 6)+"0",
					alu.floatSubtraction(
							alu.floatRepresentation("51.658", 9, 6), 
							alu.floatRepresentation("51.607", 9, 6)
							, 9, 6, 6));
			assertEquals(
					alu.floatRepresentation("-51.607", 9, 6)+"0",
					alu.floatSubtraction(
							alu.floatRepresentation("0", 9, 6), 
							alu.floatRepresentation("51.607", 9, 6)
							, 9, 6, 6));
			assertEquals(
					alu.floatRepresentation("51.607", 9, 6)+"0",
					alu.floatSubtraction(
							alu.floatRepresentation("0", 9, 6), 
							alu.floatRepresentation("-51.607", 9, 6)
							, 9, 6, 6));*/
							
		}
		@Test
		public void test19(){
			assertEquals("0"+alu.floatRepresentation("0.21875",8,23),alu.floatMultiplication(
					alu.floatRepresentation("0.5", 8,23), 
					alu.floatRepresentation("0.4375",8,23),8,23));
			assertEquals("0"+alu.floatRepresentation("0.328125",8,8),alu.floatMultiplication(
					alu.floatRepresentation("0.75",8,8), 
					alu.floatRepresentation("0.4375", 8,8),8,8));
			assertEquals("0"+alu.floatRepresentation("-0.328125", 8, 23),alu.floatMultiplication(
					alu.floatRepresentation("-0.75", 8, 23), 
					alu.floatRepresentation("0.4375", 8, 23), 8, 23));
			assertEquals("0"+alu.floatRepresentation("0.328125", 8, 23),alu.floatMultiplication(
					alu.floatRepresentation("-0.75", 8, 23), 
					alu.floatRepresentation("-0.4375", 8, 23), 8, 23));
			assertEquals("0"+alu.floatRepresentation("-0.328125", 8, 23),alu.floatMultiplication(
					alu.floatRepresentation("0.75", 8, 23), 
					alu.floatRepresentation("-0.4375", 8, 23), 8, 23));
			assertEquals("0"+alu.floatRepresentation("-48.9375", 8, 23),alu.floatMultiplication(
					alu.floatRepresentation("0.75", 8, 23), 
					alu.floatRepresentation("-65.25", 8, 23), 8, 23));
			/*assertEquals(alu.floatRepresentation(Float.MAX_VALUE+"", 8, 23),alu.floatMultiplication(
					alu.floatRepresentation("1.25", 8, 23), 
					alu.floatRepresentation(Float.MAX_VALUE+"", 8, 23), 8, 23));
			assertEquals(alu.floatRepresentation(Float.MAX_VALUE+"", 8, 23),alu.floatMultiplication(
					alu.floatRepresentation("1", 8, 23), 
					alu.floatRepresentation(Float.MAX_VALUE+"", 8, 23), 8, 23));
			assertEquals(alu.floatRepresentation(Float.MIN_VALUE+"", 8, 23),alu.floatMultiplication(
					alu.floatRepresentation("1", 8, 23), 
					alu.floatRepresentation(Float.MIN_VALUE+"", 8, 23), 8, 23));
			assertEquals(
					"0"
					+ "00000000"
					+ "000"
					+ "0000"
					+ "0000"
					+ "0000"
					+ "0000"
					+ "0001",alu.floatMultiplication(
					alu.floatRepresentation("0.5", 8, 23), 
					alu.floatRepresentation(Float.MIN_VALUE*2+"", 8, 23), 8, 23));
			assertEquals(
					"1"+ "00000000"+ "110"+ "0001"+ "1110"+ "0000"+ "0000"+ "0000",
					alu.floatMultiplication(
							"0"+ "00111111"+ "100"	+ "0000"+ "0000"+ "0000"+ "0000"+ "0000",
							"1"+ "01000000"+ "000"	+ "0010"+ "1000"+ "0000"+ "0000"+ "0000", 8, 23));
			assertEquals(
					"0"+"1"+ "00000000"+ "0110"+ "0001"+ "1110"+ "0000"+ "0000"+ "000",
					alu.floatMultiplication(
							"0"+ "00111110"+ "100"	+ "0000"+ "0000"+ "0000"+ "0000"+ "0000",
							"1"+ "01000000"+ "000"	+ "0010"+ "1000"+ "0000"+ "0000"+ "0000",8,23));*/
			assertEquals(
					"1"+"1"+ "11111111"+ "0000"+ "0000"+ "0000"+ "0000"+ "0000"+ "000",
					alu.floatMultiplication(
							"0"+ "11111111"+ "100"	+ "0000"+ "0000"+ "0000"+ "0000"+ "0000",
							"1"+ "11000000"+ "000"	+ "0000"+ "0000"+ "0000"+ "0000"+ "0000",8,23));
			assertEquals(
					"1"+"0"+ "11111111"+ "0000"+ "0000"+ "0000"+ "0000"+ "0000"+ "000",
					alu.floatMultiplication(
							"0"+ "11111111"+ "100"	+ "0000"+ "0000"+ "0000"+ "0000"+ "0000",
							"0"+ "11000000"+ "000"	+ "0000"+ "0000"+ "0000"+ "0000"+ "0000", 8,23));
		}
		@Test
		public void test20() {
			assertEquals(
					"0"+alu.floatRepresentation("0.875", 8, 23),
					alu.floatDivision(
							alu.floatRepresentation("0.4375", 8, 23), 
							alu.floatRepresentation("0.5", 8, 23), 8, 23));
			assertEquals(
					"0"+alu.floatRepresentation("-0.875", 8, 23),
					alu.floatDivision(
							alu.floatRepresentation("-0.4375", 8, 23), 
							alu.floatRepresentation("0.5", 8, 23), 8, 23));
			assertEquals(
					"0"+alu.floatRepresentation("0.875", 8, 23),
					alu.floatDivision(
							alu.floatRepresentation("-0.4375", 8, 23), 
							alu.floatRepresentation("-0.5", 8, 23), 8, 23));
			assertEquals(
					"0"+alu.floatRepresentation("-0.875", 8, 23),
					alu.floatDivision(
							alu.floatRepresentation("0.4375", 8, 23), 
							alu.floatRepresentation("-0.5", 8, 23), 8, 23));
			assertEquals(
					"0"+alu.floatRepresentation("-0", 8, 23),
					alu.floatDivision(
							alu.floatRepresentation("0", 8, 23), 
							alu.floatRepresentation("-0.5", 8, 23), 8, 23));
			assertEquals(
					"1"+"0"+ "11111111"
							+ "00000000000000000000000",
					alu.floatDivision(
							alu.floatRepresentation("0.75", 8,23), 
							alu.floatRepresentation("0", 8,23), 8,23));
			assertEquals(
					"1"+"1"+ "11111111"
							+ "00000000000000000000000",
					alu.floatDivision(
							alu.floatRepresentation("-0.75", 8,23), 
							alu.floatRepresentation("0",8,23), 8,23));
			assertEquals(
					"0"+"1"+ "01111000"+ "011"	+ "1100"+ "0101"+ "0010"+ "0110"+ "0100",
					alu.floatDivision(
							alu.floatRepresentation("0.75",8,23), 
							alu.floatRepresentation("-65.25",8,23), 8,23));
			assertEquals(
					"0"+alu.floatRepresentation("-0.01149425283074379",8,23),
					alu.floatDivision(						
							alu.floatRepresentation("0.75", 8,23), 
							alu.floatRepresentation("-65.25",8,23),8,23));
			/*assertEquals(
					"00"
					+ "00000000"
					+ "11100000000000000000000",
					alu.floatDivision(
							"0"	+ "00000001"
									+ "11000000000000000000000",
							"0"
							+ "10000000"
							+ "00000000000000000000000", 
							8,23));
			assertEquals(
					"0"
					+ "00000000"
					+ "01001001001001001001001",
					alu.floatDivision(
							"0"	+ "00000001"
									+ "00000000000000000000000",
							"0"
							+ "10000000"
							+ "11000000000000000000000", 
							 8, 23));
			assertEquals(
					"0"
					+ "11111111"
					+ "00000000000000000000000",
					alu.floatDivision(
							"0"	+ "10000001"
									+ "11100000000000000000000",
							"0"
							+ "00000001"
							+ "11000000000000000000000", 
							 8, 23));*/
		}
	}



