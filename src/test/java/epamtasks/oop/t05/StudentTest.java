package epamtasks.oop.t05;

import org.junit.jupiter.api.Test;

import static epamtasks.oop.t05.StudyMode.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void equalsAndHashCodeTest() {
        Student student1 = new Student()
                .setFirstName("Ivan")
                .setLastName("Volkov")
                .setStudyMode(FULL_TIME)
                .addGroupNumber(11345);
        Student student2 =  student1
                .addGroupNumber(5321)
                .setStudyMode(REMOTE);

        Student student3 = new Student()
                .setFirstName("Mihail")
                .setLastName("Volkov")
                .setStudyMode(FULL_TIME)
                .addGroupNumber(11345);

        assertTrue(student1.equals(student2));
        assertTrue(student1.equals(student1));
        assertTrue(student2.equals(student1));
        assertFalse(student1.equals(student3));

        assertEquals(student1.hashCode(),student2.hashCode());
        assertNotEquals(student1.hashCode(),student3.hashCode() );
        assertNotEquals(student2.hashCode(),student3.hashCode() );
    }
}