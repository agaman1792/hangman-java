package main;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
	private String[] wordlist;
	private int maxAttempts;
	private int score;
	
	private GameState state;
	
	public Game(String[] wordlist, int maxAttempts) {
		this.maxAttempts = maxAttempts;
		this.state = new GameState(wordlist);
		
		this.score = 0;
	}
	
	public void start() throws IOException {
		System.out.println("Game started, max attempts " + this.maxAttempts);
		System.out.println("Please enter a key");
		
		Scanner scanner = new Scanner(System.in);
		boolean isGuessed = false;
		
		while (!isGuessed && this.state.getMisses().size() < this.maxAttempts) {
			char c = scanner.next().charAt(0);
			System.out.println("You selected letter " + c);
			this.onLetterSelected(c);
			
			
			
			if (this.isWordGuessed(this.state.getSelectedWord(), this.state.getGuesses())) {
				isGuessed = true;
			}
		}
		
		if (isGuessed) {
			this.score += 10;
			System.out.println("Congratulations, you guessed the word!");
			System.out.println("Score: " + this.score);
		}
		
		
		scanner.close();
	}
	
	public void onLetterSelected(char letter) {
		if (this.state.getGuesses().contains(letter)) {
			System.out.println("You already guessed this letter. Try again");
			return;
		}
		
		if (this.state.getMisses().contains(letter)) {
			System.out.println("You already missed this letter. Try again");
			return;
		}
		
		if (this.isInSelectedWord(letter, this.state.getSelectedWord())) {
			this.state.addGuessedLetter(letter);
		} else {
			this.state.addMissedLetter(letter);
		}
	}
	
	private void printGameUI() {
		System.out.println("Attempts left: " + (this.maxAttempts - this.state.getMisses().size()));
		System.out.println("Word: " + this.state.getSelectedWord());
		
		System.out.print("Guesses: ");
		for (Character guess : this.state.getGuesses()) {
			System.out.print(guess + ", ");
		}
		System.out.println();
		
		System.out.print("Misses: ");
		for (Character miss : this.state.getMisses()) {
			System.out.print(miss + ", ");
		}
		System.out.println();
	}
	
	private boolean isInSelectedWord(char letter, String word) {
		return word.indexOf(letter) > -1;
	}
	
	private boolean isWordGuessed(String word, LinkedList<Character> guesses) {
		HashSet<Character> comparer = new HashSet<Character>();
		
		for (char character : word.toCharArray()) {
			comparer.add(character);
		}
		
		return new HashSet<>(guesses).equals(comparer);
	}
}