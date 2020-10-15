

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BRound526 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new BRound526 ().solve(in,out);
		in.close();
		out.close();
	}

	class Bucket {
		int id, val;
		public Bucket(int a, int b) {
			id = a;
			val = b;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		long S = in.nextLong();
		PriorityQueue<Long> pq = new PriorityQueue<>();
		long smallest = Long.MAX_VALUE;
		for(int i=0; i<N; i++) {
			long x = in.nextLong();
			pq.add(-x);
			smallest = Math.min(smallest, x);
		}
		

		while(S>0) {
			long vol = -pq.poll();
			if(vol==smallest) break;
			
			long toSubtract = vol - smallest;
			S -= toSubtract;
			
			pq.add(-(vol - toSubtract));
			
		}
		long answer = 0;
		if(S>0) {
			long need = (long) Math.ceil((double)S/(double)N);
			long allAre = smallest;
			if(allAre >= need) {
				answer = allAre-need;
			} else {
				answer = -1;
			}
		} else {
			answer = smallest ;
		}
		System.out.println(answer);
		
	}
}
