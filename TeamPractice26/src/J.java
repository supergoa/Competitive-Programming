import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

public class J {
	public static void main(String[] args) {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new J().solve(in,out);
		out.close();
	}
	private void solve(JoltyScanner in, PrintWriter out) {
		int N = in.nextInt();
		boolean odd = false;
		if(N%2==1) odd = true;
		int[] arr = new int[N];
		int[] cumsum = new int[N];
		
		arr[0] = in.nextInt();
		cumsum[0] = arr[0];
		
		for(int n=1; n<N; n++) {
			arr[n] = in.nextInt();
			cumsum[n] = arr[n] + cumsum[n-1];
		}
		if(N==1) {out.print(arr[0]); return;}
		if(N==2) {out.print(arr[1]); return;}
		int low1 = arr[0];
		int low2 = arr[1]; 
		long ans = 0;
		long ans2 = 999999999;
		for(int i=N-1; i>2;i-=2) {
			ans += arr[i] + low2 + low2 + low1;
			ans2 = Math.min(ans2, ans+cumsum[i-2]-cumsum[0] + (i-3)*low1);
		}
		if(odd) ans+= low1 + low2 + arr[2];
		else ans += low2;
		ans2 = Math.min(ans2, cumsum[N-1]-cumsum[0] + (N-2)*arr[0]);
		//System.out.print(ans2 + " ");
		//System.out.println(ans);
		System.out.println(Math.min(ans, ans2));
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
