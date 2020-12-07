package telran.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

public interface InputOutput {
	String readString(String prompt);
	void writeObject(Object obj);
	default void writeLn(Object obj) {
		writeObject(obj + "\n");
	}
	default <R>R readObject(String prompt, String errorPrompt, Function<String, R> mapper) {
		// Read string and returns any object of a R-type
		while(true) {
			String string = readString(prompt);
			try {
				R result = mapper.apply(string);
				return result;
			} catch (Exception e) {
				writeLn(errorPrompt);
			}
		}
		
	}
	default String readOption(String prompt, List<String> options) {
		// Read string and returns it only if the string is one from the specified options
		
				return readObject(String.format("%s %s", prompt, options),
						"Entered string is not in options", s -> {
							if (options.contains(s)) {
								return s;
							}
							throw new RuntimeException();
						}) ;
	}
	default Integer readInteger(String prompt) {
		// Read string and returns any integer number
				return readObject(prompt, "It is not integer number", Integer::parseInt);
	}
	default Integer readInteger(String prompt, int min, int max) {
		// Read string and returns any integer number in the range [min-max]
				return readObject(prompt, String.format("It is not number in the range [%s-%s]", min, max),
						s -> {
							int number = Integer.parseInt(s);
							if (number < min || number > max) {
								throw new NumberFormatException();
							}
							return number;
						});
	}
	default Long readLong(String prompt) {
		// Read string and returns any long number 
		return readObject(prompt, "It is not long number", Long::parseLong);
	}
	default Double readDouble(String prompt) {
		// Read string and returns any double number 
		return readObject(prompt, "It is not a number", Double::parseDouble);
	}
	default LocalDate readDate(String prompt, String format) {
		//Read string and returns any integer number in the range [min-max]
				return readObject(String.format("%s in format %s",prompt,format), "Wrong date or format", s -> {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
					return LocalDate.parse(s, dtf);
				});
	}
	
	
}
