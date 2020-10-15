import java.io.IOException;
import java.util.Scanner;

public class MonkAndOperations {
	
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		
		int N = s.nextInt();
		int M = s.nextInt();
		
		int [][] arr = new int[N][M];
		for(int i = 0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = s.nextInt();
			}
		}
		
		int v1, v2, v3, v4;
		v1 = s.nextInt();
		v2 = s.nextInt();
		v3 = s.nextInt();
		v4 = s.nextInt();
		
		// rows
		long totalSumRows = 0;
		long v1Sum = 0;
		long v2Sum = v2*M;
		long plainSum = 0;
		
		for(int i = 0; i<N; i++) {
			for(int j=0; j<M; j++) {
				plainSum += Math.abs(arr[i][j]);
				v1Sum += Math.abs(arr[i][j] + v1);
			}
			long finalRowSum = Math.max(v1Sum, v2Sum);
			finalRowSum =  Math.max(finalRowSum, plainSum);
			totalSumRows += finalRowSum;
			
			v1Sum = 0;
			plainSum = 0;
		}
		
		// cols
		long totalSumCols = 0;
		long v3Sum = 0;
		long v4Sum = v4*N;
		plainSum = 0;
		
		for(int i = 0; i<M; i++) {
			for(int j=0; j<N; j++) {
				plainSum += Math.abs(arr[j][i]);
				v3Sum += Math.abs(arr[j][i] + v3);
			}
			long finalRowSum = Math.max(v3Sum, v4Sum);
			finalRowSum =  Math.max(finalRowSum, plainSum);
			totalSumCols += finalRowSum;
			
			v3Sum = 0;
			plainSum = 0;
		}
		
		System.out.println(Math.max(totalSumCols, totalSumRows));
		s.close();
	}
}
