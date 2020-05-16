package HangmanGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
/**
 * This is the JUnit test class for TraditionalHangman.
 * @author Xueqi Wang, Weijie Qi
 */
class TraditionalHangmanTest {
	
	TraditionalHangman traditionalHangman;
	
	@BeforeEach
	void setUp() throws Exception {
		String path = "words.txt";
		traditionalHangman = new TraditionalHangman(path);
	}
	
	@Test
	void testGetGameType() {
		// test case: "traditional"	
		assertEquals("traditional", traditionalHangman.getGameType());	
	}
	
	@Test
	void testIsCorrect() {
		// test case 1: correct
		traditionalHangman.word = "qwe";
		assertTrue(traditionalHangman.isCorrect("w"));
		// test case 2: incorrect
		traditionalHangman.word = "qwe";
		assertFalse(traditionalHangman.isCorrect("a"));
	}

}
