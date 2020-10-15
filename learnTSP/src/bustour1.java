import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class bustour1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new bustour1().solve(in, out);
		in.close();
		out.close();
	}

	final int oo = (int) (1e9 + 7);
	HashMap<Integer, Integer> maskIDs;
	HashMap<Integer, Integer> moreIds;
	int[][][] memo;
	long[][] choosememo;
	int[][] adj;
	int N;

	private void solve(Scanner in, PrintWriter out) {
		int count = 1;
		choosememo = new long[25][25];
		for(int i=0; i<25; i++) {
			Arrays.fill(choosememo[i], -1);
		}
		while (in.hasNext()) {
			
			N = in.nextInt();
			int M = in.nextInt();

			boolean even = N % 2 == 0;
			adj = new int[N][N];
			
			
			
			for (int i = 0; i < N; i++) {
				Arrays.fill(adj[i], oo);
				adj[i][i] = 0;
			}
			for (int m = 0; m < M; m++) {
				int u = in.nextInt();
				int v = in.nextInt();
				int t = in.nextInt();
				adj[u][v] = t;
				adj[v][u] = t;
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					for(int k=0; k<N; k++) {
						adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
					}
				}
			}
			/*for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					System.out.print(adj[i][j] + " ");
				}
				System.out.println();
			}*/
			
			maskIDs = new HashMap<>();
			int testc = 0;
			for (int i = 0; i < (1 << N); i++) {
				if ((1 & i) != 0 && ((1 << (N - 1)) & i) != 0) continue;
				if ((1 & i) == 0 && ((1 << (N - 1)) & i) == 0) continue;
				
				if (even && Integer.bitCount(i) == N / 2) {
					maskIDs.put(i, maskIDs.size());
					testc++;
				} else if (!even && (Integer.bitCount(i) == N / 2 + 1)) {
					maskIDs.put(i, maskIDs.size());
					testc++;
				}
			}
			
			//System.out.println(N + " " + N/2);
			//System.out.println(testc);
			//System.out.println(choose(N, N/2) + " " + choose(N, N/2+1));
			/*
			 * int[][] bestLink = new int[N][maskIDs.size()]; for(int n=0; n<N; n++) {
			 * for(int mask : maskIDs.keySet()) { int min = oo; for(int nn=0; nn<N; nn++) {
			 * if(((1<<nn)&mask)==0) continue; min = Math.min(min, adj[n][nn]); }
			 * bestLink[n][maskIDs.get(mask)] = min; } } for(int n=0; n<N; n++) { for(int
			 * mask : maskIDs.keySet()) { System.out.println(n + " " +
			 * Integer.toBinaryString(mask));
			 * System.out.println(bestLink[n][maskIDs.get(mask)]); } }
			 */
			long time = System.currentTimeMillis();
			moreIds = (HashMap<Integer, Integer>) maskIDs.clone();
			int tc=0;
			for(int i=0; i<(1<<N); i++) {
				if(Integer.bitCount(i)<=(N+1)/2) {
					tc++;
					if(!moreIds.containsKey(i)) moreIds.put(i, moreIds.size());
				}
			}
			memo = new int[N][N][tc+100];
			for(int n=0; n<N; n++) {
				for(int nn=0; nn<N; nn++) {
					Arrays.fill(memo[n][nn], oo);
				}
			}
			//System.out.println("tc " + tc);
			
			for (int mask : maskIDs.keySet()) {
				for (int i = 0; i < N; i++) {
					if((mask&(1<<i))==0 || i==N-1 || i==0) continue;
					//if((mask&(1<<i))==0) continue;
					if ((1 & mask) != 0) { // first bit on
						memo[0][i][maskIDs.get(mask)] = dp(mask - 1, 0, i);
						//System.out.println("dp: "  + (0) + " " + i + " " + Integer.toBinaryString(mask)+ " " + memo[0][i][maskIDs.get(mask)]);
					} else if ((1 << (N - 1) & mask) != 0) {
						memo[N-1][i][maskIDs.get(mask)] = dp(mask - (1 << (N - 1)), N-1, i);
						//System.out.println("dp: " + (N-1) + " " + i + " " + Integer.toBinaryString(mask)+ " " + memo[N-1][i][maskIDs.get(mask)]);
					}
					//
				}
			}
			System.out.println("time " + (System.currentTimeMillis()-time));
			//System.out.println("time" +totTime);
			
			int ans = oo;
			for (int mask : maskIDs.keySet()) {
				int firstmask = mask;
				int secondmask = -1;
				
				int prev1 = -1;
				int prev2 = -1;
				if ((1 & mask) != 0) {
					prev1 = 0;
					prev2 = N-1;
					secondmask = mask - 1 + (1<<(N-1));
				} else if ((1 << (N - 1) & mask) != 0) {
					prev1 = N-1;
					prev2 = 0;
					secondmask = mask - (1<<(N-1)) + 1;
				}
				//System.out.println("Test: " + Integer.toBinaryString(firstmask) + " " + Integer.toBinaryString(secondmask));
				
				if(even) {
					int bestThere = oo;
					for (int end1 = 0; end1 < N; end1++) {
						if(((1<<end1)&mask) ==0) continue;
						for (int end2 = 0; end2 < N; end2++) {
							if((1<<end2 & (mask^((1<<N)-1))) ==0) continue;
							int maskID1 = maskIDs.get(mask);
							int maskID2 = maskIDs.get(mask^((1<<N)-1));
							int there = memo[prev1][end1][maskID1] + adj[end1][end2] +  memo[prev2][end2][maskID2];
							bestThere = Math.min(bestThere, there);
						}
					}
					
					int bestBack = oo;
					for (int end1 = 0; end1 < N; end1++) {
						if(((1<<end1)&secondmask) ==0) continue;
						for (int end2 = 0; end2 < N; end2++) {
							if((1<<end2 & (secondmask^((1<<N)-1))) ==0) continue;
							int maskID1 = maskIDs.get(secondmask);
							int maskID2 = maskIDs.get(secondmask^((1<<N)-1));
							int back = memo[prev2][end1][maskID1] + adj[end1][end2] +  memo[prev1][end2][maskID2];
							bestBack = Math.min(bestBack, back);
						}
					}
					ans = Math.min(ans, bestThere + bestBack);
				} else {
					int bestThere = oo;
					for (int end1 = 0; end1 < N; end1++) {
						if(end1==0 || end1==N-1) continue;
						if(((1<<end1)&mask) ==0) continue;
							//System.out.println(end1 + " " + Integer.toBinaryString(mask) + " " + Integer.toBinaryString((mask^((1<<N)-1)) | (1<<end1)));
							int maskID1 = maskIDs.get(mask);
							int maskID2 = maskIDs.get((mask^((1<<N)-1)) | (1<<end1));
						
							int there = memo[prev1][end1][maskID1] + adj[end1][end1] +  memo[prev2][end1][maskID2];
							bestThere = Math.min(bestThere, there);
					}
					
					int bestBack = oo;
					for (int end1 = 0; end1 < N; end1++) {
						if(end1==0 || end1==N-1) continue;
						if(((1<<end1)&secondmask) ==0) continue;
							int maskID1 = maskIDs.get(secondmask);
							int maskID2 = maskIDs.get(secondmask^((1<<N)-1) | (1<<end1));
							int back = memo[prev2][end1][maskID1] + adj[end1][end1] +  memo[prev1][end1][maskID2];
							bestBack = Math.min(bestBack, back);
					}
					ans = Math.min(ans, bestThere + bestBack);
				}
			}
			System.out.println("Case "+ (count++) + ": " + ans);
		}
	}
	long totTime = 0;
	private int dp(int mask, int prev, int end) {
		//long time = System.currentTimeMillis();
		//if (!moreIds.containsKey(mask)) moreIds.put(mask, moreIds.size());
		//long time2 = System.currentTimeMillis();
		//totTime+=time2-time;
		if(memo[prev][end][moreIds.get(mask)] != oo) return memo[prev][end][moreIds.get(mask)];
		if(mask==0) return memo[prev][end][moreIds.get(mask)] = 0;
		if (Integer.bitCount(mask) == 1) {
			//System.out.println("return base " +  adj[prev][end]);
			return memo[prev][end][moreIds.get(mask)] = adj[prev][end];
		}
		//System.out.println(Integer.toBinaryString(mask) + " " + prev + " " + end);
		int best = oo;
		for (int i = 0; i < N; i++) {
			if (i == end || ((1 << i) & mask) == 0) continue;
			best = Math.min(best, adj[prev][i] + dp(mask - (1 << i), i, end));
		}

		//System.out.println("return best " + best);
		return memo[prev][end][moreIds.get(mask)] = best;
	}
	
	
	public long choose(int total, int choose){
	    if(choosememo[total][choose] != -1) return choosememo[total][choose];
		if(total < choose)
	        return 0;
	    if(choose == 0 || choose == total)
	        return 1;
	    
	    choosememo[total][choose] = choose(total-1,choose-1)+choose(total-1,choose);
	    return choosememo[total][choose];
	}
}
