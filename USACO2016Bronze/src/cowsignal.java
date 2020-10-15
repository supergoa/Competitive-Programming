import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class cowsignal {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("cowsignal.in"));//System.in);
		PrintWriter out = new PrintWriter(new File("cowsignal.out"));
		new cowsignal().solve(in,out);
		in.close();
		out.close();
	}

	// X - 88
	// . - 46
	private void solve(Scanner in, PrintWriter out) {
		//System.out.println((int)'X');
		//System.out.println((int)'.');
		int M = in.nextInt();
		int N = in.nextInt();
		int K = in.nextInt();
		
		ArrayList<Character>[] grid = new ArrayList[M*K];
		for(int i=0; i<M*K; i++) grid[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			char[] line = in.next().toCharArray();
			for(int j=0; j<N; j++) {
				grid[i].add(line[j]);
			}
			//System.out.println(grid[i]);
		}
		
		for(int i=0; i<M; i++) {
			for(int j=0; j<N*K; j+=K) {
				
				Character x = grid[i].get(j);
				//System.out.println(j + " " + x);
				for(int k=1; k<K; k++) {
					grid[i].add(j,x);
				}
				
			}
			//System.out.println(grid[i]);
		}
		for(int i=0; i<M; i++) {
			for(int k=0; k<K; k++) {
				for(Character a: grid[i]) {
					out.print(a);
				}
				out.println();
			}
		}
	}
}
