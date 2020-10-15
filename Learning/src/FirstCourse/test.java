package FirstCourse;

public class test {
	public static void main(String[] args) {
		for(int i=-1; i<2; i++) {
			for(int j=-1; j<2; j++) {
				for(int k=-1; k<2; k++) {
					if(manhattenDistance(i,j,k)==1) {
						System.out.println("i:"+ i + " - j:"+j+" - k:"+ k);
					}
				}
			}
		}
	}
	private static int manhattenDistance(int x, int y, int z) {
		return Math.abs(x) + Math.abs(y) + Math.abs(z);
	}
}
