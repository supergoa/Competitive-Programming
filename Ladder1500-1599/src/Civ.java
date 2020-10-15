import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Civ {
	public static void main(String[] args) throws Exception {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Civ().solve(in,out);
		out.close();
	}

	class Region {
		int longestPath, farthestChild, treeCenter;
		public Region(int a, int b, int c) {
			longestPath = a;
			farthestChild = b;
			treeCenter = c;
		}
	}
	private void solve(JoltyScanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		int Q = in.nextInt();
		
		DSU dsu = new DSU(N);
		IntList[] adj = new IntList[N];
		for(int i=0; i<N; i++) adj[i] = new IntList();
		for(int m=0; m<M; m++) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			adj[a].add(b);
			adj[b].add(a);
			dsu.union(a, b);
		}
		Region[] trees = new Region[N];
		for(int t=0; t<N; t++) trees[t] = new Region(0,0,0);
		
		boolean[] used = new boolean[N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<adj[i].size(); j++) {
				if(used[dsu.root(adj[i].get(j))]) continue;
				used[dsu.root(adj[i].get(j))] = true;
				
				ArrayDeque<Integer> q = new ArrayDeque<>();
				q.add(adj[i].get(j));
				int lastNode = adj[i].get(j);
				
				boolean[] visited = new boolean[N];
				visited[adj[i].get(j)] = true;
				
				while(!q.isEmpty()) {
					int p = q.poll();
					
					lastNode = p;
					for(int w=0; w<adj[p].size(); w++) {
						int neighbor = adj[p].get(w);
						if(!visited[neighbor]) {
							q.add(neighbor);
							visited[neighbor] = true;
						}
					}
				}
				
				q.add(lastNode);
				Arrays.fill(visited, false);
				visited[lastNode] = true;
				
				int[] level = new int[N];
				level[lastNode] = 0;
				int maxLevel = 0;
				int maxLevelInd = lastNode;
				
				int[] parent = new int[N];
				while(!q.isEmpty()) {
					int p = q.poll();
					
					for(int w=0; w<adj[p].size(); w++) {
						int neighbor = adj[p].get(w);
						if(!visited[neighbor]) {
							q.add(neighbor);
							level[neighbor] = level[p] + 1;
							parent[neighbor] = p;
							if(maxLevel < level[neighbor]) {
								maxLevel = level[neighbor];
								maxLevelInd = neighbor;
							}
							visited[neighbor] = true;
						}
					}
				}
				
				int treeCenter = parent[maxLevelInd];
				for(int k=0; k<(maxLevel-1)/2; k++) {
					treeCenter = parent[treeCenter];
				}
				int maxChildDepth = (maxLevel%2==0)? maxLevel/2: maxLevel/2+1;
				
				trees[dsu.root(adj[i].get(j))].longestPath = maxLevel;
				trees[dsu.root(adj[i].get(j))].farthestChild = maxChildDepth;
			}
		}
		for(int q=0; q<Q; q++) {
			int choice = in.nextInt();
			if(choice==1) {
				int x = in.nextInt()-1;
				out.println(trees[dsu.root(x)].longestPath);
			} else {
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				
				int ida = dsu.root(a);
				int idb = dsu.root(b);
				if(ida == idb) continue;
				
				int newLongestPath = -1;
				int newFarthestChild = -1;
				
				if(trees[ida].longestPath == trees[idb].longestPath) {
					newLongestPath = trees[ida].farthestChild + trees[idb].farthestChild + 1;
				} else {
					newLongestPath = Math.max(trees[ida].longestPath, trees[idb].longestPath);
					newLongestPath = Math.max(newLongestPath, trees[ida].farthestChild + trees[idb].farthestChild + 1);
				}
				newFarthestChild = (newLongestPath%2==0)? newLongestPath/2: newLongestPath/2+1;
				
				dsu.union(a, b);
				trees[dsu.root(a)].farthestChild = newFarthestChild;
				trees[dsu.root(a)].longestPath = newLongestPath;
			}
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
	static class JoltyScanner {
        public int BS = 1 << 16;
        public char NC = (char) 0;
        public byte[] buf = new byte[BS];
        public int bId = 0;
        public int size = 0;
        public char c = NC;
        public double num = 1;
        public BufferedInputStream in;

        public JoltyScanner(InputStream is) {
            in = new BufferedInputStream(is, BS);
        }

        public JoltyScanner(String s) throws FileNotFoundException {
            in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
        }

        public char nextChar() {
            while (bId == size) {
                try {
                    size = in.read(buf);
                } catch (Exception e) {
                    return NC;
                }
                if (size == -1) return NC;
                bId = 0;
            }
            return (char) buf[bId++];
        }

        public int nextInt() {
            return (int) nextLong();
        }

        public long nextLong() {
            num = 1;
            boolean neg = false;
            if (c == NC) c = nextChar();
            for (; (c < '0' || c > '9'); c = nextChar()) {
                if (c == '-') neg = true;
            }
            long res = 0;
            for (; c >= '0' && c <= '9'; c = nextChar()) {
                res = (res << 3) + (res << 1) + c - '0';
                num *= 10;
            }
            return neg ? -res : res;
        }

    }
	static class IntList {
        static int[] EMPTY = {};
        int n = 0;
        int[] a = EMPTY;

        public void add(int v) {
            if (n >= a.length)
                a = Arrays.copyOf(a, (n << 2) + 8);
            a[n++] = v;
        }

        public int get(int idx) {
            return a[idx];
        }

        public int size() {
            return n;
        }

    }

}
