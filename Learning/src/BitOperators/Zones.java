package BitOperators;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class Zones {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Zones().solve(in,out);
		in.close();
		out.close();
	}

	class Shared {
		int idArr[];
		int overlap;
		int size;
		public Shared(int size) {
			this.size=size;
			idArr = new int[size];
		}
	}
	int[] towers;
	private void solve(Scanner in, PrintWriter out) {
		int counter = 0;
		while(true) {
			counter++;
			int N = in.nextInt();
			int B = in.nextInt();
			if(N==0 && B==0) break;
			
			towers = new int[N];
			for(int i=0; i<N; i++) towers[i] = in.nextInt();
			
			int M = in.nextInt();
			HashSet<Shared> shared = new HashSet<>();
			for(int m=0; m<M; m++) {
				int t = in.nextInt();
				Shared obj = new Shared(t);
				for(int i=0; i<obj.size; i++) {
					obj.idArr[i] = in.nextInt()-1;
				}
				obj.overlap = in.nextInt();
				shared.add(obj);
			}
			
			int ansMask = 0;
			int ans = 0;
			for(int mask=0; mask< 1<<N; mask++) {
				if(Integer.bitCount(mask)!=B) continue;
				int toSubtract = 0;
				for(Shared s : shared) {
					int towerCount = 0;
					for(int id : s.idArr) {
						if((mask & (1<<id))!=0) {
							towerCount++;
						}
					}
					if(towerCount>=2) toSubtract += ((towerCount-1) * s.overlap);
				}
				int total = 0;
				for(int bit = 0; bit<N; bit++) {
					if((mask & (1<<bit))==0) continue;
					total += towers[bit];
				}
				total -= toSubtract;
				if(total >= ans) {
					if(total == ans) {
						for(int i=0; i<N; i++) {
							if((ansMask & (1<<i))==0 && (mask & (1<<i))==0) continue;
							if((ansMask & (1<<i))!=0 && (mask & (1<<i))!=0) continue;
							if((mask & (1<<i))!=0) {
								ansMask = mask;
								ans = total;
							} else {
								break;
							}
						}
					}
					else {
						ansMask = mask;
						ans = total;
					}
				}
			}
			out.println("Case Number  " + counter);
			out.println("Number of Customers: " + ans);
			out.print("Locations recommended: ");
			for(int i=0; i<N; i++) {
				if((ansMask & (1<<i))==0) continue;
				out.print((i+1) + " ");
			}
			out.println("\n");
		}
		
	}
}
