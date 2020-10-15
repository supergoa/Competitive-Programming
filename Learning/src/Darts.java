import java.util.Scanner;

public class Darts {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		for(int n=0; n<N; n++) {
			double w = sc.nextDouble();
			double b = sc.nextDouble();
			double d = sc.nextDouble();
			double s = sc.nextDouble();
			
			int T = sc.nextInt();
			int score = 0;
			for(int t=0; t<T; t++) {
				double x = sc.nextDouble();
				double y = sc.nextDouble();
				
				double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
				if(distance < b) {
					score += 50;
					//System.out.println(x + ", " + y + " returned 50");
					continue;
				}
				
				//find wedge point is in
				double wedgeRad = (2 * Math.PI)/w;
				double dartAngle = Math.atan2(y, x);
				
				if(dartAngle < 0) {
					dartAngle += 2 * Math.PI;
				}
				//System.out.println("dartAng" + dartAngle);
				int basePoints = (int) Math.ceil(dartAngle/wedgeRad);
				if(distance < d) {
					score += basePoints * 2;
					//System.out.println(x + ", " + y + " returned" + (basePoints * 2));
					continue;
				}
				if(distance < s) {
					score += basePoints;
					//System.out.println(x + ", " + y + " returned" + (basePoints));
					continue;
				}
				else {
					//System.out.println("ERROR");
				}
			}
			System.out.println(score);
		}
		sc.close();
	}
}
