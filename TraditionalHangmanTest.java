package HangmanGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

class TraditionalHangmanTest {
	
	TraditionalHangman traditionalHangman;
	
	@BeforeEach
	void setUp() throws Exception {
		String path;
		traditionalHangman = new TraditionalHangman(path);
	}
	
	@Test
	void testGetGameType() {
		// test case: "traditional"
		
	}
	
	@Test
	void testIsCorrect() {
		// test case 1: correct
		
		// test case 2: incorrect
		
	}

}
