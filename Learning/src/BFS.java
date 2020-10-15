import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class BFS {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		ArrayList<Integer>[] edges = new ArrayList[N+1]; // 1 indexed
		
		for(int i=1; i<N+1; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i=1; i<N; i++) { // 1 indexed
			int to = scan.nextInt();
			int from = scan.nextInt();
			//System.out.println("to:" + to);
			//System.out.println("from:"+from);
			//System.out.println("i:"+i);
			edges[to].add(from);
			edges[from].add(to);
			//System.out.println("to:" + to);
			//System.out.println("from:"+from);
			//System.out.println("i:"+i);
		}
		
		int[] level = new int[N+1]; // will store each level of the current node multiple times
		int levelToCount = scan.nextInt();
		
		//BFS
		ArrayDeque<Integer> qq = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		int root = 1;
		qq.add(root);
		visited[root] = true;
		level[root] = 1;
		
		while(!qq.isEmpty()) {
			int currNode = qq.pop();
			for(Integer i = 0; i < edges[currNode].size(); i++) {
				if(!visited[edges[currNode].get(i)]) {
					qq.add(edges[currNode].get(i));
					visited[edges[currNode].get(i)] = true;
					level[edges[currNode].get(i)] = level[currNode] + 1;
					//System.out.print(edges[currNode].get(i)+",");
				}
			}
			
		}
		//System.out.println("LeveltoC"+levelToCount);
		 int count = 0;
		for(int i : level) {
			if(i==levelToCount) {
				count++;//System.out.println(i);
			}
		}
		System.out.println(count);
	}
}
