package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private static Scanner scanner = new Scanner(System.in);
	
	private String[] wordlist;
	private int maxAttempts;
	private int score;
	
	private GameState state;
	
	public Game(String[] wordlist, int maxAttempts) {
		this.maxAttempts = maxAttempts;
		this.wordlist = wordlist;
		
		this.score = 0;
	}
	
	public void start() throws IOException {
		boolean continueGame = true;
		
		while (continueGame) {
			this.state = new GameState(wordlist);
			
			this.mainGameLoop();
			
			ArrayList<String> wlist = new ArrayList<String>(Arrays.asList(this.wordlist));
			int idx = wlist.indexOf(this.state.getSelectedWord());
			wlist.remove(idx);
			this.wordlist = wlist.toArray(new String[wlist.size()]);
			
			System.out.println("Select 'N' or 'n' to exit or any other key to continue, followed by ENTER");
			char choice = scanner.next().charAt(0);
			
			if (choice == 'n' || choice == 'N') {
				continueGame = false;
			}
		}
		
		scanner.close();
	}
	
	private void mainGameLoop() throws IOException {
		
		boolean isGuessed = false;
		
		while (!isGuessed && this.state.getMisses().size() < this.maxAttempts) {
			
			System.out.print("Mysterious word: ");
			this.printSelectedWord(this.state.getSelectedWord(), this.state.getGuesses());
			System.out.println(" length " + this.state.getSelectedWord().length());
			
			System.out.print("Choose a letter and press ENTER (for multiple letters, we will choose the first): ");
			char c = scanner.next().charAt(0);
			this.onLetterSelected(c);
			
			this.printGameUI();
			
			if (this.isWordGuessed(this.state.getSelectedWord(), this.state.getGuesses())) {
				isGuessed = true;
			}
		}
		
		if (isGuessed) {
			this.score += 10;
			System.out.println("Congratulations, you guessed the word!");
		}
		
		if (!isGuessed) {
			System.out.println("Too bad, please try again");
		}
		
		System.out.println("Score: " + this.score);
		System.out.println();
		System.out.println("-------------------------------");
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
		System.out.println();
		System.out.println("Attempts left: " + (this.maxAttempts - this.state.getMisses().size()));
		
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
		System.out.println("=========================================================================");
		System.out.println();
	}
	
	private void printSelectedWord(String word, LinkedList<Character> guesses) {
		for (char letter: word.toCharArray()) {
			if (guesses.contains(letter)) {
				System.out.print(letter);
			} else {
				System.out.print("-");
			}
		}
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