package main;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		String[] wordlist = new String[] {"abstract", "boolean", "break", "byte", "case", "catch", "continue", "default", "double", "enum", "finally", "float", "implements", "import", "interface", "long", "private", "protected", "public", "static", "volatile"};
		int maxAttempts = 3;
		
		System.out.println("Wordlist length: " + wordlist.length);
		System.out.print("Before starting the game, select the maximum number of failed attempts: ");
		maxAttempts = scanner.nextInt();
		
		System.out.println();
		System.out.println("Game starting, good luck");
		System.out.println();
		
		Game game = new Game(wordlist, maxAttempts);
		game.start();
	}

}
