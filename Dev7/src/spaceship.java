import java.io.PrintWriter;
import java.util.Scanner;

public class spaceship {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new spaceship().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int x = in.nextInt();
			int y = in.nextInt();
			int z = in.nextInt();
			int E = in.nextInt();
			
			int count = 0;
			for(int e=0; e<E; e++) {
				int xx = in.nextInt();
				int yy = in.nextInt();
				int zz = in.nextInt();
				int dd = in.nextInt();
				if(Math.sqrt(Math.abs(xx-x)*Math.abs(xx-x) + Math.abs(yy-y)*Math.abs(yy-y) + Math.abs(zz-z)*Math.abs(zz-z)) <= dd) {
					count++;
				}
			}
			System.out.println("You will be picked up by " + count + " radars.");
		}
		
	}
}
