import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

//7:45
public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in, out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		String[] arr = new String[N];
		for(int n=0; n<N; n++) {
			arr[n] = in.next();
		}
		boolean[] isPal = new boolean[N];
		int[] hasOpp = new int[N];
		Arrays.fill(hasOpp, -1);
		for(int n=0; n<N; n++) {
			for(int nn=0; nn<N; nn++) {
				if(arr[n].equals(new StringBuilder(arr[n]).reverse().toString())) {
					isPal[n] = true;
				}
				if(n==nn) continue;
				if(arr[n].equals(new StringBuilder(arr[nn]).reverse().toString())) {
					hasOpp[n] = nn;
					hasOpp[nn] = n;
				}
				
			}
		}
		int len = 0;
		ArrayDeque<String> s = new ArrayDeque<>();
		boolean[] used = new boolean[N];
		for(int n=0; n<N; n++) {
			if(hasOpp[n] != -1 && !used[n]) {
				s.push(arr[n]);
				s.add(arr[hasOpp[n]]);
				used[n] = used[hasOpp[n]] = true;
				len += arr[n].length()*2;
			}
		}
		String longest = "";
		for(int n=0; n<N; n++) {
			if(!used[n] && isPal[n]) {
				if(arr[n].length() > longest.length()) {
					longest = arr[n];
				}
			}
		}
		len += longest.length();
		out.println(len);
		int init = s.size();
		if(s.isEmpty()) out.print(longest);
		while(!s.isEmpty()) {
			if(s.size()==init/2) out.print(longest);
			out.print(s.poll());
		}
	}
}
