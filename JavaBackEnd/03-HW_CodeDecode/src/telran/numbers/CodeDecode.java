package telran.numbers;

public class CodeDecode {
	String keyCodeDecode;// unique
	public CodeDecode(String keyCodeDecode) {
		super();
		this.keyCodeDecode = keyCodeDecode;
	}

private int getBase() {
//	TODO
	
	
	return 10;
}
private char getDigitChar(int digit) {
//	TODO using charAT
	
	return (char) ('0' + digit);
}
private int getDigit(char digitChar) {
//	TODO using indexOf
	
	return digitChar - '0';
}
public int decode (String numberStr) {
	int length = numberStr.length(); // srting length
	int result = 0;
	int base = getBase();
	for(int i = 0; i<length;i++) {
		char digitChar = numberStr.charAt(i);//for first iteration digitChar = '1'
		int digit = getDigit(digitChar);
		result = result*base+digit;
		
	}
	
	return result;
}
public String code(int number) {
	
	String result = "";
	int base = getBase();
	do {
		int digit = number % base;
		char digitChar = getDigitChar(digit);
		result = digitChar + result;
		number /= base;
		
	}while(number!=0);
	return result;
}
}
