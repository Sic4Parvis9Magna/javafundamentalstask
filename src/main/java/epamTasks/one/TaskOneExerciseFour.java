package epamTasks.one;

public class TaskOneExerciseFour {

	public static void main(String[] args) {
		double arr[] = {1,2,3,100,3,2,1}; 
		System.out.println("my ans = " + maxPair(arr));
	}

	
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
	
	//check then 0 is max. no one is max ???
}
