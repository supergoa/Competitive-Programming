import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		while(T-->0) {
			int N = in.nextInt();
			String path = in.next();
			int[][] freq = new int[N][2];
			for(int n=0; n<N; n++) {
				if(path.charAt(n)=='L') freq[n][0] += 1;
				if(path.charAt(n)=='U') freq[n][1] += 1;
				if(path.charAt(n)=='R') freq[n][0] += -1;
				if(path.charAt(n)=='D') freq[n][1] += -1;
				if(n != 0) {
					freq[n][0] += freq[n-1][0];
					freq[n][1] += freq[n-1][1];
				}
			}

			HashMap<String, Integer> hm = new HashMap<>();
			int ans = 987654321;
			int ansid1 = 0;
			int ansid2 = 0;
			for(int n=0; n<N; n++) {
				if(freq[n][0]==0 && freq[n][1]==0) {
					if(ans > n+1) {
						ans = n+1;
						ansid1 = 1;
						ansid2 = n+1;
					}
				}
				String id = freq[n][0]+","+freq[n][1];
				if(hm.containsKey(id)) {
					if(ans > n-hm.get(id).intValue()) {
						ans = n-hm.get(id).intValue();
						ansid1 = hm.get(id).intValue()+2;
						ansid2 = n+1;
					}
				}
				hm.put(id, n);
			}
			if(ans == 987654321) out.println(-1);
			else out.println(ansid1 + " " + ansid2);
		}
		
	}
}
