package epamtasks.one;

import org.apache.logging.log4j.Logger;

public class TaskOneExerciseThree {

	private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(TaskOneExerciseThree.class);

	public static void printFuncValues(int startX,int finishX, int step) {

		int numOfSteps = (step != 0 )?((finishX-startX)/step):0;
		for(int i=0; i <= numOfSteps;i++) {
			log.info("x: {} F(x): {}", (startX + (step * i)), Math.tan((double) 2 * (startX + (step * i))));
		}
		log.info("\n");
	}

}
