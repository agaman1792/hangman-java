package main;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] wordlist = new String[] {"abstract", "boolean", "break", "byte", "case", "catch", "continue", "default", "double", "enum", "finally", "float", "implements", "import", "interface", "long", "private", "protected", "public", "static", "volatile"};
		int maxAttempts = 3;
		
		Game game = new Game(wordlist, maxAttempts);
		game.start();
	}

}
