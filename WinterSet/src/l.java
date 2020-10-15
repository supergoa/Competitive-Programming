import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class l {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new l().solve(in,out);
		in.close();
		out.close();
	}

	class path {
		int a, b;
		public path(int e, int f) {
			a=e;
			b=f;
		}
	}
	HashSet<Integer>[] adj;
	HashMap<Integer, ArrayList<path>> edgeToPaths;
	int[][] freq;
	int N;
	int[][] edgeIDs;
	private void solve(Scanner in, PrintWriter out) {

		N = in.nextInt();
		int M = in.nextInt();
		
		adj = new HashSet[N];
		for(int n=0; n<N; n++) adj[n] = new HashSet<>();
		
		for(int m=0; m<M; m++) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			adj[a].add(b);
			//adj[b].add(a);
		}
		
		edgeIDs = new int[N][N];
		int id=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j) continue;
				//edgeIDs[i][j] = id;
				edgeIDs[i][j] = id++;
				System.out.println("ID: " + (id-1) + " " + i + " " + j);
			}
		}
		
		edgeToPaths = new HashMap<>();
		freq = new int[N][N];
		dfs(0, 0, new path(-1,-1));
		for(int i=0; i<N; i++) {
			System.out.println(Arrays.toString(freq[i]));
		}
		for(Integer i : edgeToPaths.keySet()) {
			for(int a=0; a<edgeToPaths.get(i).size(); a++) {
				System.out.println(i + " "  + (edgeToPaths.get(i).get(a).a+1) + " " + (edgeToPaths.get(i).get(a).b+1));
			}
		}
	}
	private int dfs(int cur, int depth, path p) { //add a b as parameters, they arent working

		if(cur==N-1) {
			
			path place = new path(p.a, p.b);
			System.out.println("happen" + depth + " " + p.a + " " + p.b);
			if(p.a==-1) {
				System.out.println("ay");
				freq[0][N-1]++;
				if(edgeToPaths.get(edgeIDs[0][N-1])==null) edgeToPaths.put(edgeIDs[0][N-1], new ArrayList<>());
				edgeToPaths.get(edgeIDs[0][N-1]).add(place);
			}
			else if(p.b==-1) {
				place.b=N-1;
				System.out.println("be");
				//freq[place.a][N-1]++;
				if(edgeToPaths.get(edgeIDs[0][place.a])==null) edgeToPaths.put(edgeIDs[0][place.a], new ArrayList<>());
				if(edgeToPaths.get(edgeIDs[place.a][N-1])==null) edgeToPaths.put(edgeIDs[place.a][N-1], new ArrayList<>());
				
				edgeToPaths.get(edgeIDs[0][place.a]).add(place);
				edgeToPaths.get(edgeIDs[place.a][N-1]).add(place);
			}
			return 1;
		}
		if(depth==2 && !adj[cur].contains(N-1)) return 0;
		if(depth==2 && adj[cur].contains(N-1)) {
			System.out.println((cur+1) + " " + (N));
			freq[cur][N-1]++;
			p.b=cur;
			
			path place = new path(p.a, p.b);
			if(edgeToPaths.get(edgeIDs[0][place.a])==null) edgeToPaths.put(edgeIDs[0][place.a], new ArrayList<>());
			if(edgeToPaths.get(edgeIDs[place.a][place.b])==null) edgeToPaths.put(edgeIDs[place.a][place.b], new ArrayList<>());
			if(edgeToPaths.get(edgeIDs[place.b][N-1])==null) edgeToPaths.put(edgeIDs[place.b][N-1], new ArrayList<>());
			
			edgeToPaths.get(edgeIDs[0][place.a]).add(place);
			edgeToPaths.get(edgeIDs[place.a][place.b]).add(place);
			edgeToPaths.get(edgeIDs[place.b][N-1]).add(place);
			return 1;
		}
		
		
		int ret = 0;
		int toRet = 0;
		for(Integer i: adj[cur]) {
			System.out.println("a: "+(cur+1) + " " + depth + " " + (i+1)); 
			if(depth==1) p.a=cur;
			ret=dfs(i, depth+1, p);
			freq[cur][i] += ret;
			toRet += ret;
			p.b=-1;
			//if(freq[cur][i]>0) iter++;
			//System.out.println("b: " + freq[cur][i] + " " + iter);
		}
		
		System.out.println("b: "+cur + " " + toRet);
		return toRet;
	}
}
