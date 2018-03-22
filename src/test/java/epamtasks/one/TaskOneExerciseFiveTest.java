package epamtasks.one;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TaskOneExerciseFiveTest {
    TaskOneExerciseFive exerciseFive = new TaskOneExerciseFive();
    @Test
    void SqMatrixTest() {
        int arr1[][] = {{1,0,1},
                        {0,1,0},
                        {1,0,1}};
        int arr2[][] = {{1,1},
                        {1,1},};
       int arr3[][] =  new int[0][0];


      getSqMatrixTest(arr1);
      getSqMatrixTest(arr2);
      getSqMatrixTest(arr3);

    }


    void getSqMatrixTest(int[][] testArr){
        int[][] createdArr =  exerciseFive.getSqMatrix(testArr.length);
        for(int i = 0; i < testArr.length; i++) {
            assertEquals(0, Arrays.compare(testArr[i],
                                                createdArr[i]));
        }
    }
}