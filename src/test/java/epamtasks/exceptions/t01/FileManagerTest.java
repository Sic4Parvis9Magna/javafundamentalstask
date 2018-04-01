package epamtasks.exceptions.t01;

import epamtasks.exceptions.t01.FileManager;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    void initialiseWithPathTest() {
        FileManager fm;
        String file="D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\JavaKeyWords.txt";
        String dir = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask";
        String dir1 = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src\\test\\java\\epamtasks\\oop";
        fm = FileManager.getDefaultManager();
        assertFalse(fm == null);
        System.out.println(fm.getCurrentPath());
        System.out.println(fm.getDispFiles());
        fm = FileManager.initialiseWithPath(file);

        System.out.println(fm.getCurrentPath());
        assertEquals(dir,fm.getCurrentPath());
        System.out.println("Files and dirs:\n");
        System.out.println(fm.getDispFiles());

        fm = FileManager.initialiseWithPath(dir);
        assertEquals(dir,fm.getCurrentPath());
        System.out.println(fm.getCurrentPath());
        System.out.println("Files and dirs:\n");
        System.out.println(fm.getDispFiles());

        fm = FileManager.initialiseWithPath(dir1);
        assertEquals(dir1,fm.getCurrentPath());
        System.out.println(fm.getCurrentPath());
        System.out.println("Files and dirs:\n");
        System.out.println(fm.getDispFiles());

    }

    @Test
    void goToDirTest(){
        String statrtDir = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src"+
                                "\\test\\java\\epamtasks\\oop";
        String dest = "\\t02";
        String endDir = statrtDir+dest;
        FileManager fm = FileManager.initialiseWithPath(statrtDir);
        assertEquals(statrtDir,fm.getCurrentPath());
        System.out.printf("Old dir files:%n%s",fm.getDispFiles());
        assertTrue(fm.goToDir(dest));
        assertEquals(endDir,fm.getCurrentPath());
        System.out.printf("New dir files:%n%s",fm.getDispFiles());

        assertFalse(fm.goToDir("\\t03"));
        System.out.printf("Old dir files:%n%s",fm.getDispFiles());
    }
    @Test
    void toPreviousDirTest(){
        String statrtDir = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src"+
                "\\test\\java\\epamtasks\\oop";
        String endDir = "D:\\Max\\JavaTut\\EPAM\\javafundamentalstask\\src"+
                "\\test\\java\\epamtasks";
        FileManager fm = FileManager.initialiseWithPath(statrtDir);
        assertEquals(statrtDir,fm.getCurrentPath());
        System.out.println(fm.getCurrentPath());
        System.out.printf("Curent dir files:%n%s",fm.getDispFiles());
        assertTrue(fm.toPreviousDir());
        assertEquals(endDir,fm.getCurrentPath());
        System.out.println(fm.getCurrentPath());
        System.out.printf("Previous dir files:%n%s",fm.getDispFiles());
    }
    @Test
    void makeDir(){
        String statrtDir = "D:\\Max\\JavaTut\\ForFileLearning";
        String dirName = "myNewDir";
        String endDir = statrtDir+"\\"+dirName;
        FileManager fm = FileManager.initialiseWithPath(statrtDir);
        assertEquals(statrtDir,fm.getCurrentPath());
        System.out.println(fm.getCurrentPath());
        System.out.printf("Curent dir files:%n%s",fm.getDispFiles());
        assertTrue(fm.makeDir(dirName));
        System.out.println(fm.getCurrentPath());
        System.out.printf("New dir files:%n%s",fm.getDispFiles());
        System.out.println(File.separator);
    }

    @Test
    void createTextFileTest(){
        String statrtDir = "D:\\Max\\JavaTut\\ForFileLearning";
        String fileName = "newfile.txt";
        FileManager fm = FileManager.initialiseWithPath(statrtDir);
        assertEquals(statrtDir,fm.getCurrentPath());
        System.out.println(fm.getCurrentPath());
        System.out.printf("Curent dir files:%n%s",fm.getDispFiles());
        assertTrue(fm.createFile(fileName));
        System.out.printf("%nudated dir files:%n%s",fm.getDispFiles());

    }

    @Test
    void deleteFileTest(){
        String statrtDir = "D:\\Max\\JavaTut\\ForFileLearning";
        String fileName = "newfile2.txt";
        FileManager fm = FileManager.initialiseWithPath(statrtDir);
        assertEquals(statrtDir,fm.getCurrentPath());
        System.out.println(fm.getCurrentPath());
        System.out.printf("Curent dir files:%n%s",fm.getDispFiles());
        assertTrue(fm.createFile(fileName));
        System.out.printf("%nudated dir files:%n%s",fm.getDispFiles());
        assertTrue(fm.deleteFile(fileName));
        System.out.printf("%nudated dir files:%n%s",fm.getDispFiles());
        assertFalse(fm.deleteFile("notExisting.txt"));

    }

    @Test
    void readFromWriteToFileTest(){
        String myMessage = "it's a simple string";
        String statrtDir = "D:\\Max\\JavaTut\\ForFileLearning";
        String fileName = "MyText1.txt";
        FileManager fm = FileManager.initialiseWithPath(statrtDir);
        assertEquals(statrtDir,fm.getCurrentPath());
        System.out.println(fm.getCurrentPath());
        System.out.printf("Curent dir files:%n%s",fm.getDispFiles());
        assertTrue(fm.createFile(fileName));
        System.out.printf("%nudated dir files:%n%s",fm.getDispFiles());
        assertTrue(fm.writeFoFile(fileName,myMessage));
        assertTrue(fm.readFromFile(fileName));
        System.out.println(fm.getContent());
        assertEquals(myMessage,fm.getContent().trim());


    }

    @Test
    void appendToFileTest(){
        String myMessage = " new message \n";
        String statrtDir = "D:\\Max\\JavaTut\\ForFileLearning";
        String fileName = "MyText.txt";
        FileManager fm = FileManager.initialiseWithPath(statrtDir);
        assertEquals(statrtDir,fm.getCurrentPath());
        System.out.println(fm.getCurrentPath());
        System.out.printf("Curent dir files:%n%s",fm.getDispFiles());
        assertTrue(fm.appendToFile(fileName,myMessage));
        assertTrue(fm.readFromFile(fileName));
        System.out.println(fm.getContent());
        assertTrue(fm.getContent().contains(myMessage));
    }

}