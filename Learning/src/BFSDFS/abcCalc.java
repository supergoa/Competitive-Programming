package BFSDFS;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

public class abcCalc {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new abcCalc().solve(scan, out);
		scan.close();
		out.close();
	}

	private void solve(Scanner scan, PrintWriter out) {
		int N = scan.nextInt();
		for(int n=0; n<N; n++) {
			int a = scan.nextInt();
			int b = scan.nextInt();
			int c = scan.nextInt();
			
			int end = scan.nextInt();
			int start = 0;
			
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean[] visited = new boolean[1000001];
			int[] distance = new int[1000001];
			visited[start] = true;
			distance[start] = 0;
			q.add(start);
			
			while(!q.isEmpty()) {
				int parent = q.pop();
				int[] children = {parent + a, parent * b, parent/c};
				for(int val : children) {
					while(val >= 1000000) {
						val-=1000000;
					}
					if(!visited[val]) {
						q.add(val);
						distance[val] = distance[parent] + 1;
						visited[val] = true;
					}
				}
				
			}
			System.out.println((distance[end]==0)?-1:distance[end]);
			
		}
		
	}

}
