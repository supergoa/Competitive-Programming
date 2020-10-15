import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

public class C {
	public static void main(String[] args) {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in,out);
		out.close();
	}
	int[][] glade;
	boolean[][] visited;
	int N;
	private void solve(JoltyScanner in, PrintWriter out) {
		N = in.nextInt();
		glade= new int[2][N];
		visited = new boolean[N][2];
		for(int i=0;i<2; i++) {
			for(int j=0; j<N; j++) {
				glade[i][j] = in.nextInt();
			}
		}
		int ans1 = dp(0, true, true, 0, 0);
		int ans2 = dp(0, false, true, 0, 0);
		out.print(Math.max(ans1, ans2));
	}
	
	//time is also numVisited
	private int dp(int time, boolean snake, boolean top, int x, int y) {
		if(visited[x][y]) return 0;
		if(time==2*N) {
			return time*glade[y][x];
		}
		int ans1 = 0;
		int ans2 = 0;
		if(snake) {
			if(top && visited[x][1]) {
				visited[x][y] = true;
				ans1 = time*glade[y][x] + dp(time+1, true, true, x+1, y);
				ans1 = Math.max(ans1, time*glade[y][x] + dp(time+1, false, true, x+1, y));
				visited[x][y] = false;
			} else if(top) {
				visited[x][y] = true;
				ans1 = time*glade[y][x] + dp(time+1, true, false, x, y+1);
				ans1 = Math.max(ans1, time*glade[y][x] + dp(time+1, false, false, x, y+1));
				visited[x][y] = false;
			} else if(visited[x][0]){
				visited[x][y] = true;
				ans1 = time*glade[y][x] + dp(time+1, true, false, x+1, y);
				ans1 = Math.max(ans1, time*glade[y][x] + dp(time+1, false, false, x+1, y));
				visited[x][y] = false;
			} else {
				visited[x][y] = true;
				ans1 = time*glade[y][x] + dp(time+1, true, true, x, y-1);
				ans1 = Math.max(ans1, time*glade[y][x] + dp(time+1, false, true, x, y-1));
				visited[x][y] = false;
			}
		} else {
			if(x==N-1) {
				if(visited[N-1][1] || visited[N-1][0]) {
					visited[x][y] = true;
					ans2 = time*glade[y][x] + dp(time+1, false, top, x-1, y);
					visited[x][y] = false;
				} else {
					if(y==0) {
						visited[x][y] = true;
						ans2 = time*glade[y][x] + dp(time+1, false, top, x, y+1);
						visited[x][y] = false;
					} else {
						visited[x][y] = true;
						ans2 = time*glade[y][x] + dp(time+1, false, top, x, y-1);
						visited[x][y] = false;
					}
				}
			} else {
				visited[x][y] = true;
				ans2 = time*glade[y][x] + dp(time+1, false, top, x+1, y);
				visited[x][y] = false;
			}
		}
		return Math.max(ans1, ans2);
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
