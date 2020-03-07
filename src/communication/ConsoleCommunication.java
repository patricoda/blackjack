package communication;

import java.util.Scanner;

public class ConsoleCommunication {
	private static Scanner consoleScanner = new Scanner(System.in);

	public static void output(final String message) {
		System.out.println(message);
	}
	
	public static int inputInt(final String prompt) {
		output("\n" + prompt);
		int numericValue;
		
		try{
			numericValue = Integer.parseInt(consoleScanner.nextLine());
		} catch(NumberFormatException e) {
			numericValue = -1;
		}
		
		return numericValue;
		 
	}
	
	public static String inputString(final String prompt) {
		output("\n" + prompt);
		return consoleScanner.nextLine();
	}
}
