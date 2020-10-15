import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class BookingSystem {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new BookingSystem().solve(in,out);
		in.close();out.close();
	}
	class Group implements Comparable<Group>{
		int c,p,id;
		public Group(int a, int b, int id) {
			c = a;
			p = b;
			this.id=id;
		}
		@Override
		public int compareTo(Group o) {
			if(p==o.p) return -Integer.compare(o.c, c);
			return -Integer.compare(p, o.p);
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		PriorityQueue<Group> pq = new PriorityQueue<>();
		
		for(int n=0; n<N; n++) {
			pq.add(new Group(in.nextInt(), in.nextInt(), n+1));
		}
		
		int K = in.nextInt();
		TreeMap<Integer, Integer> tables = new TreeMap<>();
		TreeMap<Integer, ArrayList<Integer>> tableIDs = new TreeMap<>();
		for(int k=0; k<K; k++) {
			int t = in.nextInt();
			tables.put(t, tables.getOrDefault(t, 0)+1);
			if(tableIDs.get(t)==null)tableIDs.put(t, new ArrayList<>());
			tableIDs.get(t).add(k+1);
		}
	
		int requests = 0;
		int money = 0;
		ArrayList<String> answers = new ArrayList<>();
		while(!pq.isEmpty()) {
			Group g = pq.poll();
			Integer table = tables.ceilingKey(g.c);
			if(table!=null) {
				requests++;
				money+=g.p;
				answers.add(g.id + " "+ tableIDs.get(table).remove(0));
				tables.put(table, tables.get(table)-1);
				if(tables.get(table)==0) {
					tables.remove(table);
					tableIDs.remove(table);
				}
			}
		}
		out.println(requests + " " + money);
		for(String i:answers) {
			out.println(i);
		}
	}
}
