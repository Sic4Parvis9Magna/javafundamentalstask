package epamtasks.oop.t05;

import org.junit.jupiter.api.Test;

import static epamtasks.oop.t05.Discipline.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentGroupTest {

    @Test
    void getGroupNumberTest() {
        StudentGroup studentGroup1 = new StudentGroup();
        StudentGroup studentGroup2 = new StudentGroup(ASTRONOMY) ;
        StudentGroup studentGroup3 = new StudentGroup(BIOLOGY) ;

        assertEquals(1,studentGroup1.getGroupNumber());
        assertEquals(2,studentGroup2.getGroupNumber());
        assertEquals(3,studentGroup3.getGroupNumber());
    }

    @Test
    void addStudentAndGetStudentsTest() {
        Student student1 = new Student()
                .setFirstName("Misha")
                .setLastName("Lobanov");
        Student student2 = new Student()
                .setFirstName("Sveta")
                .setLastName("Iyina");
        StudentGroup studentGroup = new StudentGroup(HISTORY)
                .addStudent(student1)
                .addStudent(student2);
        assertTrue(studentGroup.getStudents().contains(student1));
        assertTrue(studentGroup.getStudents().contains(student2));


    }
}