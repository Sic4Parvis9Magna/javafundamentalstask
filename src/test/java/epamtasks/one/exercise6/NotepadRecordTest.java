package epamtasks.one.exercise6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotepadRecordTest {


    @Test
    void getIdTest(){
        NotepadRecord notepadRecord1 = new NotepadRecord();
        NotepadRecord notepadRecord2 = new NotepadRecord("second record");
        NotepadRecord notepadRecord3 = new NotepadRecord("7456");
        assertEquals(4,notepadRecord1.getId());
        assertEquals(5,notepadRecord2.getId());
        assertEquals(6,notepadRecord3.getId());
    }

    @Test
    void setAdndGetRecordTest() {
        NotepadRecord notepadRecord4 = new NotepadRecord();
        assertEquals(1,notepadRecord4.getId());
        assertEquals("",notepadRecord4.getRecord());
        notepadRecord4.setRecord("Hello world");
        assertEquals("Hello world",notepadRecord4.getRecord());
        notepadRecord4.setRecord("new message");
        assertEquals("new message",notepadRecord4.getRecord());

        NotepadRecord notepadRecord5 = new NotepadRecord("fith record");
        assertEquals(2,notepadRecord5.getId());
        NotepadRecord notepadRecord6 = new NotepadRecord("8742");
        assertEquals(3,notepadRecord6.getId());
        assertEquals("fith record", notepadRecord5.getRecord());
        assertEquals("8742",notepadRecord6.getRecord());

    }



    @Test
    void getEmptyRecord() {

        NotepadRecord empty = NotepadRecord.getEmptyRecord();
        NotepadRecord emptyCopy = NotepadRecord.getEmptyRecord();
        assertEquals(0,empty.getId());
        assertEquals("EMPTY",empty.getRecord());
        assertEquals(emptyCopy.getId(),empty.getId());
        assertEquals(emptyCopy.getRecord(),empty.getRecord());

    }
}