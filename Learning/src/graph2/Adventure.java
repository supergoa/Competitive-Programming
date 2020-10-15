package graph2;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Adventure {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Adventure().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int p = in.nextInt();
			in.nextLine();
			
			ArrayList<Integer>[] pages = new ArrayList[p];
			
			int[] inDeg = new int[p];
			for(int i=0; i<p; i++) {
				pages[i] = new ArrayList<>();
				String next = in.nextLine().trim();
				
				if(!next.equals("ENDING")) {
					String[] line = next.split(" ");
					for(int j=0; j<line.length; j++) {
						int x =Integer.parseInt(line[j])-1;
						pages[i].add(x);
						inDeg[x]++;
					}
				}	
			}
			
			
			// topsort
			
			ArrayDeque<Integer> pq = new ArrayDeque<>();
			ArrayList<Integer> order = new ArrayList<>();
			for(int i=0; i<p; i++) {
				if(inDeg[i]==0) {
					pq.add(i);
				}
			}
			while(!pq.isEmpty()) {
				int node = pq.poll();
				order.add(node);
				for(int adj: pages[node]) {
					inDeg[adj]--;
					if(inDeg[adj]==0) {
						pq.add(adj);
					}
				}
			}
			Collections.reverse(order);
			if(t==9) System.out.println(order);
			
			/*int max = 1;
			int[] longest = new int[p];
			for(int node:order) {
				int curAns = 1;
				for(int child:pages[node]) {
					curAns = Math.max(curAns, longest[child]+1);
				}
				longest[node] = curAns;
				max = Math.max(longest[node], max);
			}*/ 
			int[] longest = new int[p];
            int ans = 1;
            for(int x : order) {
                int curAns = 1;
                for(int child : pages[x]) {
                    curAns = Math.max(curAns, 1 + longest[child]);
                }
                longest[x] = curAns;
                ans = Math.max(ans, longest[x]);
            }
			out.println("Book #"+(t+1)+": The longest story is "+(ans)+" pages. ");
		}	
	}
}
