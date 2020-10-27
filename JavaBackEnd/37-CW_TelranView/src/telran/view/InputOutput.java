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
	
	default <R> R readObject(String prompt, String errorPrompt, Function <String, R> mapper) {
		// Read string and return any object of R - type
		while(true) {
			String string = readString(prompt);
			try {
				R result = mapper.apply(string);
				return result;
			} catch (Exception e) {
		writeLn(errorPrompt);
				e.printStackTrace();
			}
		}
		
	}
	
	default String readOption(String prompt, List<String> options) {
		//Read string and return any string out of specified options //contain in list
		return readObject(prompt, "It is not contain in the range", s -> {
		if(!options.contains(prompt)) {
			throw new IllegalArgumentException();
		}
		return prompt;
	});
	}
	default Integer readInteger(String prompt) {
		//Read string and return int
	
		return readObject(prompt, "It is not integer number", Integer::parseInt);
	}
	
	default Integer readInteger(String prompt, int min, int max) {
		//Read string and return int in range
		
		
		return readObject(prompt, String.format("It is not number in the range [%s-%s]", min, max), s -> {
			int number = Integer.parseInt(s);
			if(number<min||number>max) {
				throw new NumberFormatException();
			}
			return number;
		});
		
	}
	
	default Long readLong(String prompt) {
		// Read string and return long
		return readObject(prompt, "It is not long number", Long::parseLong);
	}
	default Double readDouble(String prompt) {
		//Read string and return double
		
		
		return readObject(prompt, "It is not long number", Double::parseDouble);
	}
	
	default LocalDate readDate(String prompt, String formate) {
		//Read string checked format and return localdate 
		return readObject(prompt, String.format("It is not date in the format - %s",formate), s -> {
		     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(formate);
		     LocalDate date =  LocalDate.parse(prompt);
		     if(!prompt.equals(dateFormatter.format(date))) {
		    	 throw new IllegalArgumentException();
		     }
		     return date;
		        
		});
	}
	
}
