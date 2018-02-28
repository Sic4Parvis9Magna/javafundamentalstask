package epamtasks.one;

public class TaskOneExerciseFour {

	public static double maxPair(double[] array) {
		double ans=0;
		ans  =array[0] + array[array.length-1];
		double sum =0;
		for(int i=0; i < (array.length/2); i++) {
			sum = array[i] + array[array.length-1-i];
			
			if( sum > ans) ans = sum;
		}
		if(array.length%2 != 0) {
			double midElement =  array[array.length/2];
			ans = (midElement>ans)?midElement:ans;
		}
		return ans;
	}
}
