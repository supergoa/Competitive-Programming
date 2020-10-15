import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in, out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		//int T = in.nextInt();
		int num = 17;
		int remove1 = 8;
		int remove2 = 12;
		int remove3 = 14;
		int[] arr = new int[num];
		for(int i=0; i<num; i++) {
			for(int j=i; j<num; j++) {
				for(int a=i; a<=j; a++) {
					if(remove1 >=i && remove1<=j) break;
					if(remove2 >=i && remove2<=j) break;
					if(remove3 >=i && remove3<=j) break;
					arr[a]++;
				}
			}
		}
		System.out.println(Arrays.toString(arr));
		/*int T = in.nextInt();
		while(T-->0) {
			int N = in.nextInt();
			int M = in.nextInt();
			
			int n = N;
			long total = 0;
			long last = 0;
			long i = 1;
			while(n>0 && M>0) {
				if(n%2==0) {
					total += i*((n/2)*(n/2 + 1));
					last = (n/2)*(n/2 + 1);
					n = (n-2)/2;
				} else {
					total += i*((n+1)/2 * (n+1)/2);
					last = (n+1)/2 * (n+1)/2;
					n = (n-1)/2;
				}
				M -= i;
				i = i << 1;
				out.print(Long.toBinaryString(i));
			}
			out.println("total1: " + total);
			if(M<0) {
				total += M * last;
			}
			out.println("total2: " + total);
		}*/

	}
}
