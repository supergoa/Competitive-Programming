package FirstCourse;

import java.io.PrintWriter;
import java.util.Scanner;

public class Profits {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Profits().solve(scan, out);
		scan.close();
		out.close();
		
	}

	private void solve(Scanner scan, PrintWriter out) {
		while(scan.hasNext()) {
			int N = scan.nextInt();
			if(N==0) break;
			
			int[] arr = new int[N];
			for(int n=0; n<N; n++) {
				arr[n] = scan.nextInt();
			}
			int sum = 0 , max = -100, i=0, end = -1, start = -1; 
			for(int j=0; j<N; j++) {
				sum += arr[j];
				if(sum > max) {
					max = sum;
					start = i;
					end = j;
				}
				if(sum < 0) {
					sum = 0;
					i=j+1;
					//j++;
				}
			}
			out.println(max);
			out.flush();
		}
		
	}
}
