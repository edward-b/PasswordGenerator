package generator;

import java.security.SecureRandom;
import java.util.Scanner;

/**
 * Generates a password of a specified length, with the option
 * to omit certain characters. Uses characters that can be typed
 * by a standard US keyboard layout.
 * 
 * @author Edward B.
 */
public class PasswordGenerator {
	
	private static Scanner input;
	private static String lowercaseLetters;
	private static String uppercaseLetters;
	private static String numbers;
	private static String symbols;
	private static String characterList;
	private static String omittedCharacters;
	private static String generatedPassword;
	private static int passwordLength;
	private static boolean continueGeneration;
	
	
	public static void main(String args []) {
		
		initializeFields();
		
		while(continueGeneration) {
			setPasswordLength(input);
			setOmittedCharacters(input);
			generatePassword(passwordLength, omittedCharacters);
			updateLoop(input);
		}
		
		input.close();
	}
	
	/**
	 * Initializes every field.
	 */
	
	private static void initializeFields() {
		input = new Scanner(System.in);
		
		lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
		uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		numbers = "1234567890";
		symbols = "`-=~!@#$%^&*()_+[]\\{}|;':\",./<>?";
		characterList = lowercaseLetters + uppercaseLetters
				+ numbers + symbols;
		
		omittedCharacters = "";
		generatedPassword = "";
		passwordLength = 0;
		
		continueGeneration = true;
	}
	
	/**
	 * Takes keyboard input to set the length of the password.
	 * 
	 * @param in Keyboard input
	 */
	
	private static void setPasswordLength(Scanner in) {
		if(in != null) {			
			while(passwordLength <= 0) {
				System.out.println("Please input password length (must be a positive integer):");
				
				while(!in.hasNextInt()) {
					System.out.println("Invalid input, please input a positive integer");
					in.next();
				}
				
				passwordLength = in.nextInt();
			}
		}
	}
	
	/**
	 * Prompts user if they want to omit any characters
	 * and stores input as a string of characters to omit.
	 * 
	 * @param in Keyboard input
	 */
	
	private static void setOmittedCharacters(Scanner in) {
		if(in != null) {
			System.out.println("Omit any characters? (y/n)");
			
			String answer = in.next();
			
			while(!answer.equals("y") && !answer.equals("n")) {
				System.out.println("Invalid answer, please type \"y\" or \"n\"");
				answer = in.next();
			}
			
			if(answer.equals("y")) {
				System.out.println("Please input any characters to omit");
				omittedCharacters += in.next();
			}
		}
	}
	
	/**
	 * Generates a random password with the specified length
	 * and omitted characters.
	 * 
	 * @param length The password length
	 * @param omit Characters to be omitted
	 */
	
	private static void generatePassword(int length, String omit) {
		if(length > 0) {
			SecureRandom random = new SecureRandom();
			int charLength = characterList.length();
			char randomChar = 0;
			
			for(int i = 0; i < length; i++) {
				randomChar = characterList.charAt(random.nextInt(charLength - 1));
				
				while(omittedCharacters.indexOf(randomChar) >= 0) {
					randomChar = characterList.charAt(random.nextInt(charLength - 1));
				}
				
				generatedPassword += randomChar;
			}
			
			System.out.println("Generated password: " + generatedPassword);
		}
	}
	
	/**
	 * Prompts user if they want to continue generating passwords
	 * or stop and exit the program.
	 * 
	 * @param in Keyboard input
	 */
	
	private static void updateLoop(Scanner in) {
		if(in != null) {
			System.out.println("Generate a new password? (y/n)");
			
			String answer = in.next();
			
			while(!answer.equals("y") && !answer.equals("n")) {
				System.out.println("Invalid answer, please type \"y\" or \"n\"");
				answer = in.next();
			}
			
			if(answer.equals("y")) {
				passwordLength = 0;
				omittedCharacters = "";
				generatedPassword = "";
			}
			else if (answer.equals("n")) {
				continueGeneration = false;
			}
		}
	}
}
