package HangmanGame;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * This class describes traditional hangman game.
 * Once the word is selected, it will not change.
 * @author Xueqi Wang, Weijie Qi
 */
public class TraditionalHangman extends Hangman {
	
	/**
	 * "word": a randomly selected word for user to guess.
	 */
	public String word;
	
	/**
	 * "incorrectGuesses": a String list that stores all the incorrect guesses.
	 */
	public List<String> incorrectGuesses = new ArrayList<>();
	
	/**
	 * This constructor initializes a traditional hangman game.
	 * @param path of the txt file
	 */
	TraditionalHangman(String path){
		// get the clean words list
		super(path);
		
		// get the random word
		this.word = this.getRandomWord();
		
		// set the known word to "_____"
		this.knownWord = new String[this.word.length()];
		for(int i=0; i<this.getWordLength(); i++)
			this.knownWord[i] = "_ ";
		
		// print the original known word
		this.printKnownWord();
		
		// initialize the correct array
		this.correct = new boolean[this.word.length()];
	}
	
	// Methods
	/**
	 * This method randomly select a word from CleanWords list.
	 * @return RandomWord (String)
	 */
	String getRandomWord() {
		
		// get a random index
		Random r = new Random();
		int index = r.nextInt(this.cleanWords.size());
		
		// find the corresponding word
		String RandomWord = this.cleanWords.get(index);
		
		return RandomWord;
	}
	
	/**
	 * This methods returns the type of current game.
	 * @return "traditional"
	 */
	String getGameType() {
		return "traditional";
	}
	
	/**
	 * This method returns the length of the word.
	 * @return length
	 */
	int getWordLength() {
		int length = this.word.length();
		return length;
	}
	
	/**
	 * This method returns true when a character is a correct guess.
	 * This method also updates the correct array, known word and mistakes.
	 * @param ch
	 * @return true/false
	 */
	public boolean isCorrect(String ch) {
		
		for(int i=0; i<this.getWordLength(); i++) {
			if(this.correct[i] == false && ch.charAt(0) == this.word.charAt(i))
			{
				// update correct array
				this.correct[i] = true;
				
				// update known word
				this.knownWord[i] = ch;
				
				return true;
				}
		}
		
		// update the mistakes
		this.mistakes += 1;
		return false;
	}
	
	/**
	 * This method shows all the incorrect guesses and add 
	 * last incorrect guess to the incorrectGuesses array.
	 * @param ch
	 */
	void incorrectGuesses(String ch) {
		
		// store the incorrect guess
		this.incorrectGuesses.add(ch);
		
		// show all the incorrect guesses
		System.out.print("Incorrect Guesses: ");
		for(int i=0; i<this.incorrectGuesses.size(); i++)
			System.out.print(this.incorrectGuesses.get(i) + " ");
		
		System.out.print("\n");
		
	}
}
