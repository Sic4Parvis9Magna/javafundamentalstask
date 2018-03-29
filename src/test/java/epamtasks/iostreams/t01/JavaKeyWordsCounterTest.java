package epamtasks.iostreams.t01;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.apache.logging.log4j.LogManager.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JavaKeyWordsCounterTest {

    private static final Logger log = getLogger(JavaKeyWordsCounterTest.class);

    @Test
    void readContentTest() {
        String fileName = "JavaKeyWords.txt";
        JavaKeyWordsCounter javaKeyWordsCounter = new JavaKeyWordsCounter();
        assertTrue(javaKeyWordsCounter.readContent(fileName));
        System.out.println(javaKeyWordsCounter.getContent().toString());
        javaKeyWordsCounter.clearContent();
        String fileName2 = "SimpleJavaCode.txt";
        assertTrue(javaKeyWordsCounter.readContent(fileName2));
        System.out.println(javaKeyWordsCounter.getContent().toString());
    }

    @Test
    void  initialiseWordMapTest(){
        JavaKeyWordsCounter javaKeyWordsCounter = new JavaKeyWordsCounter();
        assertTrue(javaKeyWordsCounter.readContent("SimpleJavaCode.txt"));
        System.out.println( javaKeyWordsCounter.getContent().toString());
        System.out.println("Mapping result");
        javaKeyWordsCounter.initialiseWordMap("JavaKeyWords.txt");
        assertTrue(javaKeyWordsCounter.getWordMap().containsKey("return"));
        assertEquals(new Integer(3),javaKeyWordsCounter.getWordMap().get("return"));
    }
    @Test
    void uploadMapToFileText(){
        String newfile = "MapOutput.txt";
       JavaKeyWordsCounter javaKeyWordsCounter = new JavaKeyWordsCounter();
        assertTrue(javaKeyWordsCounter.readContent("SimpleJavaCode.txt"));
        javaKeyWordsCounter.initialiseWordMap("JavaKeyWords.txt");
        assertTrue(javaKeyWordsCounter.uploadMapToFile(newfile));

    }
    @Test
    void loggerFormatTest(){
        String ERROR_FORMAT ="error:{}, appears from:{} " ;
        log.info(ERROR_FORMAT,"trow","new error");
    }
}