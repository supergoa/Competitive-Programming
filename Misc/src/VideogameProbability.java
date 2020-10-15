import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class VideogameProbability {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new VideogameProbability().solve(in,out);
		in.close();
		out.close();
	}

	class Item {
		int needed;
		double prob;
		
		public Item(int a, double b) {
			needed = a;
			prob = b;
		}
	}
	
	public double dp(int numLeft, int itemNum) {
		if(numLeft == 0 || itemNum == -1) return 1;
		
		
	}
	
	Item[] items;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int G = in.nextInt();
			items = new Item[G];
			int total = 0;
			for(int g=0; g<G; g++) {
				items[g] = new Item(in.nextInt(), in.nextDouble());
				total += items[g].needed;
			}
			
			int chances = in.nextInt();
			if(total > chances) {
				out.print(0);
				return;
			}
			double ans = dp(chances, G-1);
		}
		
	}
}
