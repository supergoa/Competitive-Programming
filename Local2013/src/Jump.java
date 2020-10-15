import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class Jump {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Jump().solve(in,out);
		in.close();
		out.close();
	}

	class Point{
		int x,y;
		public Point(int a, int b) {
			x = a;
			y = b;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int x1 = in.nextInt();
			int y1 = in.nextInt();
			int x2 = in.nextInt();
			int y2 = in.nextInt();
			int xDist = Math.abs(x2-x1);
			int yDist = Math.abs(y2-y1);
			
			int y = y1;
			boolean flag1 = false;
			while(y<=xDist) {
				y+=x1;
				if(y==xDist) flag1=true;
			}
			
			int x = x1;
			boolean flag2 = false;
			while(x<=yDist) {
				x+=y1;
				if(x==yDist) flag2=true;
			}
			if(flag1&&flag2) {
				out.println(1);
			} else {
				out.println(0);
			}
			out.flush();
		}
		//ArrayDeque<Point> 
	}
}
