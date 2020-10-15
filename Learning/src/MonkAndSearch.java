import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class MonkAndSearch {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		
		String lineArr[] = br.readLine().split(" ");
		for(int i=0; i<lineArr.length; i++) {
			A[i] = Integer.parseInt(lineArr[i]);
		}
		
		Arrays.sort(A);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int Q = Integer.parseInt(br.readLine());
		for(int i=0; i<Q; i++) {
			String lineArr1[] = br.readLine().split(" ");
			int flag = Integer.parseInt(lineArr1[0]);
			int x = Integer.parseInt(lineArr1[1]);
			
			if(A[N-1]<x) {
				bw.write(0+"\n");
			}
			else if(A[0]>x) {
				bw.write(N+"\n");
			}
			else if(flag == 0) { // >=
				int low = 0;
				int high = N-1;

				while (low<=high-5) {
					int mid = (low+high)/2;
					if(A[mid] < x) {
						low = mid;
					}
					else if(A[mid] >= x) {
						high = mid;
					}
				}
				for(int j=low; j<=high; j++) {
					if(A[j]>=x) {
						high = j;
						break;
					}
				}
				bw.write(N-high+"\n");
			}
			else if (flag == 1) {
				int low = 0;
				int high = N-1;
				int mid = (low+high)/2;
				
				// 1 2 3 4 4 6 6 7 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 8 9 9 10
				// want 
				
				while (low<=high-5) {
					mid = (low+high)/2;
					if(A[mid] <= x) {
						low = mid;
					}
					else if(A[mid] > x) {
						high = mid;
					}
				}
				for(int j=high; j>=low; j--) {
					if(A[j]<=x) {
						high = j+1;
						break;
					}
				}

				bw.write(N-high+"\n");

				
				
			}
			else {
				throw new NumberFormatException();
			}
		}
		bw.flush();
	}
}

//1 2 3 4 5
//6 7 8 9 10
