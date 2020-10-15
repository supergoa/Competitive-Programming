import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class calc {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new calc().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		boolean[] visited = new boolean[(int) (1e9+1)];
		HashMap<Integer, Integer> level = new HashMap<>();
		HashSet<Integer> toFind = new HashSet<>();
		ArrayDeque<Integer> q = new ArrayDeque<>();
		HashMap<Integer, Integer> ans = new HashMap<>();
		
		ans.put(1,10);
		ans.put(2,9);
		ans.put(3,11);
		ans.put(4,10);
		ans.put(5,9);
		ans.put(6,9);
		ans.put(7,9);
		ans.put(8,9);
		ans.put(9,11);
		ans.put(10,10);
		ans.put(11,10);
		ans.put(12,9);
		ans.put(13,9);
		ans.put(14,9);
		ans.put(15,9);
		ans.put(16,10);
		ans.put(17,9);
		ans.put(18,11);
		ans.put(19,10);
		ans.put(20,11);
		ans.put(21,11);
		ans.put(22,11);
		ans.put(23,11);
		ans.put(24,10);
		ans.put(25,10);
		ans.put(26,9);
		ans.put(27,10);
		ans.put(28,10);
		ans.put(29,10);
		ans.put(30,10);
		ans.put(31,10);
		ans.put(32,11);
		ans.put(33,11);
		ans.put(34,10);
		ans.put(35,10);
		ans.put(36,12);
		ans.put(37,12);
		ans.put(38,11);
		ans.put(39,11);
		ans.put(40,12);
		ans.put(41,12);
		ans.put(42,12);
		ans.put(43,12);
		ans.put(44,12);
		ans.put(45,12);
		ans.put(46,12);
		ans.put(47,12);
		ans.put(48,11);
		ans.put(49,11);
		ans.put(50,11);
		ans.put(51,11);
		ans.put(52,10);
		ans.put(53,10);
		ans.put(54,11);
		ans.put(55,11);
		ans.put(56,10);
		ans.put(57,10);
		ans.put(58,10);
		ans.put(59,10);
		ans.put(60,11);
		ans.put(61,11);
		ans.put(62,11);
		ans.put(63,11);
		ans.put(64,10);
		ans.put(65,10);
		ans.put(66,11);
		ans.put(67,11);
		ans.put(68,11);
		ans.put(69,11);
		ans.put(70,11);
		ans.put(71,11);
		ans.put(72,11);
		ans.put(73,11);
		ans.put(74,11);
		ans.put(75,11);
		ans.put(76,11);
		ans.put(77,11);
		ans.put(78,11);
		ans.put(79,11);
		ans.put(80,10);
		ans.put(81,11);
		ans.put(82,11);
		ans.put(83,11);
		ans.put(84,11);
		ans.put(85,11);
		ans.put(86,11);
		ans.put(87,11);
		ans.put(88,11);
		ans.put(89,11);
		ans.put(90,12);
		ans.put(91,12);
		ans.put(92,11);
		ans.put(93,12);
		ans.put(94,12);
		ans.put(95,12);
		ans.put(96,11);
		ans.put(97,11);
		ans.put(98,11);
		ans.put(99,12);
		
		for(int i=1; i<=99; i++) {
		//	System.out.println(i);
		}
		for(int n=0; n<N;n++) {
			int K = in.nextInt();
			out.println(ans.get(K));
			
			/*Arrays.fill(visited, false);
			level.clear();
			visited[K]=true;
			level.put(K,0);
			
			toFind.clear();
			for(int i=1; i<100; i++) {
				if(i==K) continue;
				toFind.add(i);
			}
			
			q.clear();
			q.add(K);
			int maxLevel = 0;
			while(!toFind.isEmpty()) {
				int p = q.poll();
				int adj1 = p+1;
				int adj2 = p*3;;
				int adj3 = p/2;
				if(adj1>0 && adj1<1e9 && !visited[adj1]) {
					q.add(adj1);
					level.put(adj1, level.get(p)+1);
					maxLevel = Math.max(level.get(adj1), maxLevel);
					visited[adj1] = true;
					toFind.remove(adj1);
				}
				if(adj2>0 && adj2<1e9 && !visited[adj2]) {
					q.add(adj2);
					level.put(adj2, level.get(p)+1);
					maxLevel = Math.max(level.get(adj2), maxLevel);
					visited[adj2] = true;
					toFind.remove(adj2);
				}
				if(adj3>0 && adj3<1e9 && !visited[adj3]) {
					q.add(adj3);
					level.put(adj3, level.get(p)+1);
					maxLevel = Math.max(level.get(adj3), maxLevel);
					visited[adj3] = true;
					toFind.remove(adj3);
				}
			}
			out.println("ans.put("+K+","+maxLevel+");");
			out.flush();
			*/
			
		}
		
	}
}
