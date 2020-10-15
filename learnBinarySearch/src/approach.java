import java.io.PrintWriter;
import java.util.Scanner;

public class approach {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new approach().solve(in, out);
		in.close();
		out.close();
	}
	
	class Pair{
		int s, e;
		public Pair(int a, int b) {
			s = a;
			e = b;
		}
	}
	int N = 0;
	Pair[] p;
	private void solve(Scanner in, PrintWriter out) {
		int counter = 1;
		while(true) {
			N = in.nextInt();
			if(N==0) break;
			
			
			p = new Pair[N];
			for(int n=0; n<N; n++) {
				p[n] = new Pair(in.nextInt()*60, in.nextInt()*60);
			}
			perms(0, new int[N], new boolean[N]);
			//for(int n=0; n<N; n++) {
			//	System.out.println(p[n].s + " " + p[n].e);
			//}
			
			//System.out.println(Math.round(seconds));
			//System.out.println(minutes + " " + seconds);
			//high = 4575;
			//int left = high%(60*10);
			//int left = 3156;
			//int char1 = left%10;
			//int char2 = (left/10)%10;
			//if(char2 >= 5) left += 10;
			//System.out.println(high);
			//System.out.println(left + " " + " " + char2);
			out.printf("Case "+(counter++)+": %d:%02d\n", best/60, best%60);
			//System.out.println(low/60.0 + " " + high/60.0);
		}
	}
	
	int best = 0;
	private void perms(int cur, int[] perm, boolean[] used) {
		if(cur==N) {
			best = Math.max(best, binSearch(perm.clone()));
		}
		for(int i=0; i<N; i++) {
			if(!used[i]) {
				used[i] = true;
				perm[cur] = i;
				perms(cur+1, perm, used);
				used[i] = false;
				perm[cur] = -1;
			}
		}
	}

	private int binSearch(int[] perms) {
		double low = 0;
		double high = (int) 1440*70;
		double mid = 0;
		int loops = 40;
		while(loops-->0) {
			mid = (low+high)/2.0;
			
			boolean bad = true;
			double prev = p[perms[0]].s;
			for(int n=1; n<N; n++) {
				
				bad = false;
				int start = p[perms[n]].s;
				int end = p[perms[n]].e;
				if(end-prev >= mid) {
					if(prev+mid >= start) {
						prev = prev + mid;
					} else {
						prev = start;
					}
				} else {
					bad = true;
				}
				/*for(int i=p[perms[n]].s; i<=p[perms[n]].e; i++) {
					if(i-prev>=mid) {
						prev = i;
						bad = false;
						break;
					}
				}*/
				if(bad) break;
			}
			if(bad) {
				high = mid;
			} else {
				low = mid;
			}
		}
		int minutes = (int) (mid/60);
		int seconds = (int) Math.round(mid % 60);
		return minutes*60 + seconds;
	}
}
