package HangmanGame;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class describes a general word guess game hangman.
 * @author Xueqi Wang, Weijie Qi
 *
 */
public abstract class Hangman {
	/**
	 * "cleanWords": a String list of available words.
	 */
	public List<String> cleanWords = new ArrayList<>();
	
	/**
	 * "guesses": the times user guesses.
	 */
	private int guesses;
	
	/**
	 * "mistakes": the times user guesses wrong.
	 */
	public int mistakes;
	
	/**
	 * "knowWord": the known part of the word.
	 */
	public String[] knownWord;
	
	/**
	 * "correct": an array of booleans that represents whether the character has been guessed or not.
	 */
	public boolean[] correct;
	
	/**
	 * This constructor initializes a hangman game and the words list.
	 * @param path of the txt file
	 */
	Hangman(String path){
		
		// get the clean words
		this.cleanWords = this.getCleanWords(path);
		
		// set the counters
		this.guesses = 0;
	}
	
	// Method
	/**
	 * This method reads the file and get the clean words.
	 * @param path
	 * @return words (a String List)
	 */
	public List<String> getCleanWords(String path){
		
		List<String> words = new ArrayList<>();
		
		// read the file
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			// read each line of the file
			String line = br.readLine().strip();
			while(line != null) {
				// add the word to the list if it is clean
				if(this.isClean(line) == true)
					words.add(line);
				// read next line
				line = br.readLine();
			}
			
			br.close();
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}

		return words;
	}
	
	/**
	 * This method checks whether a word is clean
	 * @param the word to be checked
	 * @return clean (true/false)
	 */
	private boolean isClean(String word) {
		boolean clean = true;
		// check upper case letters: Zambia
		if(Character.isUpperCase(word.charAt(0)) == true)
			clean = false;
		// check abbreviations: mrs.
		else if(word.contains("."))
			clean = false;
		// check apostrophe: you're
		else if(word.contains("'"))
			clean = false;
		// check hyphen: user-generated
		else if(word.contains("-"))
			clean = false;
		// check compound words: post office
		else if(word.contains(" "))
			clean = false;
		// check digit: 3D
		else if(this.ContainsDigit(word))
			clean = false;
		return clean;
	}
	
	/**
	 * This methods returns the type of current game.
	 * @return the type of hangman game
	 */
	abstract String getGameType();
	
	/**
	 * This method returns the length of word.
	 * @return length (int)
	 */
	abstract int getWordLength();
	
	// helper
	/**
	 * The method checks whether a String contains a digit.
	 * @param word
	 * @return true/false
	 */
	boolean ContainsDigit(String word) {
		// iterate each character
		for(int i=0; i<word.length(); i++) {
			// check if it is a digit
			if(Character.isDigit(word.charAt(i)) == true)
				return true;
		}
		return false;
	}
	
	/**
	 * This method print the known word.
	 */
	void printKnownWord() {
		for(int i=0; i<this.knownWord.length; i++)
			System.out.print(this.knownWord[i]);
		System.out.print("\n");
	}
	
	/**
	 * This method returns what user guesses and check whether it is valid.
	 * @param scan
	 * @return the character that user guesses
	 */
	String getUserGuess(Scanner scan) {
		
		System.out.println("Guess a letter.");
		
		String s = scan.next();
		String ch = Character.toString(s.charAt(0));
		
		// check whether the input is valid;
		while(this.isClean(ch) == false) {
			System.out.println("Invalid guess, please enter another guess.");
			s = scan.next();
			ch = Character.toString(s.charAt(0));
		}
		
		// update the guesses
		this.guesses += 1;
				
		return ch;
	}
	
	/**
	 * This method returns true when the game is over.
	 * When the user guesses all the characters of the
	 * word, the game is over.
	 * @return over (true/false)
	 */
	boolean isGameOver() {
		boolean over = true;
		
		// check the correct array
		for(int i=0; i<this.correct.length; i++) {
			if(correct[i] == false)
				over = false;
		}
		
		return over;
	}
	
	/**
	 * This method shows the game results.
	 */
	void showFinalResults() {
		// show the game is over
		System.out.println("The " + this.getGameType() + " hangman game is over.");
		
		// show the counters
		System.out.println("You guess " + this.guesses + " time(s).");
		System.out.println("You make " + this.mistakes + " mistake(s).");
	}
}
