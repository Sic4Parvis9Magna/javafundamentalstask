package epamtasks.iostreams.t02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaKeyWordsCounterChTest {

    @Test
    void readContentTest() {
        JavaKeyWordsCounterCh jc = new JavaKeyWordsCounterCh();
        assertTrue(jc.readContent("SimpleJavaCode.txt"));
        System.out.println( jc.getContent().toString());
    }

    @Test
    void initialiseWordMapTest(){
        JavaKeyWordsCounterCh jc = new JavaKeyWordsCounterCh();
        assertTrue(jc.readContent("SimpleJavaCode.txt"));
        jc.initialiseWordMap("JavaKeyWords.txt");
        assertTrue(jc.getWordMap().containsKey("return"));
        assertEquals(new Integer(3),jc.getWordMap().get("return"));
        System.out.println(jc.getWordsMapAsString());
    }

    @Test
    void uploadTofileTest(){
        JavaKeyWordsCounterCh jc = new JavaKeyWordsCounterCh();
        assertTrue(jc.readContent("SimpleJavaCode.txt"));
        jc.initialiseWordMap("JavaKeyWords.txt");
        assertTrue( jc.uploadMapToFile("CharMapOutput.txt"));
        jc.clearContent().clearWordMap();

        assertTrue(jc.readContent("CharMapOutput.txt"));
        jc.initialiseWordMap("JavaKeyWords.txt");
        assertTrue(jc.getWordMap().containsKey("return"));
        assertEquals(new Integer(1),jc.getWordMap().get("return"));

    }
}