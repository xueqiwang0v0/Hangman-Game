package HangmanGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
/**
 * This is the JUnit test class for EvilHangman.
 * @author Xueqi Wang, Weijie Qi
 */
class EvilHangmanTest {
	
	EvilHangman evilHangman;
	
	@BeforeEach
	void setUp() throws Exception {
		String path = "words.txt";
		evilHangman = new EvilHangman(path);
	}
	
	@Test
	void testGetGameType() {
		// test case: "evil"
		assertEquals("evil", evilHangman.getGameType());
	}
	
	@Test
	void testIsCorrect() {
		evilHangman.wordsFamily.clear();
		evilHangman.wordsFamily.add("one");
		evilHangman.word="one";
		
		// test case 1: correct
		evilHangman.getWordsFamily("o");
		assertTrue(evilHangman.isCorrect("o"));
		
		// test case 2: incorrect
		evilHangman.getWordsFamily("k");
		assertFalse(evilHangman.isCorrect("k"));
	}
	
	@Test
	void testGetWordsFamilyIni() {
		
		evilHangman.wordsFamily.clear();
		evilHangman.cleanWords.clear();
		evilHangman.cleanWords.add("one");
		evilHangman.cleanWords.add("two");
		evilHangman.cleanWords.add("three");
		
		// test case 1: normal case
		evilHangman.word="one";
		evilHangman.getWordsFamilyIni();
		assertEquals(2, evilHangman.wordsFamily.size());
		
		// test case 2: normal case
		evilHangman.word="three";
		evilHangman.wordsFamily.clear();
		evilHangman.getWordsFamilyIni();
		assertEquals(1, evilHangman.wordsFamily.size());
		
	}
	
	@Test
	void testGetWordsFamily() {
		// test case 1: normal case
		
		// test case 2: normal case
		
	}
	
	@Test
	void testGetLargestWordsFamily() {
		// test case 1: only 1 largest words family
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> com = new ArrayList<String>();
		com.add("test1_1");
		map.put("test1",com);
		List<String> com2 = new ArrayList<String>();
		com2.add("test2_1");
		com2.add("test2_2");
		map.put("test2",com2);
		assertEquals("test2",evilHangman.getLargestWordsFamily(map));
		
		// test case 2: 2 or more words families are both the largest
		com.add("test1_2");
		map.replace("test1", com);
		assertEquals("test2",evilHangman.getLargestWordsFamily(map));
		
		// test case 3: only 1 word family
		map.clear();
		map.put("test1",com);
		assertEquals("test1",evilHangman.getLargestWordsFamily(map));	
	}

}
