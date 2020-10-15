import java.io.PrintWriter;
import java.util.Scanner;

public class artwork {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new artwork().solve(in, out);
		in.close();
		out.close();
	}

	int N, M;
	int[][] grid;
	class QQ {
		int x1, x2, y1, y2;
		public QQ(int a, int b, int c, int d) {
			x1 = a;
			x2 = c;
			y1 = b;
			y2 = d;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		M = in.nextInt();
		N = in.nextInt();
		int Q = in.nextInt();
		
		grid = new int[N][M];
		QQ[] qq = new QQ[Q];
		for(int q=0; q<Q; q++) {
			int x1 = in.nextInt()-1;
			int y1 = in.nextInt()-1;
			int x2 = in.nextInt()-1;
			int y2 = in.nextInt()-1;
			//System.out.println("aye" + x1 + " " + y1 + " " + x2 + " " + y2);
			qq[q] = new QQ(x1, y1, x2, y2);
			for(int i=x1; i<=x2; i++) {
				for(int j=y1; j<=y2; j++) {
					//System.out.println(i + " " + j);
					grid[j][i]++;
				}
			}
		}
		for(int n=0; n<N; n++) {
			for(int m=0; m<M; m++) {
				System.out.print(grid[n][m] + " ");
			}
			System.out.println();
		}
		/*for(int n=0; n<N; n++) {
			for(int m=0; m<M; m++) {
				System.out.print(getID(n,m)+" ");
			}
			System.out.println();
		}*/
		//System.out.println("IDS");
		DSU dsu = new DSU(N*M);
		for(int n=0; n<N; n++) {
			for(int m=0; m<M; m++) {
				if(grid[n][m]==0) {
					int id = getID(n,m);
					//System.out.println(id);
					dsu.used(id);
					fill(dsu, n, m, id);
				}
			}
		}
		StringBuilder toPrint = new StringBuilder("");
		toPrint.append(dsu.size+"\n");
		for(int q=Q-1; q>0; q--) {
			int x1 = qq[q].x1;
			int y1 = qq[q].y1;
			int x2 = qq[q].x2;
			int y2 = qq[q].y2;
			//System.out.println("querey new");
			for(int i=x1; i<=x2; i++) {
				for(int j=y1; j<=y2; j++) {
					grid[j][i]--;
					int id = getID(j, i);
					//System.out.print(id + " ");
					if(grid[j][i]==0) {
						dsu.used(id);
						fill(dsu, j, i, id);
					}
				}
			}
			//System.out.println();
			toPrint.append(dsu.size+"\n");
		}
		out.println(toPrint.reverse().toString().trim());
		for(int n=0; n<N; n++) {
			for(int m=0; m<M; m++) {
				System.out.print(grid[n][m] + " ");
			}
			System.out.println();
		}
	}

	private int getID(int n, int m) {
		return M*n+m;
	}

	private void fill(DSU dsu, int n, int m, int id) {
		for(int i=-1; i<2; i++) {
			for(int j=-1; j<2; j++) {
				if(Math.abs(i+j)==1) {
					int nn = n+i;
					int mm = m+j;
					if(nn<N && nn>=0 && mm<M && mm>=0) {
						int newID = getID(nn, mm);
						//System.out.println("newID " + newID);
						if(grid[nn][mm]==0) {
							dsu.used(newID);
							dsu.union(newID, id);
						}
					}
				}
			}
		}
		
	}

	public class DSU {
		int[] id;
		int size;
		DSU(int x) { //x is size
			size = 0;
			id = new int[x]; // disj relation
			for (int i = 0; i < x; ++i) {
				id[i] = -1;
			}
		}
		
		int root(int a) { //get root of a. "id[a] = get(id[a])" makes retrieval O(1) on subseqent calls
			return id[a] == a ? a : (id[a] = root(id[a]));
		}
		
		void used(int a) {
			if(id[a]!=-1) return;
			id[a] = a;
			size++;
		}
		
		void union(int a, int b) {
			if(root(a) == root(b)) return;
			id[root(a)] = id[root(b)];
			--size;
		}
	}

}
