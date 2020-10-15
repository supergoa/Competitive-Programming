import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class mario {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new mario().solve(in,out);
		in.close();
		out.close();
	}

	class Point {
		float x,y,z;
		public Point(float x, float y, float z) {
			this.x=x;
			this.y=y;
			this.z=z;
		}
	}
	
	int N;
	Point[][] switchPoints;
	float[][][][] coinDp;
	float[][] leverDp;
	float[][][][] distPre;
	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			N = in.nextInt();
			Point start = new Point(in.nextInt(), in.nextInt(), in.nextInt());
			if(N==0 && start.x==0 && start.y==0 && start.z==0) break;
			switchPoints = new Point[N][];
			
			for(int i=0; i<N; i++) {
				int K = in.nextInt();
				switchPoints[i] = new Point[K+1];
				switchPoints[i][K] = new Point(in.nextInt(), in.nextInt(), in.nextInt());
				for(int k=0; k<K; k++) {
					switchPoints[i][k] = new Point(in.nextInt(), in.nextInt(), in.nextInt());
				}
			}
			distPre = new float[14][14][14][14];
			for(int i=0; i<14; i++) {
				if(switchPoints.length <= i) continue;
				for(int j=0; j<14; j++) {
					if(switchPoints[i].length <= j) continue;
					for(int k=0; k<14; k++) {
						if(switchPoints.length <= k) continue;
						for(int l=0; l<14; l++) {
							if(switchPoints[k].length <= l) continue;
							distPre[i][j][k][l] = dist(switchPoints[i][j], switchPoints[k][l]);
						}
					}
				}
			}
			coinDp = new float[N][N+1][][];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N+1; j++) {
					curLever = i;
					curCoins = switchPoints[curLever].length; // K
					curMask = (1<<(switchPoints[curLever].length-1))+1;

					coinDp[i][j] = new float[curCoins][];
					for(int k=0; k<curCoins; k++) {
						coinDp[i][j][k] = new float[curMask];
					}
					for(int k=0; k<curCoins; k++) {
						Arrays.fill(coinDp[i][j][k], -1);
					}
				}
			}
			for(int lever=0; lever<N; lever++) {
				curLever = lever;
				curCoins = switchPoints[curLever].length-1; // K
				curMask = (1<<(switchPoints[lever].length-1))-1;
				
				for(int end=0; end<=N; end++) {
					if(end==lever) continue;
					
					float best = 999999999;
					for(int prev=0; prev<curCoins; prev++) {
						
						best = Math.min(best, distPre[lever][curCoins][lever][prev] + dpCoins(end, prev, curMask-(1<<prev)));
					}
					//System.out.println(lever + " " + end + " " + curCoins + " " + curMask);
					coinDp[lever][end][curCoins][curMask] = best;
				}
			}
			
			leverDp = new float[N][(1<<N)+1];
			for(int n=0; n<N; n++) Arrays.fill(leverDp[n], -1);
			float best = 99999999;
			for(int prev=0; prev<N; prev++) {
				curCoins = switchPoints[prev].length-1; // K
				best = Math.min(best, dist(start, switchPoints[prev][curCoins]) + dp(prev, (1<<N)-1-(1<<prev)));
			}
			System.out.printf("%.2f\n", best);
			
		}
	}
	
	private float dp(int prev, int mask) {
		
		if(leverDp[prev][mask]!=-1) return leverDp[prev][mask];
		
		int curCoins = switchPoints[prev].length-1; // K
		int curMask = (1<<(switchPoints[prev].length-1))-1;
		if(mask==0) return coinDp[prev][N][curCoins][curMask];
		
		float best = 999999999;
		for(int i=0; i<N; i++) {
			if(((1<<i)&mask)==0)continue;
			best = Math.min(best, coinDp[prev][i][curCoins][curMask] + dp(i, mask-(1<<i)));
		}
		return leverDp[prev][mask] = best;
	}
	
	int curLever;
	int curCoins;
	int curMask;
	private float dpCoins(int end, int prev, int mask) {
		
		if(coinDp[curLever][end][prev][mask]!=-1) return coinDp[curLever][end][prev][mask];
		
		if(mask==0) {
			if(end==N) return 0;
			return distPre[curLever][prev][end][switchPoints[end].length-1];
		}
		
		float best = 99999999;
		for(int i=0; i<curCoins; i++) {
			if((mask&(1<<i))==0) continue;
			best = Math.min(best, distPre[curLever][prev][curLever][i] + dpCoins(end, i, mask-(1<<i)));
		}
		return coinDp[curLever][end][prev][mask] = best;
	}
	
	float dist(Point p1, Point p2) {
		return (float) Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y) + (p1.z-p2.z)*(p1.z-p2.z));
	}
}