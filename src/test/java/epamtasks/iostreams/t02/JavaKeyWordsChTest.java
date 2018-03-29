package epamtasks.iostreams.t02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaKeyWordsChTest {

    @Test
    void initialiseWords() {
        JavaKeyWordsCh ch = new JavaKeyWordsCh();
        assertTrue(ch.initialiseWords("JavaKeyWords.txt"));
        assertTrue(ch.getWordSet().contains("return"));
        ch.printWords();
    }
}