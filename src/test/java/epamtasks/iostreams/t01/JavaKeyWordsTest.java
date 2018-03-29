package epamtasks.iostreams.t01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaKeyWordsTest {

    @Test
    void initialiseWordsTest() {
        JavaKeyWords javaKeyWords = new JavaKeyWords();
        String fileName = "JavaKeyWords.txt";
        assertTrue(javaKeyWords.initialiseWords(fileName));
        assertEquals(53,javaKeyWords.getWordSet().size());
        assertTrue(javaKeyWords.getWordSet().contains("true"));
        assertTrue(javaKeyWords.getWordSet().contains("class"));
        assertTrue(javaKeyWords.getWordSet().contains("final"));
        assertTrue(javaKeyWords.getWordSet().contains("finally"));
        javaKeyWords.printWords();
    }
}