import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class missing {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new missing().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
			
			int op = N;
			for(int i=1; i<N/2; i+=2) {
				if(!hm.containsKey(i))  {
					hm.put(i,  new ArrayList<>());
				}
				if(!hm.containsKey(i+1))  {
					hm.put(i+1,  new ArrayList<>());
				}
				if(!hm.containsKey(op))  {
					hm.put(op,  new ArrayList<>());
				}
				if(!hm.containsKey(op-1))  {
					hm.put(op-1,  new ArrayList<>());
				}
				hm.get(i).add(i);
				hm.get(i).add(i+1);
				hm.get(i).add(op);
				hm.get(i).add(op-1);
				
				hm.get(i+1).add(i);
				hm.get(i+1).add(i+1);
				hm.get(i+1).add(op);
				hm.get(i+1).add(op-1);
				
				hm.get(op).add(i);
				hm.get(op).add(i+1);
				hm.get(op).add(op);
				hm.get(op).add(op-1);
				
				hm.get(op-1).add(i);
				hm.get(op-1).add(i+1);
				hm.get(op-1).add(op);
				hm.get(op-1).add(op-1);
				//System.out.println(i + " " + (i+1));
				op-=2;
			}
			int P = in.nextInt();
			ArrayList<Integer> ans = hm.get(P);
			Collections.sort(ans);
			for(int a:ans) {
				if(a==P) continue;
				System.out.print(a + " ");
			}
			System.out.println();
		}
		
	}
}
