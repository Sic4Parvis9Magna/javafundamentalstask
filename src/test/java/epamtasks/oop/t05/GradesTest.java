package epamtasks.oop.t05;

import org.junit.jupiter.api.Test;

import static epamtasks.oop.t05.Discipline.*;
import static epamtasks.oop.t05.Grades.convertGrade;
import static org.junit.jupiter.api.Assertions.*;


class GradesTest {

    @Test
   void convertGradeTest(){
        assertEquals(4.0,convertGrade(ASTRONOMY,4.6));
        assertEquals(3.0,convertGrade(ASTRONOMY,3.2));
        assertEquals(4.6,convertGrade(MATH,4.6));
        assertEquals(3.2,convertGrade(MATH,3.2));
    }

    @Test
    void getMeanGrade() {
        Grades grades = new Grades();
        grades.addGrade(ASTRONOMY,5.5)
                .addGrade(ASTRONOMY,4.5)
                .addGrade(ASTRONOMY,3.4)
                .addGrade(ASTRONOMY,2.8)
                .addGrade(MATH,4.4)
                .addGrade(MATH,4.7)
                .addGrade(MATH,3.8);
       assertEquals(3.5, grades.getMeanGrade(ASTRONOMY));
       assertEquals(4.300000000000001, grades.getMeanGrade(MATH));

    }
}