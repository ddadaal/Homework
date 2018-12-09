import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * ģ��ALU���������͸���������������
 * @author [161250010_�¿���]
 *
 */

// test

public class ALU {
	
	public static final BinaryOperator<Boolean> booleanAnd = (x,y)->x&&y;
	public static final BinaryOperator<Boolean> booleanOr = (x,y)->x||y;
	public static final BinaryOperator<Boolean> booleanXOR = (x,y)->xor(x, y);
	
	
	public static void main(String[] args){
		ALU alu = new ALU();
		System.out.println(alu.integerMultiplication("00", "11", 2));
	}
	
	public static final boolean floatIsInfinity(String input, int eLength, int sLength){
		return input.matches(String.format("(1|0)(1{%d})(0{%d})",eLength,sLength));
	}
	
	public static final String complementLength(String binary, int length){
		return repeatedString(binary.substring(0,1), length-binary.length()) + binary;
	}
	
	public static final String repeatedString(String str, int times){
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<times;i++){
			builder.append(str);
		}
		return builder.toString();
	}
	
	public static final String bitOp(String oneBitOperand1, String oneBitOperand2, BinaryOperator<Boolean> op){
		boolean bit1 = oneBitOperand1.equals("1");
		boolean bit2=oneBitOperand2.equals("1");
		return op.apply(bit1, bit2)?"1":"0";
	}
	
	public static final boolean isSignTheSame(String binary1, String binary2){
		return binary1.charAt(0) == binary2.charAt(0);
	}
	
	public static String movePoint(String operand, int offset){
		StringBuilder mover = new StringBuilder(operand);
		if (operand.indexOf(".")==-1){
			mover.append(".");
		}
		if (offset>0){
			for(int i=0;i<offset;i++){
				int dotIndex = mover.indexOf(".");
				if (dotIndex==mover.length()-1){
					mover.append("0");
					
				}
				char temp = mover.charAt(dotIndex+1);
				mover.setCharAt(dotIndex+1, '.');
				mover.setCharAt(dotIndex, temp);
			}
		}else{
			for(int i=0;i>offset;i--){
				int dotIndex = mover.indexOf(".");
				if (dotIndex==0){
					mover.insert(0,"0");
					dotIndex++;
				}
				char temp = mover.charAt(dotIndex-1);
				mover.setCharAt(dotIndex-1, '.');
				mover.setCharAt(dotIndex, temp);
			}
			if (mover.charAt(0)=='.'){
				mover.insert(0, "0");
			}

		}
		return mover.toString();
	}
	
	
	public static final char charOp(BinaryOperator<Boolean> op, Character... bits){
		return Arrays.stream(bits).map(x->x.equals('1')).reduce((x,y)->op.apply(x, y)).orElse(bits[0]=='1') ? '1':'0';
	}
	
	public static final String stringOp(String operand1, String operand2, BinaryOperator<Boolean> op){
		if (operand1.length()==1){
			return bitOp(operand1, operand2, op);
		}
		return bitOp(operand1.substring(0, 1), operand2.substring(0,1),op) + stringOp(operand1.substring(1), operand2.substring(1),op);
		
	}
	
	public static final boolean xor(boolean operand1, boolean operand2){
		return (operand1 && !operand2) || (operand2 && !operand1);
	}
	
	public static int minBinaryLong(int number){
		if(number<2){
			return 1;
		}
		return 1+minBinaryLong(number/2);
	}
	
	public static String integerBinRepresentationInMinLength(String decimalNumber){
		ALU alu = new ALU();
		return alu.integerRepresentation(decimalNumber, minBinaryLong(Integer.parseInt(decimalNumber)));
	}

	
	
	public static String get2sComplement(String binaryNumber, boolean isNegative){
		ALU alu =new ALU();
		if (isNegative){
			StringBuilder builder = new StringBuilder(alu.negation(binaryNumber));
			for(int i=binaryNumber.length()-1;i>=0;i--){
				if (builder.charAt(i)=='0'){
					builder.setCharAt(i, '1');
					break;
				}else{
					builder.setCharAt(i, '0');
					continue;
				}
			}
			return builder.toString();
		}else{
			return binaryNumber;
		}
	}
	
	/**
	 * ����ʮ���������Ķ����Ʋ����ʾ��<br/>
	 * ����integerRepresentation("9", 8)
	 * @param number ʮ������������Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param length �����Ʋ����ʾ�ĳ���
	 * @return number�Ķ����Ʋ����ʾ������Ϊlength
	 */
	public String integerRepresentation (String number, int length) {
		long value = Integer.parseInt(number);
		
		boolean isNegative=false;
		if (value<0){
			value=-value;
			isNegative = true;
		}
		
		StringBuilder builder = new StringBuilder();

		for(int i=0;i<length;i++){
			long thisPosition = value /2;
			builder.append(value-(thisPosition*2));
			value=value>>1;
		}
		builder=builder.reverse();
		return get2sComplement(builder.toString(), isNegative);
		
	}
	
	/**
	 * ����ʮ���Ƹ������Ķ����Ʊ�ʾ��
	 * ��Ҫ���� 0������񻯡����������+Inf���͡�-Inf������ NaN�����أ������� IEEE 754��
	 * �������Ϊ��0���롣<br/>
	 * ����floatRepresentation("11.375", 8, 11)
	 * @param number ʮ���Ƹ�����������С���㡣��Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return number�Ķ����Ʊ�ʾ������Ϊ 1+eLength+sLength���������ң�����Ϊ���š�ָ���������ʾ����β������λ���أ�
	 */
	public String floatRepresentation (String number, int eLength, int sLength) {
		long upperBound = (long) (Math.pow(2,Math.pow(2, eLength-1)) - Math.pow(2, Math.pow(2, eLength-1)-sLength-1));



		double lowerBound = Math.pow(2, -Math.pow(2, eLength)-2-sLength); 

		long shift = (long)(Math.pow(2, eLength-1)-1);
		
		boolean isNegative = number.startsWith("-");
		String firstBit = isNegative ? "1" : "0";
		
		double trueValue;
		if(number.contains("Inf")){
			return firstBit+ALU.repeatedString("1", eLength)+ALU.repeatedString("0", sLength);
		}
		try{
			trueValue = Double.parseDouble(number);
			if (Double.isNaN(trueValue)){
				throw new NumberFormatException();
			}

		}
		catch(NumberFormatException e){
			return firstBit+repeatedString("1", eLength)+repeatedString("0", sLength-1)+"1";
		}
		
		//Returns 0 if trueValue is 0
		if (Math.abs(trueValue)<=lowerBound){
			return firstBit+repeatedString("0", eLength+sLength);
		}
		
		//Returns +Inf if trueValue is greater than the upper bound
		if (trueValue>=upperBound){
			return "0"+repeatedString("1", eLength)+repeatedString("0", sLength);
		}
		
		//Returns -Inf if trueValue is less than the lower bound
		if (trueValue<=-upperBound){
			return "1"+repeatedString("1", eLength)+repeatedString("0", sLength);
		}
		
		trueValue = Math.abs(trueValue);
		
		//denormalized
		if (trueValue<Math.pow(2, -Math.pow(2, eLength-2))){
			StringBuilder result = new StringBuilder(isNegative ?"1" : "0");
			result.append(repeatedString("0", eLength));
			trueValue*=Math.pow(2,shift-1);
			while (result.length()!=1+eLength+sLength){
				trueValue *=2;
				if ((int)trueValue==1) {
					result.append("1");
					trueValue--;
				}else{
					result.append("0");
				}
			}
			return result.toString();
		}
		

		StringBuilder result = new StringBuilder(firstBit);
		
		//convert float to binary sequence
		StringBuilder binary = new StringBuilder();

		
		String integerPart = integerBinRepresentationInMinLength(String.valueOf((int)trueValue));
		binary.append(integerPart).append(".");
		
		double decFloatPart = trueValue - (int)trueValue;
		
		while(binary.length()<=sLength+3){ //sLength+2 preserves the required number length. sLength with 0 and dot
			decFloatPart *=2;
			if ((int)decFloatPart==1){
				binary.append("1");
				decFloatPart--;
			}else{
				binary.append("0");
			}
		}
		
		int firstOneIndex = binary.indexOf("1");

		//if no 1 exists, indicates the value can be 0
		if (firstOneIndex==-1){
			return result.append(repeatedString("0", sLength+eLength)).toString();
		}
		
		//gets the index of dot. Appends if not exist.
		int dotIndex = binary.indexOf(".");
		if (dotIndex==-1){
			binary.append(".");
			dotIndex = binary.length()-1;
		}

		long exponent =shift;
		if (dotIndex>firstOneIndex){//1001.111, firstOneIndex=0, dotIndex=5, should be 4
			exponent += dotIndex-firstOneIndex-1; 
		}else{ //0.000101, firstOneIndex=5, dotIndex=1, should be -4
			exponent += dotIndex-firstOneIndex;
		}
		String exponentPart = integerRepresentation(String.valueOf(exponent), eLength);
		result.append(exponentPart);

		StringBuilder mantissa = new StringBuilder(binary.substring(firstOneIndex+1).replace(".",""));
		mantissa.append(repeatedString("0", sLength-mantissa.length()));
		result.append(mantissa);

		result.setLength(1+eLength+sLength); //preserves length

		return result.toString();
	}
	
	/**
	 * ����ʮ���Ƹ�������IEEE 754��ʾ��Ҫ�����{@link #floatRepresentation(String, int, int) floatRepresentation}ʵ�֡�<br/>
	 * ����ieee754("11.375", 32)
	 * @param number ʮ���Ƹ�����������С���㡣��Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 * @param length �����Ʊ�ʾ�ĳ��ȣ�Ϊ32��64
	 * @return number��IEEE 754��ʾ������Ϊlength���������ң�����Ϊ���š�ָ���������ʾ����β������λ���أ�
	 */
	public String ieee754 (String number, int length) {
		return floatRepresentation(number, length==32 ? 8 : 11, length==32 ? 23:52);
	}
	
	/**
	 * ��������Ʋ����ʾ����������ֵ��<br/>
	 * ����integerTrueValue("00001001")
	 * @param operand �����Ʋ����ʾ�Ĳ�����
	 * @return operand����ֵ����Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ
	 */
	public String integerTrueValue (String operand) {
		String value = operand;
		long result =0;
		boolean isNegative = false;
		if (operand.startsWith("1")){
			isNegative=true;
			value = get2sComplement(operand, true);
		}
		
		int length= operand.length();
		
		for(int i=0;i<length;i++){
			result+=Integer.parseInt(value.substring(i, i+1)) * Math.pow(2, length-i-1);
		}
		
		String resultStr = (isNegative?"-":"")+ Long.toString(result);
		return resultStr;
		
		
	}
	
	/**
	 * ���������ԭ���ʾ�ĸ���������ֵ��<br/>
	 * ����floatTrueValue("01000001001101100000", 8, 11)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return operand����ֵ����Ϊ���������һλΪ��-������Ϊ������ 0�����޷���λ����������ֱ��ʾΪ��+Inf���͡�-Inf���� NaN��ʾΪ��NaN��
	 */
	public String floatTrueValue (String operand, int eLength, int sLength) {
		boolean isNegative = operand.charAt(0)=='1';
		boolean notStandardized = operand.matches(String.format("(1|0)(0{%d})[01]{%d}",eLength,sLength));
		//infinity
		if (floatIsInfinity(operand, eLength, sLength)){
			return (isNegative?"-":"+")+"Inf";
		}

		//NaN
		if (operand.matches(String.format("(1|0)(1{%d})[01]{%d}",eLength,sLength))){
			return "NaN";
		}

		int shift = (int)(Math.pow(2, eLength-1)-1);
		int exponentValue = -shift;
		double mantissaValue = 1;

		if (notStandardized){
			exponentValue = -shift+1;
			mantissaValue = 0;
		}

		String mantissa = operand.substring(eLength+1);

		for(int i=eLength;i>=1;i--){
			exponentValue+= Integer.parseInt(operand.substring(i,i+1)) * Math.pow(2, eLength-i);
		}

		for(int i=0;i<sLength;i++){
			mantissaValue+= Double.parseDouble(mantissa.substring(i,i+1)) * Math.pow(2, -i-1);
		}

		double result = mantissaValue * Math.pow(2, exponentValue);
		if (result == (int)result){
			return (isNegative?"-":"") + String.valueOf((int)result)+".0";
		}
	
		String resultRepresentation = String.valueOf(result);
		if (resultRepresentation.contains("E")){
			String[] split = resultRepresentation.split("E");
			resultRepresentation = movePoint(split[0], Integer.parseInt(split[1]));
		}

		return (isNegative?"-":"") + resultRepresentation;
		


	}
	
	/**
	 * ��λȡ��������<br/>
	 * ����negation("00001001")
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @return operand��λȡ���Ľ��
	 */
	public String negation (String operand) {
		StringBuilder builder = new StringBuilder(operand);
		for(int i=0;i<operand.length();i++){
			char bit = builder.charAt(i);
			bit = charOp(booleanXOR, bit, '1');
			builder.setCharAt(i, bit);
		}
		return builder.toString();
	}
	
	/**
	 * ���Ʋ�����<br/>
	 * ����leftShift("00001001", 2)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param n ���Ƶ�λ��
	 * @return operand����nλ�Ľ��
	 */
	public String leftShift (String operand, int n) {
		StringBuilder builder = new StringBuilder();
		
		for(int i=n;i<operand.length();i++){
			builder.append(operand.charAt(i));
		}
		for(int i=0;i<n;i++){
			builder.append('0');
		}
		builder.setLength(operand.length());
		return builder.toString();
	}
	
	/**
	 * �߼����Ʋ�����<br/>
	 * ����logRightShift("11110110", 2)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param n ���Ƶ�λ��
	 * @return operand�߼�����nλ�Ľ��
	 */
	public String logRightShift (String operand, int n) {
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<n;i++){
			builder.append('0');
		}
		for(int i=0;i<operand.length()-n;i++){
			builder.append(operand.charAt(i));
		}
		builder.setLength(operand.length());
		return builder.toString();
	}
	
	/**
	 * �������Ʋ�����<br/>
	 * ����logRightShift("11110110", 2)
	 * @param operand �����Ʊ�ʾ�Ĳ�����
	 * @param n ���Ƶ�λ��
	 * @return operand��������nλ�Ľ��
	 */
	public String ariRightShift (String operand, int n) {
		
		StringBuilder builder = new StringBuilder();
		
		char leadingLetter = operand.charAt(0);
		
		for(int i=0;i<n;i++){
			builder.append(leadingLetter);
		}
		
		for(int i=0;i<operand.length()-n;i++){
			builder.append(operand.charAt(i));
		}
		builder.setLength(operand.length());
		return builder.toString();
	}
	
	/**
	 * ȫ����������λ�Լ���λ���мӷ����㡣<br/>
	 * ����fullAdder('1', '1', '0')
	 * @param x ��������ĳһλ��ȡ0��1
	 * @param y ������ĳһλ��ȡ0��1
	 * @param c ��λ�Ե�ǰλ�Ľ�λ��ȡ0��1
	 * @return ��ӵĽ�����ó���Ϊ2���ַ�����ʾ����1λ��ʾ��λ����2λ��ʾ��
	 */
	public String fullAdder (char x, char y, char c) {
		StringBuilder builder = new StringBuilder();
		
		char carryResult = charOp(booleanOr, 
				charOp(booleanAnd, x, y),
				charOp(booleanAnd, x, c),
				charOp(booleanAnd, y, c));
		
		char sum = charOp(booleanXOR, x,y,c);
		
		builder.append(carryResult).append(sum);
		
		return builder.toString();
	}
	
	/**
	 * 4λ���н�λ�ӷ�����Ҫ�����{@link #fullAdder(char, char, char) fullAdder}��ʵ��<br/>
	 * ����claAdder("1001", "0001", '1')
	 * @param operand1 4λ�����Ʊ�ʾ�ı�����
	 * @param operand2 4λ�����Ʊ�ʾ�ļ���
	 * @param c ��λ�Ե�ǰλ�Ľ�λ��ȡ0��1
	 * @return ����Ϊ5���ַ�����ʾ�ļ����������е�1λ�����λ��λ����4λ����ӽ�������н�λ��������ѭ�����
	 */
	public String claAdder (String operand1, String operand2, char c) {
		StringBuilder result = new StringBuilder();
		BiFunction<Character, Character, Integer> pi = (x,y)->charOp(booleanOr, x, y)=='1' ? 1 :0;
		BiFunction<Character, Character, Integer> gi = (x,y)->charOp(booleanAnd, x, y)=='1' ? 1 :0;
		
		int p1 = pi.apply(operand1.charAt(3), operand2.charAt(3));
		int g1 = gi.apply(operand1.charAt(3), operand2.charAt(3));
		int p2 = pi.apply(operand1.charAt(2), operand2.charAt(2));
		int g2 = gi.apply(operand1.charAt(2), operand2.charAt(2));
		int p3 = pi.apply(operand1.charAt(1), operand2.charAt(1));
		int g3 = gi.apply(operand1.charAt(1), operand2.charAt(1));
		int p4 = pi.apply(operand1.charAt(0), operand2.charAt(0));
		int g4 = gi.apply(operand1.charAt(0), operand2.charAt(0));
		
		int c1= g1 | (p1 & c);
		int c2 =g2 | (p2 & g1) | (p2&p1 & c);
		int c3=g3|(p3 &g2) | (p3&p2&g1) | (p3&p2&p1&c);
		int c4=g4|(p4 & g3)|(p4&p3&g2) | (p4&p3&p2&g1) | (p4&p3&p2&p1&c);
		
		result.append(c4);
		result.append(fullAdder(operand1.charAt(0), operand2.charAt(0) , c3==1 ? '1' : '0').charAt(1));
		result.append(fullAdder(operand1.charAt(1), operand2.charAt(1) , c2==1 ? '1' : '0').charAt(1));
		result.append(fullAdder(operand1.charAt(2), operand2.charAt(2) , c1==1 ? '1' : '0').charAt(1));
		result.append(fullAdder(operand1.charAt(3), operand2.charAt(3) , c).charAt(1));	
		return result.toString();
	}
	
	/**
	 * ��һ����ʵ�ֲ�������1�����㡣
	 * ��Ҫ�������š����š�����ŵ�ģ�⣬
	 * ������ֱ�ӵ���{@link #fullAdder(char, char, char) fullAdder}��
	 * {@link #claAdder(String, String, char) claAdder}��
	 * {@link #adder(String, String, char, int) adder}��
	 * {@link #integerAddition(String, String, int) integerAddition}������<br/>
	 * ����oneAdder("00001001")
	 * @param operand �����Ʋ����ʾ�Ĳ�����
	 * @return operand��1�Ľ��������Ϊoperand�ĳ��ȼ�1�����е�1λָʾ�Ƿ���������Ϊ1������Ϊ0��������λΪ��ӽ��
	 */
	public String oneAdder (String operand) {
		
		if (operand.indexOf("0")==-1){
			return ALU.repeatedString("0", operand.length()+1);
		}
		
		char rightmostBit = charOp(booleanXOR, operand.charAt(operand.length()-1), '1');
		char carry = operand.charAt(operand.length()-1);
		
		StringBuilder result = new StringBuilder(String.valueOf(rightmostBit));
		
		for(int i=operand.length()-2;i>=0;i--){
			result.append(charOp(booleanXOR, operand.charAt(i), carry));
			carry = charOp(booleanAnd, operand.charAt(i), carry);
		}
		
		
		boolean isOverflow = charOp(booleanXOR, operand.charAt(0), result.charAt(result.length()-1)) == '1';
		
		result.append(isOverflow?"1":"0");
		
		result.reverse();
		return result.toString();
	}
	
	/**
	 * �ӷ�����Ҫ�����{@link #claAdder(String, String, char)}����ʵ�֡�<br/>
	 * ����adder("0100", "0011", ��0��, 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ļ���
	 * @param c ���λ��λ
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����ӽ��
	 */
	public String adder (String operand1, String operand2, char c, int length) {
		String normalizedOperand1 = complementLength(operand1, length);
		String normalizedOperand2 = complementLength(operand2, length);
		
		char carry = c;
		StringBuilder finalResult = new StringBuilder();
		
		int partsLeft = length/4;
		int highestBits = length%4; //if length is not a multiple of 4, calculates the rest bits
		
		while(partsLeft>0){
			String claResult = claAdder(normalizedOperand1.substring(highestBits + 4*partsLeft-4, highestBits + 4*partsLeft), normalizedOperand2.substring(highestBits+ 4*partsLeft-4, highestBits+4*partsLeft), carry);
			finalResult.insert(0, claResult.substring(1));
			carry = claResult.charAt(0); 
			partsLeft--;
		}
		
		//uses fullAdder for the rest bits
		for(int i=highestBits-1;i>=0;i--){
			String bitResult = fullAdder(normalizedOperand1.charAt(i), normalizedOperand2.charAt(i), carry);
			carry = bitResult.charAt(0);
			finalResult.insert(0, bitResult.charAt(1));
		}
		
		//overflow
		if (normalizedOperand1.charAt(0)==normalizedOperand2.charAt(0) && normalizedOperand1.charAt(0) != finalResult.charAt(0)){
			finalResult.insert(0, "1");
		}else{
			finalResult.insert(0, "0");
		}
		
		return finalResult.toString();
		
		
	}
	
	/**
	 * �����ӷ���Ҫ�����{@link #adder(String, String, char, int) adder}����ʵ�֡�<br/>
	 * ����integerAddition("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ļ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����ӽ��
	 */
	public String integerAddition (String operand1, String operand2, int length) {
		return adder(operand1, operand2, '0', length);
	}
	
	/**
	 * �����������ɵ���{@link #adder(String, String, char, int) adder}����ʵ�֡�<br/>
	 * ����integerSubtraction("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ļ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ��������
	 */
	public String integerSubtraction (String operand1, String operand2, int length) {
		return adder(operand1, negation(operand2), '1', length);
	}
	
	/**
	 * �����˷���ʹ��Booth�㷨ʵ�֣��ɵ���{@link #adder(String, String, char, int) adder}�ȷ�����<br/>
	 * ����integerMultiplication("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ĳ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊlength+1���ַ�����ʾ����˽�������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������lengthλ����˽��
	 */
	public String integerMultiplication (String operand1, String operand2, int length) {
		String formattedOperand1 = complementLength(operand1, length);
		String formattedOperand2 = complementLength(operand2, length);

		String minusOperand1 = negation(formattedOperand1);
		
		String thatTwoRegisters = repeatedString("0", length)+formattedOperand2+"0";
		
		for(int i=0;i<length;i++){
			switch(thatTwoRegisters.substring(thatTwoRegisters.length()-2, thatTwoRegisters.length())){
			case "10":{
				String intermediateResult = adder(minusOperand1, thatTwoRegisters.substring(0, length), '1', length).substring(1);
				thatTwoRegisters = intermediateResult + thatTwoRegisters.substring(length);
				break;
			}
			case "01":{
				String intermediateResult = adder(formattedOperand1, thatTwoRegisters.substring(0, length), '0', length).substring(1);
				thatTwoRegisters = intermediateResult + thatTwoRegisters.substring(length);
				break;
			}
			case "00":
			case "11":
				break;
			}
			thatTwoRegisters = ariRightShift(thatTwoRegisters, 1);
		}
		
		boolean notOverflow = thatTwoRegisters.substring(0,length+1).indexOf("1")==-1
				|| thatTwoRegisters.substring(0,length+1).indexOf("0")==-1;
		
		
		return (notOverflow ?"0" : "1")+ thatTwoRegisters.substring(length,2*length);
		
	}
	
	/**
	 * �����Ĳ��ָ������������ɵ���{@link #adder(String, String, char, int) adder}�ȷ���ʵ�֡�<br/>
	 * ����integerDivision("0100", "0011", 8)
	 * @param operand1 �����Ʋ����ʾ�ı�����
	 * @param operand2 �����Ʋ����ʾ�ĳ���
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ���ĳ���������ĳ���С��lengthʱ����Ҫ�ڸ�λ������λ
	 * @return ����Ϊ2*length+1���ַ�����ʾ�������������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0�������lengthλΪ�̣����lengthλΪ����
	 */
	public String integerDivision (String operand1, String operand2, int length) {
		
		String formattedOperand1 = complementLength(operand1, length);
		String formattedOperand2 = complementLength(operand2, length);
		
		if (formattedOperand2.indexOf("1")==-1){
			return "NaN";
		}
		
		String thatTwoRegisters = repeatedString(formattedOperand1.substring(0,1), length)+formattedOperand1;
		String minusOperand2 = negation(formattedOperand2);
		
		
		boolean isOverflow = false;
		boolean nextPlus = false; //indicates next op, true for plus and false for minus
		char Qn;
		//calculates Qn and judges if overflow
		if (isSignTheSame(formattedOperand1, formattedOperand2)){
			thatTwoRegisters = adder(thatTwoRegisters.substring(0,length), minusOperand2, '1', length).substring(1) + thatTwoRegisters.substring(length);
			if (isSignTheSame(thatTwoRegisters, formattedOperand2)){
				Qn = '1';
				nextPlus = false;
				isOverflow = true;
			}
			else{
				Qn = '0';
				nextPlus = true;
			}
		}else{
			thatTwoRegisters = adder(thatTwoRegisters.substring(0,length), formattedOperand2, '0',length).substring(1) + thatTwoRegisters.substring(length);
			if (isSignTheSame(thatTwoRegisters, formattedOperand2)){
				Qn = '1';
				nextPlus = false;
			}
			else{
				Qn = '0';
				isOverflow = true;
				nextPlus = true;
			}
		}
		
		thatTwoRegisters = thatTwoRegisters.substring(1)+Qn;
		
		//cycle
		for(int i=0;i<length;i++){
			if (!nextPlus){
				thatTwoRegisters =  adder(thatTwoRegisters.substring(0, length), minusOperand2, '1', length).substring(1) + thatTwoRegisters.substring(length);

			}else{
				thatTwoRegisters = adder(thatTwoRegisters.substring(0, length), formattedOperand2, '0', length).substring(1) + thatTwoRegisters.substring(length);
			}
			if (isSignTheSame(thatTwoRegisters, formattedOperand2)){
				Qn='1';
				nextPlus = false;
			}else{
				Qn='0';
				nextPlus = true;
			}
			if (i!=length-1){
				thatTwoRegisters = thatTwoRegisters.substring(1) + Qn;
			}

		}
		
		//cycle ends
		String left = thatTwoRegisters.substring(0,length);
		String result = thatTwoRegisters.substring(length+1)+Qn;

		
		if (!isSignTheSame(formattedOperand1, formattedOperand2)){
			result = oneAdder(result).substring(1);
		}

		
		if (!isSignTheSame(left, formattedOperand1)){
			if (isSignTheSame(formattedOperand1,formattedOperand2)){
				left = adder(left, formattedOperand2, '0', length).substring(1);
			}else{
				left = adder(left, minusOperand2, '1',length).substring(1);
			}
		}
		
		//handle left=operand2 -6/-3=2
		if (integerSubtraction(left, operand2, length).substring(1).equals(repeatedString("0", length))){
			left = repeatedString("0",length);
			result = oneAdder(result).substring(1);
		}
		
		//handle left=-operand2 -6/3=-2
		if (integerAddition(left, operand2, length).substring(1).equals(repeatedString("0", length))){
			left = repeatedString("0",length);
			result = integerAddition(result, repeatedString("1", length),length).substring(1);
		}
		
		isOverflow = isOverflow || (result.equals("1"+repeatedString("0", length-1)));
		
		
		return (isOverflow ? "1" : "0") + result+left;
	
	}
	
	/**
	 * �����������ӷ������Ե���{@link #adder(String, String, char, int) adder}�ȷ�����
	 * ������ֱ�ӽ�������ת��Ϊ�����ʹ��{@link #integerAddition(String, String, int) integerAddition}��
	 * {@link #integerSubtraction(String, String, int) integerSubtraction}��ʵ�֡�<br/>
	 * ����signedAddition("1100", "1011", 8)
	 * @param operand1 ������ԭ���ʾ�ı����������е�1λΪ����λ
	 * @param operand2 ������ԭ���ʾ�ļ��������е�1λΪ����λ
	 * @param length ��Ų������ļĴ����ĳ��ȣ�Ϊ4�ı�����length��С�ڲ������ĳ��ȣ����������ţ�����ĳ���������ĳ���С��lengthʱ����Ҫ���䳤����չ��length
	 * @return ����Ϊlength+2���ַ�����ʾ�ļ����������е�1λָʾ�Ƿ���������Ϊ1������Ϊ0������2λΪ����λ����lengthλ����ӽ��
	 */
	public String signedAddition (String operand1, String operand2, int length) {
		int valueLength = length+1;
		StringBuilder result = new StringBuilder();
		String absOperand1 = repeatedString("0", length-operand1.length()+2) + operand1.substring(1); //+1 for compensating sign bit, +1 for making it positive for adder
		String absOperand2 = repeatedString("0", length-operand2.length()+2) + operand2.substring(1);
		
		if (isSignTheSame(operand1, operand2)){
			String addResult = adder(absOperand1, absOperand2, '0', valueLength).substring(1);
			
			result.append(addResult.charAt(0)); //overflow
			
			result.append(operand1.charAt(0)).append(addResult.substring(1));
			
		}else{
			String minusResult = adder("0"+absOperand1, "0"+negation(absOperand2), '1', valueLength+1); //overflow bit indicates the highest carry
			char sign = operand1.charAt(0);
			char highestCarry = minusResult.charAt(0);
			minusResult = minusResult.substring(3);
			if (highestCarry=='0'){
				//no carry, result is negative, 
				minusResult = get2sComplement(minusResult, true);
				sign = sign=='0' ?'1':'0';
			}
			result.append('0').append(sign).append(minusResult);
		}
		return result.toString();
	}
	
	/**
	 * �������ӷ����ɵ���{@link #signedAddition(String, String, int) signedAddition}�ȷ���ʵ�֡�<br/>
	 * ����floatAddition("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ļ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param gLength ����λ�ĳ���
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����ӽ�������е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatAddition (String operand1, String operand2, int eLength, int sLength, int gLength) {
		
		if (operand1.substring(1).indexOf("1")==-1){
			return "0"+operand2;
		}
		if (operand2.substring(1).indexOf("1")==-1){
			return "0"+operand1;
		}
		
		
		
		
		
		boolean operand1IsInfinity = floatIsInfinity(operand1, eLength, sLength);
		boolean operand2IsInfinity = floatIsInfinity(operand2, eLength, sLength);
		
		if ((operand1IsInfinity && operand2IsInfinity) | (operand1.matches(String.format("(1|0)(1{%d})[01]{%d}",eLength,sLength)) || operand2.matches(String.format("(1|0)(1{%d})[01]{%d}",eLength,sLength)))){
			return "0"+repeatedString("1", eLength)+repeatedString("0", sLength-1)+"1";
		}
		
		if (operand1IsInfinity){
			return operand1;
		}
		
		if (operand2IsInfinity){
			return operand2;
		}
		
		
		String value1 = "1."+operand1.substring(eLength+1);
		String value2= "1."+operand2.substring(eLength+1);
		

		boolean operand1Denormalized = operand1.substring(1,eLength+1).equals(repeatedString("0", eLength));
		boolean operand2Denormalized = operand2.substring(1,eLength+1).equals(repeatedString("0", eLength));
		//unifies order
		int order1 = Integer.parseInt(integerTrueValue("0"+operand1.substring(1, eLength+1)));
		int order2 = Integer.parseInt(integerTrueValue("0"+operand2.substring(1, eLength+1)));
		int biggerOrder = order1;
		if (operand1Denormalized){
			//operand1 is denormalized
			value1 = "0."+value1.split("\\.")[1];
		}
		
		if (operand2Denormalized){
			//operand2 is denormalized
			value2 = "0."+value2.split("\\.")[1];
		}
		
		int originalValue1Length = value1.length();
		int originalValue2Length = value2.length();
		
		
		if (order1-order2>0){
			//uses order1
			value2 = movePoint(value2, order2-order1);
			if (value2.length()>originalValue2Length + gLength){
				value2 = value2.substring(0, originalValue2Length + gLength);
			}
		}else{
			//uses order2
			biggerOrder = order2;
			value1 = movePoint(value1, order1-order2);
			if (value1.length()>originalValue1Length + gLength){
				value2 = value2.substring(0, originalValue1Length + gLength);
			}
		}
		

		
		//add leading letter for sign
		value1 = operand1.charAt(0)+ value1;
		value2 = operand2.charAt(0)+ value2;
		
		//add ending 0 to correct bits 
		if (value1.length()<value2.length()){
			value1= value1 + repeatedString("0", value2.length()-value1.length());
		}else{
			value2 = value2+repeatedString("0", value1.length()-value2.length());
		}
		
		String totalResult = signedAddition(value1.replace(".", ""), value2.replace(".",""), value1.length()-1);
		
		//returns 0 if it's 0
		if (!totalResult.substring(2).contains("1")){
			return repeatedString("0", sLength+eLength+2);
		}
		
		
		totalResult = (new StringBuilder(totalResult)).insert(4, ".").toString();
	
		
		
		char sign = totalResult.charAt(1);
		
		if (totalResult.charAt(totalResult.indexOf(".")-2) == '1'){
			//carry exists in integer parts
			biggerOrder++;
			totalResult = movePoint(totalResult, -1);
		}
		
		while(totalResult.charAt(totalResult.indexOf(".")-1)!='1'){
			totalResult = movePoint(totalResult, 1);
			biggerOrder--;
			if (biggerOrder==0) break;
		}
		
		
		
		//Inf
		if (biggerOrder > Math.pow(2, eLength)-1){
			return "1"+sign+repeatedString("1", eLength)+repeatedString("0", sLength);
		}
		
		
		totalResult = totalResult.split("\\.")[1];
		totalResult = totalResult + repeatedString("0", sLength-totalResult.length());
		totalResult = totalResult.substring(0, sLength);
		
		StringBuilder result = new StringBuilder();
		
		result.append("0");
		result.append(sign).append(biggerOrder <= 0 ? repeatedString("0", eLength) : integerRepresentation(String.valueOf(biggerOrder), eLength));
		result.append(totalResult);
		
		return result.toString();
	}
	
	/**
	 * �������������ɵ���{@link #floatAddition(String, String, int, int, int) floatAddition}����ʵ�֡�<br/>
	 * ����floatSubtraction("00111111010100000", "00111111001000000", 8, 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ļ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param gLength ����λ�ĳ���
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ�������������е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatSubtraction (String operand1, String operand2, int eLength, int sLength, int gLength) {
		return floatAddition(operand1, (operand2.charAt(0)=='0' ? "1" : "0")+operand2.substring(1), eLength, sLength, gLength);
	}
	
	/**
	 * �������˷����ɵ���{@link #integerMultiplication(String, String, int) integerMultiplication}�ȷ���ʵ�֡�<br/>
	 * ����floatMultiplication("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ĳ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����˽��,���е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatMultiplication (String operand1, String operand2, int eLength, int sLength) {
		char sign = charOp(booleanXOR,operand1.charAt(0), operand2.charAt(0));
		
		if (operand1.substring(1).indexOf("1")==-1 || operand2.substring(1).indexOf("1")==-1){
			return "0"+sign+ALU.repeatedString("0", sLength+eLength);
		}
		
		
		int order1 = Integer.parseInt(integerTrueValue('0'+operand1.substring(1,eLength+1)));
		int order2 = Integer.parseInt(integerTrueValue('0'+operand2.substring(1,eLength+1)));
		long shift = (long) (Math.pow(2, eLength-1)-1); 
		
		

		long orderResult = order1+order2-shift;

		String manttisa1 = "01"+operand1.substring(eLength+1);
		String manttisa2 = "01"+operand2.substring(eLength+1);
		
		if (order1==0){
			manttisa1 = "00"+operand1.substring(eLength+1);
		}
		if (order2==0){
			manttisa2 = "00"+operand1.substring(eLength+1);
		}
		
		String manttisaResult = integerMultiplication(manttisa1, manttisa2, 2*sLength+2);
		manttisaResult = manttisaResult.substring(0, 3)+"."+manttisaResult.substring(3);
		if (manttisaResult.charAt(1)=='1'){
			manttisaResult = movePoint(manttisaResult, -1);
			orderResult++;
		}
		while (manttisaResult.charAt(manttisaResult.indexOf('.')-1)!='1'){
			manttisaResult = movePoint(manttisaResult, 1);
			orderResult--;
			if (orderResult==0){
				return "0"+sign+repeatedString("0", eLength)+manttisaResult.split("\\.")[1];
			}
		}
		if (orderResult > Math.pow(2, eLength)-1){
			return "1"+sign+repeatedString("1", eLength)+repeatedString("0", sLength);
		}
		
		StringBuilder result = new StringBuilder();
		result.append('0');
		result.append(sign);
		result.append(integerRepresentation(String.valueOf(orderResult), eLength));
		result.append(manttisaResult.split("\\.")[1].substring(0,sLength));
		
		return result.toString();
	}
	
	/**
	 * �������������ɵ���{@link #integerDivision(String, String, int) integerDivision}�ȷ���ʵ�֡�<br/>
	 * ����floatDivision("00111110111000000", "00111111000000000", 8, 8)
	 * @param operand1 �����Ʊ�ʾ�ı�����
	 * @param operand2 �����Ʊ�ʾ�ĳ���
	 * @param eLength ָ���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @param sLength β���ĳ��ȣ�ȡֵ���ڵ��� 4
	 * @return ����Ϊ2+eLength+sLength���ַ�����ʾ����˽��,���е�1λָʾ�Ƿ�ָ�����磨���Ϊ1������Ϊ0��������λ����������Ϊ���š�ָ���������ʾ����β������λ���أ����������Ϊ��0����
	 */
	public String floatDivision (String operand1, String operand2, int eLength, int sLength) {
		
		if (operand2.equals(ALU.repeatedString("0", 1+eLength+sLength))){
			return "00"+ALU.repeatedString("1", eLength)+ALU.repeatedString("0", sLength);
		}
		
		char sign = charOp(booleanXOR,operand1.charAt(0), operand2.charAt(0));
		
		if (operand1.substring(1).indexOf("1")==-1){
			return "0"+sign+repeatedString("0", eLength+sLength);
		}
		
		if (operand2.substring(1).indexOf("1")==-1){
			return "1"+sign+repeatedString("1", eLength)+repeatedString("0", sLength);
		}
		
		
		int order1 = Integer.parseInt(integerTrueValue('0'+operand1.substring(1,eLength+1)));
		int order2 = Integer.parseInt(integerTrueValue('0'+operand2.substring(1,eLength+1)));
		long shift = (long) (Math.pow(2, eLength-1)-1); 
		
		
		
		

		long orderResult = order1-order2+shift;

		String manttisa1 = "1."+operand1.substring(eLength+1);
		String manttisa2 = "1."+operand2.substring(eLength+1);
		if (order1==0){
			manttisa1 = "0."+operand1.substring(eLength+1);
		}
		if (order2==0){
			manttisa2 = "0."+operand1.substring(eLength+1);
		}
		
		
		
		String manttisaResult = trueFormDivision(manttisa1, manttisa2, sLength+1);
		
		
		
		
		manttisaResult = manttisaResult.charAt(0)+"."+manttisaResult.substring(1);
		while (manttisaResult.charAt(manttisaResult.indexOf('.')-1)!='1'){
			manttisaResult = movePoint(manttisaResult, 1);
			orderResult--;
			if (orderResult==0){
				return "0"+sign+repeatedString("0", eLength)+manttisaResult.split("\\.")[1];
			}
		}
		if (orderResult > Math.pow(2, eLength)-1){
			return "1"+sign+repeatedString("1", eLength)+repeatedString("0", sLength);
		}
		
		StringBuilder result = new StringBuilder();
		result.append('0');
		result.append(sign);
		result.append(integerRepresentation(String.valueOf(orderResult), eLength));
		result.append(manttisaResult.split("\\.")[1].substring(0,sLength));
		
		return result.toString();
	}
	//ֱ�Ӵ���1.011����0.111���У�lengthΪС����󳤶�+1��һ��Ϊ24
	public String trueFormDivision (String operand1, String operand2, int length) {
		operand1 = "0"+operand1.replace(".", "");
		operand2 = "0"+operand2.replace(".", "");
		String thatTwoRegisters = operand1+ repeatedString("0", length);
		String minusOperand2 = negation(operand2);
		
		boolean nextPlus = false; //indicates next op, true for plus and false for minus
		char Qn;
		//calculates Qn and judges if overflow
		if (isSignTheSame(operand1, operand2)){
			thatTwoRegisters = adder(thatTwoRegisters.substring(0,length), minusOperand2, '1', length).substring(1) + thatTwoRegisters.substring(length);
			if (isSignTheSame(thatTwoRegisters, operand2)){
				Qn = '1';
				nextPlus = false;
			}
			else{
				Qn = '0';
				nextPlus = true;
			}
		}else{
			thatTwoRegisters = adder(thatTwoRegisters.substring(0,length), operand2, '0',length).substring(1) + thatTwoRegisters.substring(length);
			if (isSignTheSame(thatTwoRegisters, operand2)){
				Qn = '1';
				nextPlus = false;
			}
			else{
				Qn = '0';
				nextPlus = true;
			}
		}
		
		thatTwoRegisters = thatTwoRegisters.substring(1)+Qn;
		
		//cycle
		for(int i=0;i<length;i++){
			if (!nextPlus){
				thatTwoRegisters =  adder(thatTwoRegisters.substring(0, length), minusOperand2, '1', length).substring(1) + thatTwoRegisters.substring(length);

			}else{
				thatTwoRegisters = adder(thatTwoRegisters.substring(0, length), operand2, '0', length).substring(1) + thatTwoRegisters.substring(length);
			}
			if (isSignTheSame(thatTwoRegisters, operand2)){
				Qn='1';
				nextPlus = false;
			}else{
				Qn='0';
				nextPlus = true;
			}
			if (i!=length-1){
				thatTwoRegisters = thatTwoRegisters.substring(1) + Qn;
			}

		}
		
		//cycle ends
		String left = thatTwoRegisters.substring(0,length);
		String result = thatTwoRegisters.substring(length+1)+Qn;

		
		if (!isSignTheSame(operand1, operand2)){
			result = oneAdder(result).substring(1);
		}

		
		if (!isSignTheSame(left, operand1)){
			if (isSignTheSame(operand1,operand2)){
				left = adder(left, operand2, '0', length).substring(1);
			}else{
				left = adder(left, minusOperand2, '1',length).substring(1);
			}
		}
		
		return result;
	
	}
}
