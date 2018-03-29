package epamtasks.iostreams.t03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WizardEncoderTest {

    @Test
    void readFromFileTest1() {
        WizardEncoder we = new WizardEncoder();
        assertEquals("UTF-8",we.getEncoding());
        assertTrue( we.readFromFile("SimpleJavaCode.txt"));
        assertTrue(we.getContent().toString().contains("return"));
        System.out.println(we.getContent().toString());
    }
    @Test
    void writeToFileTest(){
        WizardEncoder we = new WizardEncoder();
        assertEquals("UTF-8",we.getEncoding());
        assertTrue( we.readFromFile("SimpleJavaCode.txt"));
        assertTrue(we.getContent().toString().contains("return"));
        System.out.println(we.getContent().toString());
        we.setEncoding("UTF-16");
        assertTrue(we.writeToFile("TextUFT-16.txt"));
    }
    @Test
    void readFromFileTest2() {
        WizardEncoder we = new WizardEncoder();
        assertEquals("UTF-8",we.getEncoding());
        we.setEncoding("UTF-16");
        assertTrue(we.readFromFile("TextUFT-16.txt"));
        assertTrue(we.getContent().toString().contains("return"));
        System.out.println(we.getContent().toString());
    }
}