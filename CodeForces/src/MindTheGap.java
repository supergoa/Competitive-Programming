import java.io.PrintWriter;
import java.util.Scanner;

public class MindTheGap {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new MindTheGap().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N, minimumMin;
		N = in.nextInt();
		minimumMin = in.nextInt()+1;
		int[] timesLanding = new int[N+1];
		timesLanding[0]=-minimumMin+1;
		boolean found = false;
		for (int n=1; n<N+1; n++) {
			int h = in.nextInt();
			int m = in.nextInt();
			timesLanding[n] = h*60 + m + 1;
			//System.out.println(n+":");
			//System.out.println((timesLanding[n]-timesLanding[n-1]-1)/2.0);
			if((timesLanding[n]-timesLanding[n-1])/2.0>=minimumMin) {
				int ansHour = 0;
				int ansMin = 0;
				while(timesLanding[n-1]>=60) {
					ansHour++;
					timesLanding[n-1]-=60;
				}
				ansMin = timesLanding[n-1]+minimumMin-1;
				while(ansMin>=60) {
					ansHour++;
					ansMin-=60;
				}
				out.print(ansHour + " " + ansMin);
				found = true;
				break;
			}
		}
		//System.out.println(timesLanding[N]);
		if(!found) {
			int ansHour = 0;
			int ansMin = 0;
			while(timesLanding[N]>=60) {
				ansHour++;
				timesLanding[N]-=60;
			}
			ansMin = timesLanding[N]+minimumMin-1;
			while(ansMin>=60) {
				ansHour++;
				ansMin-=60;
			}
			out.print(ansHour + " " + ansMin);
			found = true;
		}
		
	}
}
