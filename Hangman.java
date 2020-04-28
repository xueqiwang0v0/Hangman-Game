package HangmanGame;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;package HangmanGame;

import java.util.Random;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Map;

public class HangmanGame {
	
	/**
	 * "mode": true means traditionalHangman, false means evilHangman.
	 */
	private boolean mode;
	
	/**
	 * The constructor randomly selects the game mode.
	 */
	HangmanGame(){
		Random r = new Random();
		// randomly select the mode of hangman game
		this.mode = r.nextBoolean();
	}
	
	/**
	 * Main method of the Hangman game.
	 * @param args
	 */
	public static void main(String[] args) {
		// the state show whether to play again
		boolean again = true;
		
		Scanner scan = new Scanner(System.in);
		// keep playing if the user wants
		while(again) {
			// initialize a new hangman game
			HangmanGame newGame = new HangmanGame();
			
			String path = "/Users/xueqiwang/OneDrive - PennO365/UPenn/2nd semester/CIT590/Homework/HW9/words.txt";
			
			// traditional hangman mode
			if(newGame.mode == true)
				newGame.traditionalHangmanGame(scan, path);
			// evil hangman mode
			else
				newGame.evilHangmanGame(scan, path);
			
			// ask the user whether to play again
			again = newGame.playAgain(scan);
		}
		
		scan.close();
	}
	
	/**
	 * This method is a new traditional hangman game.
	 * The method contains the whole game.
	 * @param scan
	 * @param path of the txt file
	 */
	void traditionalHangmanGame(Scanner scan, String path) {
		TraditionalHangman newTraditional = new TraditionalHangman(path);
		
		String ch;     // user's input
		boolean valid; // whether the user's input is correct
		
		boolean over = false;
		while(over == false)
		{
			// get the user's guess
			ch = newTraditional.getUserGuess(scan);
			
			// check the guess
			valid = newTraditional.isCorrect(ch);
			
			// print incorrect guesses if the current guess is incorrect
			if(valid == false)
				newTraditional.incorrectGuesses(ch);
			
			// show current known word
			newTraditional.printKnownWord();
			
			// check whether the game is over
			over = newTraditional.isGameOver();
		}
		
		// show the results
		newTraditional.showFinalResults();
		
	}
	
	/**
	 * This method is a new evil hangman game.
	 * The method contains the whole game.
	 * @param scan
	 * @parat path of txt file
	 */
	void evilHangmanGame(Scanner scan, String path) {
		EvilHangman newEvil = new EvilHangman(path);
		
		String ch;     // user's input
		boolean valid; // whether the user's input is correct
		boolean over = false;
		while(over == false)
		{
			// get the user's guess
			ch = newEvil.getUserGuess(scan);
			
			// get the new words family
			
			newEvil.getWordsFamily(ch);
			
			
			System.out.println(newEvil.wordsFamily);
			
			// check the guess
			valid = newEvil.isCorrect(ch);
			
			// print incorrect guesses if the current guess is incorrect
			if(valid == false)
				newEvil.incorrectGuesses(ch);
			
			// show current known word
			newEvil.printKnownWord();
			
			// check whether the game is over
			over = newEvil.isGameOver();
		}
		
		
		// show the results
		newEvil.showFinalResults();
	}
	
	/**
	 * This method returns true if the user wants to play again.
	 * @param scan
	 * @return again (true/false)
	 */
	boolean playAgain(Scanner scan) {
		
		boolean valid = false;
		boolean again = false;
		
		while(valid == false) {
			System.out.println("Do you want to play again? y/n");
			String ans = scan.next();
			if(ans.startsWith("y") || ans.startsWith("Y")) {
				valid = true;
				again = true;
			}
			else if(ans.startsWith("n") || ans.startsWith("N")) {
				valid = true;
				again = false;
			}
		}
		return again;
	}
	
	
}


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
