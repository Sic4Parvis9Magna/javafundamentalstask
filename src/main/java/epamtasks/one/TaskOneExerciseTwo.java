package epamtasks.one;

import org.apache.logging.log4j.Logger;

public class TaskOneExerciseTwo {

	private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(TaskOneExerciseTwo.class);

	public static int nearestIndexOfArray(int size,double epsilon) {
		int nearestIndex = -1;
		boolean found = false;
		double currentElement;
		for(int i =0;i < size; i++) {
			currentElement = 1 / Math.pow((i + 1), 2);
			if ( currentElement < epsilon && !found){
				nearestIndex = i;
				found = true;
			}
			log.info("a[{}] = {}", i,currentElement);
		}
		if(nearestIndex > -1){
			log.info("Epsilon = {}, nearestIndex = {}.\n",epsilon,nearestIndex);
		}else{
			log.info("Epsilon = {}, nearestIndex  what satisfies by condition isn't present in this array.\n",epsilon);
		}
		return nearestIndex;
	}

	public static void main(String[] args) {

	}

}
