
public class VanyaAndLanterns {
	public static void main(String[] args) {
		
	}
	static double[] doubleBinarySearch(double low, double high, double target) {
		double mid = 0;
		for(int i=0; i<500; i++) {
			mid = (low+high)/2;
			if(target==arr[mid]) break;
			if(arr[mid]>target) {
				high = mid;
			} else if(arr[mid]<target) {
				low = mid;
			}
		}
		return new int[] {mid,low,high};
	}
}
