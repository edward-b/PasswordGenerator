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
	private static int passwordLength;
	private static boolean continueGeneration;
	
	
	public static void main(String args []) {
		
		initializeFields();
		
		while(continueGeneration) {
			setPasswordLength(input);
			
			continueGeneration = false;
		}
		
		input.close();
	}
	
	private static void initializeFields() {
		input = new Scanner(System.in);
		
		lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
		uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		numbers = "1234567890";
		symbols = "`-=~!@#$%^&*()_+[]\\{}|;':\",./<>?";
		
		characterList = lowercaseLetters + uppercaseLetters
				+ numbers + symbols;
		omittedCharacters = "";
		passwordLength = 0;
		
		continueGeneration = true;
	}
	
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
}