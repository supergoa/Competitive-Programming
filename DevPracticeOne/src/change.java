import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class change {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new change().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int C = in.nextInt();
			int[] packages = new int[C];
			for(int c=0; c<C; c++) {
				packages[c] = in.nextInt();
				
			}
			Arrays.sort(packages);
			int sum = 0;
			for(int i=0; i<C; i++) {
				if(sum+1 >= packages[i])
					sum += packages[i];
			}
			System.out.println("Set #"+(n+1)+": "+(sum+1));
			System.out.println();
			/*int toMake = 1;
			int curHave = packages[0];
			
			for(int c=1; c<C; c++) {
				toMake = packages[c-1]+1;
				System.out.println(toMake + " " + curHave);
				
				if(toMake <= curHave || packages[c]==toMake) {
					curHave +=  packages[c];
					
				} else break;
			}*/
			//out.println(curHave+1);
			
			/*
			 * 3
6
12 8 1 2 4 100
3
1 2 3
6 
3 1 3 2 3 3
			 */
		}
		
	}
}
