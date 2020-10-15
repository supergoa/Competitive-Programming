import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

public class I3 {
	public static void main(String[] args) {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new I3().solve(in,out);
		out.close();
	}
	
	class Person {
		int t,w;
		public Person(int t, int w) {
			this.t= t;
			this.w = w;
		}
	}
	int W, N;
	Person[] people;
	Person[] groups;
	private void solve(JoltyScanner in, PrintWriter out) {
		W = in.nextInt();
		N = in.nextInt();
		people = new Person[N];
		groups = new Person[N];
		for(int n=0; n<N; n++) {
			people[n] = new Person(in.nextInt(), in.nextInt());
		}
		
		System.out.println(dp(0, 0));
	}
	
	final int oo = (int) (1e9 + 7);
	private int dp(int curr, int numGroups) {
		if(curr==N) {
			int time = 0;
			for(int i=0; i<numGroups; i++) {
				time += groups[i].t;
			}
			return time;
		}
		int ans1 = oo;
		for(int i=0; i<numGroups; i++) {
			if(groups[i].w + people[curr].w <= W) {
				
				int beforeTime = groups[i].t;
				int beforeWeight = groups[i].w;
				
				groups[i].t = Math.max(people[curr].t, beforeTime);
				groups[i].w += people[curr].w;
				ans1 = Math.min(ans1, dp(curr+1, numGroups));
				groups[i].t = beforeTime;
				groups[i].w = beforeWeight;
			}
		}
		int ans2 = oo;
		//new group
		if(numGroups<N) {
			groups[numGroups] = new Person(people[curr].t, people[curr].w);
			ans2 = dp(curr+1, numGroups+1);
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
