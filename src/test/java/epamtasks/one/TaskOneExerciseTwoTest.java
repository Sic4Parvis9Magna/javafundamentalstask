package epamtasks.one;


import org.junit.jupiter.api.Test;
import epamtasks.one.TaskOneExerciseTwo;
import static org.junit.jupiter.api.Assertions.*;

class TaskOneExerciseTwoTest {
    TaskOneExerciseTwo exerciseTwo = new TaskOneExerciseTwo();
    @Test
    void nearestIndexOfArrayTest() {

        assertEquals(-1,exerciseTwo.nearestIndexOfArray(1,0.004));
        assertEquals(-1,exerciseTwo.nearestIndexOfArray(0,0.004));
        assertEquals(-1,exerciseTwo.nearestIndexOfArray(1,0.));
        assertEquals(-1,exerciseTwo.nearestIndexOfArray(-2,0.01));
        assertEquals(-1,exerciseTwo.nearestIndexOfArray(-3,10));
        assertEquals(1,exerciseTwo.nearestIndexOfArray(5,1));
        assertEquals(-1,exerciseTwo.nearestIndexOfArray(3,-0.004));
    }
}