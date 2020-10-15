import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class stars {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new stars().solve(in,out);
		in.close();
		out.close();
	}

	class Star {
		ArrayList<Integer> closestStarIDs = new ArrayList<>();
		int id;
		int x; int y;
		public Star(int a, int b) {
			x = a;
			y = b;
		}
	}
	

	public class DSU {
		int[] id;
		int size;
		DSU(int x) { //x is size
			size = x + 1;
			id = new int[size]; // disj relation
			for (int i = 0; i < size; ++i) {
				id[i] = i;
			}
		}
		int root(int a) { //get root of a. "id[a] = get(id[a])" makes retrieval O(1) on successive calls
			return id[a] == a ? a : (id[a] = root(id[a]));
		}
		
		void union(int a, int b) {
			if(root(a) == root(b)) return;
			id[root(a)] = id[root(b)];
			--size;
		}
	}

	private void solve(Scanner in, PrintWriter out) {
		int counter = 1;
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			Star[] stars = new Star[N];
			for(int n=0; n<N; n++) {
				stars[n] = new Star(in.nextInt(), in.nextInt());
			}
			double[][] dists = new double[N][N];
			for (int n=0; n<N; n++) {
				double minN = 987654321;
				ArrayList<Integer> minNIDs = new ArrayList<>();
				for(int nn=0; nn<N; nn++) {
					if(n==nn) continue;
					dists[n][nn] =  Math.sqrt(Math.abs(stars[n].x-stars[nn].x)*Math.abs(stars[n].x-stars[nn].x) +
											  Math.abs(stars[n].y-stars[nn].y)*Math.abs(stars[n].y-stars[nn].y));
					if(dists[n][nn] <= minN + .0000001) {
						if(Math.abs(dists[n][nn]-minN) <= .0000001) {
							minNIDs.add(nn);
						} else {
							minNIDs.clear();
							minNIDs.add(nn);
						}
						minN = dists[n][nn];
					}
				}
				stars[n].closestStarIDs = (ArrayList<Integer>) minNIDs.clone();
			}
			
			boolean[] visited = new boolean[N];
			visited[0] = true;
			ArrayDeque<Integer> q= new ArrayDeque<>();
			q.add(0);
			DSU dsu = new DSU(N);
			while(true) {
				while(!q.isEmpty()) {
					int id = q.poll();
					boolean notAll = false;
					for(int ids:stars[id].closestStarIDs) {
						if(dsu.root(ids)==dsu.root(id)) continue;
						notAll = true;
					}
					if(!notAll) continue;
					
					for(int ids:stars[id].closestStarIDs) {
						if(dsu.root(ids)!=dsu.root(id)) {
							q.add(ids);
							dsu.union(id, ids);
							visited[ids] = true;
						}
					}
				}
				boolean atLeastOne = false;
				for(int i=0; i<N; i++) {
					if(!visited[i]) {
						atLeastOne = true;
						q.add(i);
						visited[i] = true;
						break;
					}
				}
				if(!atLeastOne) break;
			}
			System.out.println("Universe " + counter++ + " contains " + (dsu.size-1) + " constellations");
		}
		
	}
}
