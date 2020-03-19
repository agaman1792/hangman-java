package main;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		String[] wordlist = new String[] {"asdf", "asdwerqa", "dawd", "dsfsadfes", "efsdfas", "dsffsadfds", "dsfsdffds", "dsfsdffds", "fdsfdsdsf", "frerfr"};
		int maxAttempts = 3;
		
		Game game = new Game(wordlist, maxAttempts);
		game.start();
	}

}
