import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class I4 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new I4().solve(in, out);
		in.close();
		out.close();
		
	}

	private void solve(Scanner in, PrintWriter out) {
		in.nextLine();
		int N = in.nextInt();
		String[] input = new String[N];
		HashMap<String, Integer>[] occurences = new HashMap[200];
		int length = 0;
		for(int n=0; n<N; n++) {
			while(input[n]==null || input[n].equals("")) {
				input[n] = in.nextLine();
			}
			String[] line = input[n].split(", ");
			length = Math.max(length, line.length);
			
			for(int i=0; i<line.length; i++) {
				if(occurences[i]==null)occurences[i]=new HashMap<>();
				occurences[i].put(line[i], occurences[i].getOrDefault(line[i],0)+1);
			}
			
		}
		
		int[] maxes = new int[length];
		for(int i=0; i<length; i++) {
			int max = 0;
			for(String a: occurences[i].keySet()) {
				max = Math.max(max, occurences[i].get(a));
			}
			maxes[i] = max;
		}
		
		for(int inp=0; inp<N; inp++) {
			String[] line = input[inp].split(", ");
			boolean goodInput = true;
			for(int i=0; i<length; i++) {
				if(occurences[i].get(line[i]) != maxes[i]) {
					goodInput = false;
				}
			}
			if(goodInput) {
				out.println(input[inp]);
			}
		}
	}
}
