import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class x {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new x().solve(in,out);
		in.close();
		out.close();
	}

	class Point {
		int x,y;
		public Point(int a, int b) {
			x=a;
			y=b;
		}
	}
	Point[] p;
	double[][][] areas;
	int N;
	double[][][][] memo;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		firstLeave = N+1;
		int K = in.nextInt();
		
		p = new Point[N];
		areas = new double[N][N][N];
		for(int i=0; i<N; i++) {
			p[i] = new Point(in.nextInt(), in.nextInt());
		}
		double polyArea = 0;
		for(int i=0; i<N; i++) {
			int id2 = i-1;
			if(id2<0) id2+=N;	
			polyArea += (p[id2].x-p[i].x) * (p[id2].y + p[i].y);
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					double v1x = p[j].x-p[i].x;
					double v1y = p[j].y-p[i].y;
					double v2x = p[k].x-p[i].x;
					double v2y = p[k].y-p[i].y;
					
					double cross = v1x*v2y-v2x*v1y;
					double area = cross/2.0;
					areas[i][j][k] = Math.abs(area);
				}
			}
		}
		memo = new double[N+3][N+1][N-K+1][N+1];
		for(int i=0; i<N+3; i++) {
			for(int j=0; j<N+1; j++) {
				for(int k=0; k<N-K+1; k++) {
					Arrays.fill(memo[i][j][k], -1);
				}
			}
		}
		
		polyArea=Math.abs(polyArea/2.0);
		double ans = polyArea - dp(firstLeave, N-1, N-K, 0);
		System.out.printf("%.1f",ans);
		
	}
	int firstLeave;
	double oo = 987654321000.0;
	private double dp(int fl, int prev, int left, int i) {
		if(left==0) return 0;
		if(left!=0 && i==N) return oo;
		if(memo[fl][prev][left][i]!=-1) return memo[fl][prev][left][i];
		double choose=oo; double leave=oo;
		int next = i+1;
		if(next >= N && fl != firstLeave) next = fl;
		if(next >= N && fl==prev) return oo;
		
		choose = areas[prev][i][next]+dp(fl, prev, left-1, i+1);
		
		if(fl==firstLeave)  fl = i;
		leave = dp(fl, i, left, i+1);
		
		return memo[fl][prev][left][i]=Math.min(choose, leave);
	}
}
