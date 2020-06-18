package telran.numbers;

public class CodeDecode {
	private String keyCodeDecode;// unique
	public CodeDecode(String keyCodeDecode) {
		super();
		this.keyCodeDecode = keyCodeDecode;
	}

private int getBase() {
int length = keyCodeDecode.length();
	return length;
}
private char getDigitChar(int digit) {
	char result = this.keyCodeDecode.charAt(digit);

	return result;
}
private int getDigit(char digitChar) {
int result = (int)this.keyCodeDecode.indexOf(digitChar);
	
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

}
