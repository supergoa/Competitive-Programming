import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;


public class B1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B1().solve(in,out);
		in.close();out.close();
			
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int K = in.nextInt();
		int A = in.nextInt();
		int B = in.nextInt();
		HashSet<String> newTrains = new HashSet<>();
		for(int k=0; k<K; k++) {
			newTrains.add((in.nextInt()-1) + "," + (in.nextInt()-1));
		}
		boolean toEndAPrice = (newTrains.contains("0,"+(N-1)))?true:false;
		int ans1 = 987654321;
		if(toEndAPrice) {
			ans1 = A;
		} else {
			ans1 = B;
		}
		
		int ans2 = 987654321;
		if(toEndAPrice) {
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean[] visited = new boolean[N];
			int[] level = new int[N];
			q.add(0);
			level[0] = 0;
			while(!q.isEmpty()) {
				int node = q.poll();
				if(visited[node]) continue;
				visited[node] = true;
				if(!newTrains.contains(node+","+(N-1))) {
					ans2=(level[node]+1)*B;
					break;
				}
				for(int i=0; i<N; i++) {
					if(!visited[i] && !newTrains.contains(node+","+i)) {
						q.add(i);
						level[i] = level[node] + 1;
					}
				}
			}
			out.println(Math.min(ans1, ans2));
		} else {
			ArrayDeque<Integer> q = new ArrayDeque<>();
			boolean[] visited = new boolean[N];
			int[] level = new int[N];
			q.add(0);
			level[0] = 0;
			while(!q.isEmpty()) {
				int node = q.poll();
				if(visited[node]) continue;
				visited[node] = true;
				if(newTrains.contains(node+","+(N-1))) {
					ans2=(level[node]+1)*A;
					break;
				}
				for(int i=0; i<N; i++) {
					if(!visited[i] && newTrains.contains(node+","+i)) {
						q.add(i);
						level[i] = level[node] + 1;
					}
				}
			}
			out.println(Math.min(ans1, ans2));
		}
	}
	
}
