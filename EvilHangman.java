package HangmanGame;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class describes evil hangman game.
 * The word is always changing.
 * @author Xueqi Wang, Weijie Qi
 */

public class EvilHangman extends Hangman {
	
	/**
	 * "wordsFamily": the word family related to the random word.
	 */
	public List<String> wordsFamily = new ArrayList<>();
	
	/**
	 * "shownFamily": several words families after the user inputs a character.
	 * The key is the known pattern such as "_ e e _".
	 * The value is a list of words that belong to the family.
	 */
	Map<String, List<String>> shownFamily = new HashMap<String, List<String>>();
	
	/**
	 * This constructor initializes an evil hangman game.
	 * @param path of the file
	 */
	EvilHangman(String path){
		// initialize the game
		super(path);
		
		// initialize the words family
		this.getWordsFamilyIni();
	}
	
	// Methods
	/**
	 * This method initializes the words family before guessing.
	 */
	void getWordsFamilyIni() {
		for(int i=0; i<this.cleanWords.size(); i++) {
			// add the word if the length equals
			if(this.cleanWords.get(i).length() == this.getWordLength())
				this.wordsFamily.add(this.cleanWords.get(i));
		}
	}
	
	/**
	 * This method updates the words family when the user inputs a guess.
	 * @param user's guess ch
	 */
	void getWordsFamily(String ch) {
		// reset the shown Family
		this.shownFamily.clear();
		
		// iterate over the exist words family
		String[] pattern;
		String word;
		String key;  // the known word pattern
		List<String> value; // words that belong to a words family
		for(int i=0; i<this.wordsFamily.size(); i++) {
			// reset the parameters
			key = "";
			pattern = this.knownWord.clone();
			word = this.wordsFamily.get(i);
			value = new ArrayList<>();
			// check each char of the word
			for(int j=0; j<this.getWordLength(); j++) {
				// check whether the char has been guessed
				if(pattern[j].contains("_")) {
					// update the pattern of the given word
					if(ch.charAt(0) == word.charAt(j))
						pattern[j] = ch;
				}
			}
			
			// get the key
			for(int k=0; k<pattern.length; k++)
				key += pattern[k];
			// add the word to the word family with key pattern
			if(!this.shownFamily.containsKey(key)) {
				value.add(word);
				this.shownFamily.put(key, value);
			}
			else {
				value = this.shownFamily.get(key);
				value.add(word);
				this.shownFamily.replace(key, value);
			}

		}
		
		// find the largest words family
		String largest;
		largest = this.getLargestWordsFamily(this.shownFamily);
		
		// get the new words family
		List<String> newWordsFamily = this.shownFamily.get(largest);
		
		// update the word family and the knownword
		this.wordsFamily.clear();
		this.wordsFamily = newWordsFamily;
		
		// update the shown word
		for(int i=0; i<this.knownWord.length; i++)
			this.knownWord[i] = String.valueOf(largest.charAt(i));
		
	}
	
	/**
	 * This method finds the largest words family and returns the key.
	 * @param map
	 * @return key of the largest word family
	 */
	 String getLargestWordsFamily(Map<String, List<String>> map) {
		int max = 0;
		int freq; // the volume of the words family
		String key = null;
		for(Entry<String, List<String>> entry: map.entrySet()) {
			//System.out.println("key: "+entry.getKey()+" value: "+entry.getValue());
			freq = entry.getValue().size();
			// update the maximum volume of word family
			if(freq > max) {
				max = freq;
				key = entry.getKey();
			}
		}
		return key;
	}
	
	/**
	 * This method returns true when a character is a correct guess.
	 * This method also updates the correct array and mistakes.
	 * @param ch
	 * @return correct (true/false)
	 */
	public boolean isCorrect(String ch) {
		
		boolean correct = false;
		
		for(int i=0; i<this.knownWord.length; i++) {
			if(this.knownWord[i].contains(ch))
			{
				// update correct array
				this.correct[i] = true;
				correct = true;
				}
		}
		
		// update the mistakes
		if(correct == false)
			this.mistakes += 1;
		return correct;
	}
	
	/**
	 * This methods returns the type of current game.
	 * @return "evil"
	 */
	String getGameType() {
		return "evil";
	}
}
