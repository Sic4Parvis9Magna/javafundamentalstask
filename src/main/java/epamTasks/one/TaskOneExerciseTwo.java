package epamTasks.one;

public class TaskOneExerciseTwo {

	public static void main(String[] args) {

		int size =15;
		
		double epsilon = 1e-2;
		
		for(int i =0;i<size; i++) 
			System.out.printf("ai= %f \n"	,(1/Math.pow(i+1, 2)));
		
		System.out.printf("Size= %d , epsilon = %f\n", size,epsilon);
		System.out.printf("nearest index= %d" ,nearestIndexOfArray(size, epsilon));
	}
	
	public static int nearestIndexOfArray(int size,double epsilon) {
		for(int i =0;i<size; i++) 
			if((1/Math.pow(i+1, 2))<epsilon) return i; 
		return -1;
	}

}
