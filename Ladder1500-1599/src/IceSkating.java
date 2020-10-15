import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class IceSkating {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new IceSkating().solve(in,out);
		in.close();out.close();
	}

	class Point {
		int id,x,y;
		public Point(int id, int x, int y) {
			this.id = id;
			this.x=x;
			this.y=y;
		}
	}
	int ans = 0;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		Point[] drifts = new Point[N];
		HashMap<Integer, ArrayList<Point>> horz = new HashMap<>();
		HashMap<Integer, ArrayList<Point>> vert = new HashMap<>();
		for(int n=0; n<N; n++) {
			int x = in.nextInt();
			int y = in.nextInt();
			drifts[n] = new Point(n, x, y);
			if(horz.get(x)==null) horz.put(x, new ArrayList<>());
			if(vert.get(y)==null) vert.put(y, new ArrayList<>());
			horz.get(x).add(drifts[n]);
			vert.get(y).add(drifts[n]);
		}
	
		ArrayList<Integer>[] adj = new ArrayList[N]; for(int i=0; i<N; i++) adj[i] = new ArrayList<>();
		for(int n=0; n<N; n++) {
			int x = drifts[n].x;
			int y = drifts[n].y;
			for(Point i : horz.get(x)) {
				if(n!=i.id) {
					adj[n].add(i.id);
				}
			}
			for(Point i : vert.get(y)) {
				if(n!=i.id) {
					adj[n].add(i.id);
				}
			}
		}
		ArrayDeque<Integer> qq = new ArrayDeque<>();
		boolean[] visited = new boolean[N];;
		qq.add(0);
		boolean notDone = true;
		while(notDone) {			
			while(!qq.isEmpty()) {
				int drift = qq.poll();
				if(visited[drift]) continue;
				visited[drift] = true;
				for(int node : adj[drift]) {
					if(!visited[node]) {
						qq.add(node);
					}
				}
			}
			notDone = false;
			for(int i=0; i<N; i++) {
				if(!visited[i]) {
					notDone = true;
					ans++;
					qq.add(i);
					break;
				}
			}	
		}		
		System.out.println(ans);
	}
}
