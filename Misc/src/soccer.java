import java.util.Arrays;
import java.util.Scanner;

public class soccer {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new soccer().solve(in);
		in.close();
	}

	class Point {
		int x,y;
		public Point (int a, int b) {
			x=a;
			y=b;
		}
	}
	private void solve(Scanner in) {
		int N = in.nextInt();
		Point[] players1 = new Point[N];
		Point[] players2 = new Point[N];
		
		for(int n=0; n<N; n++) {
			players1[n] = new Point(in.nextInt(), in.nextInt());
		}
		for(int n=0; n<N; n++) {
			players2[n] = new Point(in.nextInt(), in.nextInt());
		}
		
		int[][] adj = new int[N][N];
		for(int i=0;i<N; i++) Arrays.fill(adj[i], 987654321);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				boolean blocked = false;
				
				// other team
				for(int k=0; k<N; k++) {
					double s1 = slope(players1[i].x, players1[i].y, players1[j].x, players1[j].y);
					double s2 = slope(players1[i].x, players1[i].y, players2[k].x, players2[k].y);
					
					double dist1 = dist(players1[i].x, players1[i].y, players1[j].x, players1[j].y);
					double dist2 = dist(players1[i].x, players1[i].y, players2[k].x, players2[k].y);
					if(dist2< dist1 && Math.abs(s1-s2) <= .000001) blocked = true;
					//if(i==0 && j==2 && k==0) System.out.println(s1 + " " + s2 + " " + dist1 + " " + dist2);
					//System.out.println(i + " " + j + " " + k + " " + blocked );
				}
				
				// my team
				for(int k=0; k<N; k++) {
					if(k==i || k==j) continue;
					double s1 = slope(players1[i].x, players1[i].y, players1[j].x, players1[j].y);
					double s2 = slope(players1[i].x, players1[i].y, players1[k].x, players1[k].y);

					double dist1 = dist(players1[i].x, players1[i].y, players1[j].x, players1[j].y);
					double dist2 = dist(players1[i].x, players1[i].y, players1[k].x, players1[k].y);
					if(dist2< dist1 && Math.abs(s1-s2) <= .000001) blocked = true;
				}
				
				if(!blocked)  {
					adj[i][j] = 1;
					adj[j][i] = 1;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					if(adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
					}
				}
			}
		}
		if(adj[0][N-1]==987654321) System.out.println(-1);
		else System.out.println(adj[0][N-1]);
	}
	private double dist(int x, int y, int x2, int y2) {
		return Math.sqrt((x-x2)*(x-x2)+(y-y2)*(y-y2));
	}
	private double slope(double x, double y, double x2, double y2) {
		double num = Math.abs((y2-y)/(x2-x));
		if(Double.isNaN(num) || Double.isInfinite(num)) return -1;
		else return num;
	}
}
