package HangmanGame;

import java.util.Random;
import java.util.Scanner;

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
		//this.mode = r.nextBoolean();
		this.mode = true;
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
			System.out.println("Do you want to play again? y/n");
			String ans = scan.next();
			if(ans.startsWith("y") || ans.startsWith("Y"))
				again = true;
			else if(ans.startsWith("n") || ans.startsWith("N"))
				again = false;
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
		
	}
	
	
	
}
