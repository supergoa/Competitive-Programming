import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class starrectangles {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new starrectangles().solve(in,out);
		in.close();
		out.close();
	}

	class Point implements Comparable<Point>{
		int x; int y;
		public Point(int a, int b) {
			x=a;
			y=b;
		}
		@Override
		public int compareTo(Point o) {
			if(x==o.x) {
				return Integer.compare(this.y, o.y);
			}
			return Integer.compare(this.x, o.x);
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int M = in.nextInt();
		for(int m=0; m<M; m++) {
			HashMap<Integer, HashSet<Integer>> xtoys = new HashMap<>();
			HashMap<Integer, HashSet<Integer>> ytoxs = new HashMap<>();
			
			int N = in.nextInt();
			Point[] points = new Point[N];
			for(int n=0; n<N; n++) {
				Point p;
				int x,y;
				x = in.nextInt();
				y = in.nextInt();
				if(!xtoys.containsKey(x)) xtoys.put(x, new HashSet<>());
				if(!ytoxs.containsKey(y)) ytoxs.put(y, new HashSet<>());
				p = new Point(x,y);
				xtoys.get(x).add(p.y);
				ytoxs.get(y).add(p.x);
				points[n] = p;
			}
			Arrays.sort(points);
			int biggestArea = 0;
			ArrayList<Point> ans = new ArrayList<>();
			for(int p=0; p<N; p++) {
				int x1 = points[p].x;
				int y1 = points[p].y;
				
				for(Integer onLiney: xtoys.get(x1)) {
					if(onLiney==y1)continue;
					int x2 = x1;
					int y2 = onLiney;
					
					for(Integer onLinex: ytoxs.get(y1)) {
						if(onLinex==x1)continue;
						int y3 = y1;
						int x3 = onLinex;
						
						if(xtoys.get(x3).contains(y2) && ytoxs.get(y2).contains(x3)) {
							if(Math.abs((y2-y1)*(x3-x1)) > biggestArea) {
								biggestArea = Math.abs((y2-y1)*(x3-x1));
								ans.clear();
								ans.add(new Point(x1,y1));
								ans.add(new Point(x2,y2));
								ans.add(new Point(x3,y3));
								ans.add(new Point(x3,y2));
							}
						}
						
						
					}
				}
			}
			//System.out.println(biggestArea);
			Collections.sort(ans);
			for(int i=0; i<4; i++) {
				if(i!=3) System.out.print("("+ans.get(i).x+","+ans.get(i).y+"),");
				else System.out.println("("+ans.get(i).x+","+ans.get(i).y+")");
			}
			
		}
		
	}
}
