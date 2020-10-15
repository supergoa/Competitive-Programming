import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class I2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new I2().solve(in, out);
		in.close();
		out.close();
		
	}

	private void solve(Scanner in, PrintWriter out) {
		in.nextLine();
		int N = in.nextInt();
		HashMap<String, Integer>[] occurences = new HashMap[200];
		int length = 0;
		for(int n=0; n<N; n++) {
			
			String[] line = in.nextLine().split(", ");
			if(line.length==1) {
				//System.out.println("wtf");
				n--;
				continue;
			}
			length = Math.max(length, line.length);
			//System.out.println(Arrays.toString(line));
			
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
		ArrayList<String>[] strings = new ArrayList[length];
		for(int i=0; i<lengt)
		for(int i=0; i<length; i++) {
			for(String a: occurences[i].keySet()) {
				if(maxes[i]==occurences[i].get(a)) {
					strings[i].add(a);
				}
			}
		}
		ArrayList<String> answers = new ArrayList<>();
		for(int i=0; i<length; i++) {
			int numNeeded = strings[i].size()-1;
			for(int k=0; k<numNeeded; k++) {
				
			}
			for(int j=0; j<strings[i].size(); i++) {
				
			}
		}
	}
}
