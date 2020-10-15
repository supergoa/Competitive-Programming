import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

public class Haybales {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("haybales.in"));
		PrintWriter out = new PrintWriter(new File("haybales.out"));
		new Haybales().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int Q = in.nextInt();
		TreeMap<Integer, Integer> bales = new TreeMap<>();
		int[] pos = new int[N];
		for(int n=0; n<N; n++) {
			pos[n] = in.nextInt();
		}
		Arrays.sort(pos);
		
		bales.put(pos[0], 1);
		for(int n=1; n<N; n++) {
			int loc = pos[n];
			bales.put(loc, bales.get(bales.lowerKey(loc)) + 1);
			//System.out.println("1 " + loc + " " + bales.get(bales.lowerKey(loc)));
		}
		for(int q=0; q<Q; q++) {
			int a = in.nextInt();
			int b = in.nextInt();
			//System.out.println(a+" "+b );
			Integer upper = bales.floorKey(b);
			Integer lower = bales.lowerKey(a);
			if(lower==null) {
				out.println(bales.get(upper));
			} else {
				out.println(bales.get(upper) - bales.get(lower));
			}
			//out.flush();
		}
	}
}
