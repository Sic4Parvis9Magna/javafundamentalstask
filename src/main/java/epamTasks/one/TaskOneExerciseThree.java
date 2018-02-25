package epamTasks.one;

public class TaskOneExerciseThree {

	public static void main(String[] args) {
		
		printFuncValues(1, 10, 1);
		printFuncValues(1, 10, 100);
		printFuncValues(1, 11, 3);
	}
	
	// type of entrance value
	public static void printFuncValues(int startX,int finishX, int step) {
		
		int delta =finishX-startX;
		
		if(delta<step || delta%step > 0) {
			System.out.printf("not allowed values\n");
			return;
		}
			
		
		int numOfSteps = ((finishX-startX)/step);
		for(int i=1; i <= numOfSteps;i++)
			System.out.printf("x: %d F(x): %f\n",(startX+step*i),Math.tan(2*startX*step*i));
		System.out.printf("\n");
	}

}
