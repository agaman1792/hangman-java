package main;

import java.util.LinkedList;
import java.util.Random;

public class GameState {
	private static Random randomizer = new Random();
	
	private String selectedWord;
	private LinkedList<Character> guesses;
	private LinkedList<Character> misses;
	
	public GameState(String[] wordlist) {
		this.selectedWord = this.chooseValueFromWordlist(wordlist);
		this.guesses = new LinkedList<Character>();
		this.misses = new LinkedList<Character>();
	}
	
	private String chooseValueFromWordlist(String[] wordlist) {
		int index = randomizer.nextInt(wordlist.length - 1);
		return wordlist[index];
	}
	
	public void addGuessedLetter(char c) {
		this.guesses.add(c);
	}
	
	public void addMissedLetter(char c) {
		this.misses.add(c);
	}
	
	public String getSelectedWord() {
		return this.selectedWord;
	}
	
	public LinkedList<Character> getGuesses() {
		return this.guesses;
	}
	
	public LinkedList<Character> getMisses() {
		return this.misses;
	}
}
