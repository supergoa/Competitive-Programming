import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;

public class Ladder {
	public static void main(String[] args) {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Ladder().solve(in,out);
		out.close();
	}
	class Num {
		int num;
		boolean incr;
		public Num(int x, boolean y) {
			num = x;
			incr = y;
		}
	}
	private void solve(JoltyScanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		int[] cumsum = new int[N];
		Num[] nums = new Num[N];
		for(int i=0; i<N; i++) {
			nums[i] = new Num(in.nextInt(),true);
		}
		int start = nums[0].num;
		int ind = 1;
		while(ind<N && start==nums[ind].num) {ind++;}
		if(ind>=N) {for(int i=0; i<M; i++) out.println("Yes"); return;}
		
		boolean incr = false;
		if(start < nums[ind].num) {
			incr = true;
		}
		
		for(int i=1; i<N; i++) {
			if(nums[i].num > nums[i-1].num && !incr) {
				cumsum[i] = cumsum[i-1]+1;
				incr = !incr;
			}
			else if(nums[i].num < nums[i-1].num && incr) {
				cumsum[i] = cumsum[i-1]+1;
				incr = !incr;
			} else {
				cumsum[i] = cumsum[i-1];
			}
			nums[i].incr = incr;
		}
		
		for(int i=0; i<M; i++) {
			int l = in.nextInt()-1;
			int r = in.nextInt()-1;
			
			int start2 = nums[l].num;
			int ind2 = l;
			boolean incr2 = true;
			while(ind2<=r && start2==nums[ind2].num) {ind2++;}
			if(ind2>=r) {out.println("Yes"); continue;}
			if(start2 > nums[ind2].num) {
				incr2 = false;
			}
			//out.println(incr);
			
			if(r-l <2) out.println("Yes");
			else if(cumsum[r]-cumsum[ind2] == 0) out.println("Yes");
			else if(!incr2 && cumsum[r]-cumsum[ind2] > 0) out.println("No");
			else if(!incr2) out.println("Yes");
			else if(cumsum[r]-cumsum[ind2] > 1) {
				out.println("No");
			} else {
				out.println("Yes");
			}
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
