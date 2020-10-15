import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class FoxandNames2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new FoxandNames2().solve(in,out);
		in.close();
		out.close();
		
	}
	class Letter {
		Character upper, lower;
		Character c;
		public Letter(Character a, Character b, Character c) {
			this.c = a;
			lower = b;
			upper = c;
		}
	}
	ArrayList<Integer>[] adj;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		adj = new ArrayList[26];
		for(int i=0; i<26; i++) adj[i] = new ArrayList<>();
		int[] inDeg = new int[26];
		
		String[] arr = new String[N];
		for(int n=0; n<N; n++) {
			arr[n] = in.next();
		}
		
		for(int n=1; n<N; n++) {
			String str1 = arr[n-1];
			String str2 = arr[n];
			Character c1 = null;
			Character c2 = null;
			int len = Math.min(str1.length(), str2.length());
			boolean different = false;
			for(int i=0; i<len; i++) {
				if(str1.charAt(i)!=str2.charAt(i)) {
					different = true;
					adj[str1.charAt(i)-'a'].add(str2.charAt(i)-'a');
					inDeg[str2.charAt(i)-'a']++;
					break;
				}
			}
			if(!different && str1.length() > str2.length()) {
				out.print("Impossible");
				return;
			}
		}
		if(topSort(inDeg)!=0) {
			//HashSet<Character> used = new HashSet<>();
			for(int i=0; i<order.size(); i++) {
				out.print((char)(order.get(i)+'a'));
				//used.add((char)(order.get(i)+'a'));
			}
			//for(Character a:used) {
			//	System.out.println("used: " + a);
			//}
			//for(int i=0; i<26; i++) {
			//	if(!used.contains(Character.valueOf((char) ('a'+i)))) {
			//		out.print((char)('a'+i));
			//	}
			//}
		} else {
			out.print("Impossible");
		}
		
	}
	
	ArrayList<Integer> order = new ArrayList<>();
	private int topSort(int[] inDeg) {
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		
		for(int d=0; d<inDeg.length; d++) {
			if(inDeg[d]==0) {
				q.add(d);
			}
		}
		int numVisited=0;
		boolean mult = false;
		while(!q.isEmpty()) {
			if(q.size()>1) {
				mult=true;
			}
			int node = q.poll();
			numVisited++;
			order.add(node);
			for(int v: adj[node]) {
				inDeg[v]--;
				if(inDeg[v]==0) {
					q.add(v);
				}
			}
		}
		if(26==numVisited) {
			if(mult) {
				return 2;
			}
			return 1;
		} else {
			return 0;
		}
	}
}
