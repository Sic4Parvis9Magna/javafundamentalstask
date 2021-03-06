package epamtasks.one;



public  class TaskOneExerciseFive {

	 static int[][] getSqMatrix(int size){
		if(size <= 0)return new int[0][0];
		int[][] matrix = new int[size][size];
		for(int row=0; row <size; row++)
			for(int column=0; column <size; column++ ) {
				if(row == column) matrix[row][column]=1;
				if((row+column+2) == (size+1))matrix[row][column]=1;
			}
		return matrix;
	}
	
	 static void printSqMatrix(int[][]matrix) {
		for(int row=0; row <matrix.length ; row++) {
			for(int column=0; column <matrix.length; column++ )
				System.out.printf("| %d |",matrix[row][column]);
			System.out.printf("%n");
		}
		 System.out.printf("%n");
	}

}
