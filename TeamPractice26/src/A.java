import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;


public class A {
	public static void main(String[] args) {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new A().solve(in,out);
		out.close();
	}
	private void solve(JoltyScanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		int P = in.nextInt();
		int ans = 0;
		//HashSet<String> done = new HashSet<>();
		boolean[][][][] done = new boolean[N+1][M+1][N+1][M+1];
		for(int n=1; n<=N; n++) {
			for(int m=1; m<=M; m++) {
				for(int i=1; i<=N-n+1; i++) {
					for(int j=1; j<=M-m+1; j++) {
						if(done[n][m][i][j]) continue;
						done[n][m][i][j] = true;
						/*int num1 = N/n;
						int num2 = M/m;
						int num3 = 0;
						int num4 = 0;
						if(n==1 && m==1) {}
						if(n==N && m==M) {}
						if(n==m) {}
						else {
							//System.out.println("he");
							num3 = N/m;
							num4 = M/n;
						}*/
						
						//System.out.println(n +" " + m + " " + " " + num1 + " " + num2+ " " + num3+ " " + num4);
						if(2*i + 2*j >= P) {
							ans++;
						}
					}
				}
			}
		}
		
		System.out.println(ans);
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
