package HangmanGame;

import java.util.Random;

/**
 * This class describes evil hangman game.
 * The word is always changing.
 * @author Xueqi Wang, Weijie Qi
 */

public class EvilHangman extends Hangman {
	/**
	 * This methods returns the type of current game.
	 * @return "evil"
	 */
	String getGameType() {
		return "evil";
	}
	
	/**
	 * This method returns the length of the word.
	 * @return length
	 */
	int getWordLength() {
		Random r = new Random();
		int length = r.nextInt();
		return length;
	}
}
