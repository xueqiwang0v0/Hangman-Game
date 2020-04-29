package HangmanGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class EvilHangmanTest {
	
	EvilHangman evilHangman;
	
	@BeforeEach
	void setUp() throws Exception {
		String path;
		evilHangman = new EvilHangman(path);
	}
	
	@Test
	void testGetGameType() {
		// test case: "evil"
		
	}
	
	@Test
	void testIsCorrect() {
		// test case 1: correct
		
		// test case 2: incorrect
		
	}
	
	@Test
	void testGetWordsFamilyIni() {
		// test case 1: normal case
		
		// test case 2: normal case
		
	}
	
	@Test
	void testGetWordsFamily() {
		// test case 1: normal case
		
		// test case 2: normal case
		
	}
	
	@Test
	void testGetLargestWordsFamily() {
		// test case 1: only 1 largest words family
		
		// test case 2: 2 or more words families are both the largest
		
		// test case 3: only 1 word family
		
	}

}
