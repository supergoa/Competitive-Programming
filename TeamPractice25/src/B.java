import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class B {
	public static void main(String[] args) {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in,out);
		out.close();
			
	}
	private void solve(JoltyScanner in, PrintWriter out) {
		int N = in.nextInt();
		int K = in.nextInt();
		int A = in.nextInt();
		int B = in.nextInt();
		HashMap<Integer,HashSet<Integer>> newTrains = new HashMap<>();
		//boolean[][] newTrains = new boolean
		for(int k=0; k<K; k++) {
			int x = in.nextInt()-1;
			int y = in.nextInt()-1;
			if(newTrains.get(x)==null) newTrains.put(x, new HashSet<>());
			newTrains.get(x).add(y);
		}
		boolean toEndAPrice = (newTrains.get(0).contains(N-1))?true:false;
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
				if(newTrains.get(node)!=null && !newTrains.get(node).contains(N-1)) {
					ans2=(level[node]+1)*B;
					break;
				}
				for(int i=0; i<N; i++) {
					if(!visited[i] && newTrains.get(node)!=null && !newTrains.get(node).contains(i)) {
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
				if(newTrains.get(node)!=null && newTrains.get(node).contains(N-1)) {
					ans2=(level[node]+1)*A;
					break;
				}
				for(int i=0; i<N; i++) {
					if(!visited[i] && newTrains.get(node).contains(i)) {
						q.add(i);
						level[i] = level[node] + 1;
					}
				}
			}
			out.println(Math.min(ans1, ans2));
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
}
