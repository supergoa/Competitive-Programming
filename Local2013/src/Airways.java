import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Airways {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Airways().solve(in,out);
		in.close();
		out.close();
	}
	int N;
	class Flight implements Comparable<Flight>{
		int id, flightNum;
		String from, to;
		public Flight(int a, int d, String b, String c) {
			id = a;
			flightNum = d;
			from = b;
			to = c;
		}
		
		@Override
		public int compareTo(Flight o) {
			return this.flightNum - o.flightNum;
		}
	}
	ArrayList<Integer>[] adj;
	Flight[] flights;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0;t<T; t++) {
			N = in.nextInt();
			order.clear();
			
			//HashMap<String, ArrayList<Integer>> endWith = new HashMap<>();
			HashMap<String, ArrayList<Integer>> startWith = new HashMap<>();
			
			int[] inDeg = new int[N];
			flights = new Flight[N];
			
			adj = new ArrayList[N];
			for(int i=0; i<N; i++) adj[i] = new ArrayList<>();
			
			for(int n=0; n<N; n++) {
				String a = in.next();
				String b = in.next();
				if(!startWith.containsKey(a)) startWith.put(a, new ArrayList<>());
				//if(!endWith.containsKey(b)) endWith.put(b, new ArrayList<>());
				startWith.get(a).add(n);
				//endWith.get(b).add(n);
				flights[n] = new Flight(n, in.nextInt(), a, b);
			}
			for(int n=0; n<N; n++) {
				ArrayList<Integer> otherFlights = startWith.get(flights[n].to);
				if(otherFlights==null) continue;
				for(int i:otherFlights) {
					adj[n].add(i);
					inDeg[i]++;
				}
			}
			topSort(inDeg);
			for(int i:order) {
				out.print(flights[i].flightNum + " ");
			}
			out.println();
		}
		
	}
	ArrayList<Integer> order = new ArrayList<>();
	private int topSort(int[] inDeg) {
		PriorityQueue<Flight> q = new PriorityQueue<Flight>();
		
		for(int d=0; d<inDeg.length; d++) {
			if(inDeg[d]==0) {
				q.add(flights[d]);
			}
		}
		
		int numVisited=0;
		boolean mult = false;
		
		while(!q.isEmpty()) {
			if(q.size()>1) mult=true;
			int node = q.poll().id;
			
			numVisited++;
			order.add(node);
			for(int v: adj[node]) {
				inDeg[v]--;
				if(inDeg[v]==0) {
					q.add(flights[v]);
				}
			}
		}
		if(N==numVisited) {
			if(mult) return 2; //multiple valid orders
			return 1; //one valid orders
		} else {
			return 0; //no valid orders
		}
	}
}
