import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class learnJ {
	public static void main(String[] args) {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new learnJ().solve(in,out);
		out.close();
	
	}
	int N;
	private void solve(JoltyScanner in, PrintWriter out) {
		N = in.nextInt();
		ArrayList<Integer> here = new ArrayList<>();
		for(int n=0; n<N; n++) {
			here.add(in.nextInt());
		}
		System.out.println(dp(0,here, new ArrayList<>(),"", true));
		
		
	}
	final int oo = (int) (1e9+7);
	private int dp(int ans, ArrayList<Integer> here, ArrayList<Integer> there, String path, boolean sideHere) {
		if(there.size()==N && ans==4392) {
			System.out.println(path);
			return 0;
		}
		int ans1 = oo;
		int ans2 = oo;
		if(sideHere) {
			for(int i=0; i<here.size();i++) {
				for(int j=0; j<here.size(); j++) {
					if(i==j)continue;
					int one = here.get(i);
					int two = here.get(j);
					here.remove((Integer)one);
					here.remove((Integer)two);
					there.add(one);
					there.add(two);
					int add = Math.max(one, two);
					ans1 = Math.min(ans1, add+dp(ans+add,here,there, path+"["+one+",+"+two+"],", false));
					there.remove((Integer)one);
					there.remove((Integer)two);
					here.add(two);
					here.add(one);
				}
			}
		}
		else {
			for(int i=0;i<there.size(); i++) {
				int one = there.get(i);
				there.remove((Integer)one);
				here.add(one);
				int add = one;
				ans2 = Math.min(ans2, add+dp(ans+add,here,there, path+"["+one+"],", true));
				here.remove((Integer)one);
				there.add(one);
			}
		}
		return Math.min(ans1, ans2);
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
