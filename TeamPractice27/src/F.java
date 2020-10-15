import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class F {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new F().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int G = in.nextInt();
		PriorityQueue<Integer> games = new PriorityQueue<>();
		int totalPoints = 0;
		for(int n=0; n<N; n++) {
			int S = in.nextInt();
			int R = in.nextInt();
			if(R-S<0) {
				//System.out.println(1);
				totalPoints += 3;
				continue;
			}
			games.add(R-S);
		}
		while(!games.isEmpty()) {
			int num = games.poll();
			if(G-(num+1) >= 0) {
				G -= (num+1);
				//System.out.println(2);
				totalPoints += 3;
			} else if(G-(num) >= 0){
				G -= (num);
				//System.out.println(3);
				totalPoints += 1;
			}
		}
		System.out.println(totalPoints);
	}
}
