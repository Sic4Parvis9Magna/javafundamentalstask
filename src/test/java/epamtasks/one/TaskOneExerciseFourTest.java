package epamtasks.one;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskOneExerciseFourTest {
    TaskOneExerciseFour oneExerciseFour = new TaskOneExerciseFour();
    @Test
    void maxPairTest1() {
        double arr1[] = {1,2,3,5,3,2,1};
        double arr2[] = {1,2,3,10,3,2,1};
        double arr3[] = {1,2,3,5,0,2};

        assertEquals(6,oneExerciseFour.maxPair(arr1));
        assertEquals(10,oneExerciseFour.maxPair(arr2));
        assertEquals(8,oneExerciseFour.maxPair(arr3));

    }
    @Test
    void maxPairTest2() {
        double arr1[] = {-1,-2,-3,-5,-3,-2,-1};
        double arr2[] = {0,0,0,-10,0,0,-1};
        double arr3[] = {0,0,0,0,0,0};
        double arr4[] = {-1,-5,-2,7,3,3};

        assertEquals(-2,oneExerciseFour.maxPair(arr1));
        assertEquals(0,oneExerciseFour.maxPair(arr2));
        assertEquals(0,oneExerciseFour.maxPair(arr3));
        assertEquals(5,oneExerciseFour.maxPair(arr4));

    }
}