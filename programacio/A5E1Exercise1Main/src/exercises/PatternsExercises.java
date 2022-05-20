package exercises;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternsExercises {
	
	private final static String MY_STRING = "4445DB";

	public static void main(String[] args) {
		
		Pattern pattern;
		
		pattern = Pattern.compile("^(\\d{0,4})([A-Z]{2})$");
		
		Matcher matcher = pattern.matcher(MY_STRING);
		
		while (matcher.find()) {
			
			System.out.println(matcher.group());
			
		}
		
		
		
	}

}
