import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class InnaAndDima {
	static PrintWriter out;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out = new PrintWriter(System.out);
		new InnaAndDima().solve(in,out);
		in.close();
		out.close();
	}

	final int oo = (int) 1e9;
	boolean[][] visited;
	public int dp(char curr, int x, int y) {
		//System.out.println(curr+ " "+x + " "+y);
		if(x>=N || y>=M || x<0 || y<0) {
			return -oo;
		}
		if(visited[x][y]) {
			out.println("Poor Inna!");
			out.flush();
			System.exit(0);
		}
		
		if(memo[x][y]!=-1) return memo[x][y];
		
		char nextSpace = next.get(curr);
		int ans = curr =='A'?1:0;
		
		int up=0;
		int down=0;
		int left=0;
		int right=0;
		
		visited[x][y] = true;
		if(inBounds(x-1,y) && floor[x-1][y]==nextSpace) {
			up = dp(nextSpace,x-1,y);
		}
		if(inBounds(x+1,y) && floor[x+1][y]==nextSpace) {
			down = dp(nextSpace,x+1,y);
		}
		if(inBounds(x,y-1) && floor[x][y-1]==nextSpace) {
			left = dp(nextSpace,x,y-1);
		}
		if(inBounds(x,y+1) && floor[x][y+1]==nextSpace) {
			right = dp(nextSpace,x,y+1);
		}
		visited[x][y] = false;
		
		return memo[x][y] = ans+Math.max(up, Math.max(down, Math.max(left, right)));
	}
	private boolean inBounds(int x, int y) {
		if(x>=N || y>=M || x<0 || y<0) {
			return false;
		}
		return true;
	}
	class Pair {
		int x,y;
		public Pair(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	int N;
	int M;
	HashMap<Character, Character> next;
	char[][] floor;
	int[][] memo;
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		M = in.nextInt();
		
		next = new HashMap<>();
		next.put('D', 'I');
		next.put('I', 'M');
		next.put('M', 'A');
		next.put('A', 'D');
		//
		floor = new char[N][M];
		visited = new boolean[N][M];
		ArrayList<Pair> starts = new ArrayList<>();
		for(int n=0; n<N; n++) {
			String line = in.next();
			for(int m=0; m<M;m++) {
				floor[n][m]=line.charAt(m);
				if(floor[n][m]=='D') {
					starts.add(new Pair(n,m));
				}
			}
		}
		memo = new int[N+1][M+1];
		for(int n=0;n<N;n++)Arrays.fill(memo[n], -1);
		
		int ans = 0;
		for(Pair p:starts)
			ans =  Math.max(ans, dp('D',p.x,p.y));
		if(ans==0) {
			out.println("Poor Dima!");
		} else {
			out.println(ans);
		}
	}
}
