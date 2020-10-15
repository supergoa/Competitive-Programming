import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;

public class ViewAngle {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new ViewAngle().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		TreeSet<Double> angles = new TreeSet<>();
		for(int n=0; n<N; n++) {
			int x = in.nextInt();
			int y = in.nextInt();
			double angle = Math.atan2(y,x) + Math.PI;
			angles.add(angle);
		}
		double maxAngle = 0;
		for(double a:angles) {
			Double above = angles.higher(a);
			if(above==null) {
				above = Math.PI*2 + angles.first();
			}
			maxAngle = Math.max(Math.abs(above-a), maxAngle);
		}
		double ans = ((2*Math.PI-maxAngle)*360)/(2*Math.PI);
		System.out.println(ans);
	}

	/*private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		double minAngle = -123456789;
		double maxAngle = -123456789;
		for(int n=0; n<N; n++) {
			int x = in.nextInt();
			int y = in.nextInt();
			if(minAngle==-123456789) {
				minAngle = maxAngle = Math.atan2(y, x);
			} else {
				double angle = Math.atan2(y, x);
				System.out.println(minAngle + " " + maxAngle + " " + angle);
				if(minAngle < 0 && maxAngle < 0) {
					if(angle<=maxAngle && angle>=minAngle) continue;
				} else if (minAngle < 0) {
					if(angle<=maxAngle && angle>=minAngle) continue;
				} else if (maxAngle < 0) {
					if((angle<=maxAngle && angle >=-Math.PI) || (angle<=Math.PI && angle>=minAngle)) continue;
				} else {
					if(angle<=maxAngle && angle>=minAngle) continue;
				}
				maxAngle = Math.max(angle, maxAngle);
				minAngle = Math.min(angle, minAngle);
				double minDistToMax = Math.min(((maxAngle<0 && angle<0) || (maxAngle>=0 && angle>=0))?Math.abs(maxAngle-angle):Math.abs(maxAngle)+Math.abs(angle), Math.abs(Math.PI-maxAngle)+Math.abs(-Math.PI-angle));
				double minDistToMin = Math.min(((minAngle<0 && angle<0) || (minAngle>=0 && angle>=0))?Math.abs(minAngle-angle):Math.abs(minAngle)+Math.abs(angle), Math.abs(-Math.PI-minAngle)+Math.abs(Math.PI-angle));
				if(minDistToMax > minDistToMin) {
					maxAngle = angle;
				} else {
					minAngle = angle;
				}
			}
			
			System.out.println((maxAngle*360/(2*Math.PI))-minAngle*360/(2*Math.PI));
		}
		
	}*/
}
