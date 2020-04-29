package HangmanGame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the JUnit test class for Hangman.
 * @author Xueqi Wang, Weijie Qi
 *
 */
class HangmanTest {
	
	Hangman hangman;
	
	@Test
	void testGetRandomWord() {
		// test case 1: normal case
		
		// test case 2: normal case
		
		// test case 3: normal case
		
		
	}
	
	@Test
	void testGetCleanWords() {
		String path;
		// test case: clean word txt
	}

	@Test
	void isClean() {
		// test case 1: upper case letters
		
		// test case 2: abbreviations
		
		// test case 3: apostrophe
		
		// test case 4: hyphen
		
		// test case 5: compound word
		
		// test case 6: digit
		
	}
	
	@Test
	void testGetWordLength() {
		// test case 1: normal case
		
		// test case 2: normal case
		
		// test case 3: normal case
	}
	
	@Test
	void testContainsDigit() {
		// test case 1: contains digit
		
		// test case 2: does not contain digit
		
	}
	
	@Test
	void testGetUserGuess() {
		// test case 1: valid input
		
		// test case 2: invalid input
		
		// test case 3: invalid input
		
	}
	
	@Test
	void testIncorrectGusses() {
		// test case 1: normal case
		
		// test case 2: normal case
	}
	
	@Test
	void testIsGameOver() {
		// test case 1: game over
		
		// test case 2: game not over
		
	}
}
