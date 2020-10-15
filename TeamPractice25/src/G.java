import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class G implements Runnable{
	public static void main(String[] args) {
        new Thread(null, new G(), "lol", (1L<<30)).start();
    }
	public void run() {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new G().solve(in,out);
		out.close();
			
	}
	class Target {
		int a,b,c;
		public Target(int a, int b, int c) {
			this.a=a;
			this.b=b;
			this.c=c;
		}
	}
	int N;
	Target[] targets;
	long[][][] memo;
	private void solve(JoltyScanner in, PrintWriter out) {
		N = in.nextInt();
		targets = new Target[N];
		memo = new long[2][2][N+1];
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		for(int i=0; i<N; i++) {
			targets[i] = new Target(in.nextInt(), in.nextInt(), in.nextInt());
			//targets[i] = new Target(1,2,3);
		}
		out.print(dp(0, false, false));
	}
	final long oo = (int) 1e9;
	//choice=0 must skip, 1 must choose
	private long dp(int curr, boolean prevChosen, boolean prevPrevChosen) {
		if(curr==N-1) {
			long ans = -oo;
			if(prevChosen) {
				if(prevPrevChosen) {
					ans = Math.max(ans, targets[curr-1].b);
					ans = Math.max(ans, targets[curr-1].c + targets[curr].b);
				} else {
					ans = Math.max(ans, targets[curr-1].a);
					ans = Math.max(ans, targets[curr-1].b + targets[curr].b);
				}
			} else {
				ans = Math.max(ans, targets[curr].a);
				ans = Math.max(ans, 0);
			}
			return ans;
		}
		if(memo[prevChosen?1:0][prevPrevChosen?1:0][curr]!=-1) return memo[prevChosen?1:0][prevPrevChosen?1:0][curr];
		
		long ans2 = -oo;
		long ans3 = -oo;
		if(!prevChosen) {
			ans2 = dp(curr+1, true, false);
			ans3 = dp(curr+1, false, false);
		}
		
		long ans1 = -oo;
		long ans4 = -oo;
		
		if(prevChosen && prevPrevChosen) {
			ans1 = targets[curr-1].c + dp(curr+1, true, true);
			ans4 = targets[curr-1].b + dp(curr+1, false, true);
		}
		if(prevChosen && !prevPrevChosen) {
			ans1 = targets[curr-1].b + dp(curr+1, true, true);
			ans4 = targets[curr-1].a + dp(curr+1, false, true);
		}
		
		return memo[prevChosen?1:0][prevPrevChosen?1:0][curr] = Math.max(ans1, Math.max(ans2, Math.max(ans3, ans4)));
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

