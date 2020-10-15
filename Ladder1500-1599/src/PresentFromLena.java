import java.util.Scanner;


public class PresentFromLena {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int[][] shape = new int[2*N+1][2*N+1];
		for(int j=0;j<2*N+1; j++) {
			if(j<=N) {
				shape[N][j] = j;
			} else {
				shape[N][j] = 2*N-j;
			}
			//int ind = N-1;
			for(int i=1;i<=N; i++) {
				shape[N-i][j] = shape[N][j]-i;
				shape[N+i][j] = shape[N][j]-i;
			}
		}
		for(int i=0;i<2*N+1; i++) {
			String line = "";
			for(int j=0; j<2*N+1; j++) {
				if(shape[i][j]>=0) {
					line+=shape[i][j] + " ";
				} else {
					line+="  ";
				}
			}
			//System.out.println(line);
			System.out.println(line.substring(0,line.lastIndexOf('0')+1));
		}
	}
}
