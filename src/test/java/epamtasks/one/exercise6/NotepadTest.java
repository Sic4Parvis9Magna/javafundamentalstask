package epamtasks.one.exercise6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotepadTest {

    @Test
    void getRecordPosition() {
        Notepad notepad0 = new Notepad(5);
        assertEquals(-1,notepad0.getRecordPosition("666"));
        notepad0.addRecord("A")
                .addRecord("B")
                .addRecord("C");
        assertEquals(0,notepad0.getRecordPosition("A"));
        assertEquals(1,notepad0.getRecordPosition("B"));
        assertEquals(2,notepad0.getRecordPosition("C"));
    }

    @Test
    void addRecordAndGetRecordId() {
        Notepad notepad1 = new Notepad();
        assertEquals(-1,notepad1.getRecordId("666"));
        notepad1.addRecord("new record")
                .addRecord("second record")
                .addRecord("third record");
        assertEquals(-1,notepad1.getRecordId("788"));

       int id1 = notepad1.getRecordId("new record");
       assertEquals("new record",notepad1.getRecordWithId(id1));

        id1 = notepad1.getRecordId("second record");
        assertEquals("second record",notepad1.getRecordWithId(id1));

        id1 = notepad1.getRecordId("third record");
        assertEquals("third record",notepad1.getRecordWithId(id1));

    }

    @Test
    void removeFromPosition() {
        Notepad notepad2 = new Notepad(6);
        notepad2.addRecord("sunday") //0
                .addRecord("monday")
                .addRecord("thuesday")//2
                .addRecord("wednesday")
                .addRecord("thursday")//4
                .addRecord("friday")
                .addRecord("saturday");//6
        assertEquals(1,notepad2.getRecordPosition("monday"));
        assertEquals("friday",notepad2.getRecordOnPosition(5));
         int frId = notepad2.getRecordId("friday");

        notepad2.removeFromPosition(5);//remove "friday"
        assertNotEquals("friday",notepad2.getRecordOnPosition(5));
        assertEquals("NOT_FOUND",notepad2.getRecordWithId(frId));
        assertEquals("saturday",notepad2.getRecordOnPosition(5));
        assertEquals("EMPTY",notepad2.getRecordOnPosition(6));
    }

    @Test
    void removeRecordWithId() {
        Notepad notepad3 = new Notepad(10);
        notepad3.addRecord("13")//0
                .addRecord("564")
                .addRecord("67")//2
                .addRecord("622")
                .addRecord("23")//4
                .addRecord("924")
                .addRecord("101")//6
                .addRecord("42");
        int id1 = notepad3.getRecordId("67");
        assertEquals("67",notepad3.getRecordWithId(id1));
        assertEquals(2,notepad3.getRecordPosition("67"));
        notepad3.removeRecordWithId(id1);
        assertEquals("NOT_FOUND",notepad3.getRecordWithId(id1));
        assertEquals("622",notepad3.getRecordOnPosition(2));
        assertEquals(-1,notepad3.getRecordId("67"));



    }


    @Test
    void appendToRecordOnPosition() {
        Notepad notepad4 = new Notepad();
        notepad4.addRecord(new String("Hello"))
                .addRecord(new String(" Java"))
                .addRecord(new String(" World!!"))
                .addRecord(new String(" all!"));

        assertEquals("Hello",notepad4.getRecordOnPosition(0));
        assertTrue(notepad4
                .appendToRecordOnPosition(notepad4.getRecordOnPosition(1),0));
        assertEquals("Hello Java",notepad4.getRecordOnPosition(0));
    }

        @Test
    void appendToRecordWithId() {
        Notepad notepad5 = new Notepad();
        notepad5.addRecord("Hello")
                .addRecord(" Java")
                .addRecord(" World!!")
                .addRecord(" all!");
        int id1 = notepad5.getRecordId("Hello");
        assertEquals("Hello",notepad5.getRecordWithId(id1));
        int id2 = notepad5.getRecordId(" Java");
        int id3 = notepad5.getRecordId(" World!!");
        assertTrue(notepad5.appendToRecordWithId(notepad5.getRecordWithId(id2),id1));
        assertTrue(notepad5.appendToRecordWithId(notepad5.getRecordWithId(id3),id1));
        assertEquals("Hello Java World!!",notepad5.getRecordWithId(id1));
    }

    @Test
    void rewriteRecordOnPosition() {
        Notepad notepad6 = new Notepad();
        notepad6.addRecord("Masha")//0
                .addRecord("Misha")
                .addRecord("Pasha")//2
                .addRecord("Sasha")
                .addRecord("Vika");//4
        assertEquals("Pasha",notepad6.getRecordOnPosition(2));
        assertTrue(notepad6.rewriteRecordOnPosition("Nikita",2));
        assertEquals("Nikita",notepad6.getRecordOnPosition(2));
    }

    @Test
    void rewriteRecordwithId() {
        Notepad notepad7 = new Notepad();
        notepad7.addRecord("Masha")//0
                .addRecord("Misha")
                .addRecord("Pasha")//2
                .addRecord("Sasha")
                .addRecord("Vika");//4
        int id1 = notepad7.getRecordId("Sasha");
        assertEquals("Sasha",notepad7.getRecordWithId(id1));
        assertTrue(notepad7.rewriteRecordwithId("Marina",id1));
        assertEquals("Marina",notepad7.getRecordWithId(id1));
    }
}