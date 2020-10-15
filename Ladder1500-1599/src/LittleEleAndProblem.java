import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;

public class LittleEleAndProblem {
	public static void main(String[] args) {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new LittleEleAndProblem().solve(in,out);
		out.close();
	}

	private void solve(JoltyScanner in, PrintWriter out) {
		int N = in.nextInt();
		int[] nums = new int[N];
		for(int i=0; i<N; i++) {
			nums[i] = in.nextInt();
		}
		int[] clone = nums.clone();
		Arrays.sort(clone);
			
		HashSet<Integer> inds = new HashSet<Integer>();
	
		for(int i=0; i<N; i++) {
			if(nums[i] != clone[i]) {
				inds.add(i);
			}
		}
		if(inds.size()==0) {out.print("YES");return;}
		if(inds.size()!=2) {out.print("NO");return;}
		
		int ind1 = (int) inds.toArray()[0];
		int ind2 = (int) inds.toArray()[1];
		
		nums[ind1] = clone[ind1];
		nums[ind2] = clone[ind2];
		
		boolean good = true;
		for(int k:inds) {
			int lower = k-10;
			int upper = k+10;
			while(lower<0) lower++;
			while(upper>=N) upper--;
			for(int l = lower+1; l<=upper; l++) {
				if(nums[l-1] > nums[l]) {
					good = false;
				}
			}
			if(!good) break;
		}

			
		if(good) out.print("YES");
		else out.print("NO");
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

/*
 * import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;

public class LittleEleAndProblem {
	public static void main(String[] args) {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new LittleEleAndProblem().solve(in,out);
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
	/*
	 * 7
		1 2 3 4 2 4 6
	 
	private void solve(JoltyScanner in, PrintWriter out) {
		int N = in.nextInt();
		if(N<=3) {out.print("YES");return;}
		int[] cumsum = new int[N];
		int[] nums = new int[N];
		for(int i=0; i<N; i++) {
			nums[i] = in.nextInt();
		}
			
		boolean incr = true;
		HashSet<Integer> inds = new HashSet<Integer>();
		for(int i=1; i<N; i++) {
			if(nums[i] > nums[i-1] && !incr) {
				cumsum[i] = cumsum[i-1];
				incr = !incr;
			}
			else if(nums[i] < nums[i-1] && incr) {
				cumsum[i] = cumsum[i-1] + 1;
				incr = !incr;
				
				int indToAdd = 1;
				while(i-indToAdd >= 0 && nums[i-indToAdd] == nums[i-1]) {
					indToAdd++;
				} indToAdd--;
				
				inds.add(i);
				inds.add(i-indToAdd);
			} else {
				cumsum[i] = cumsum[i-1];
			}
		}
		if(inds.size()==0) {out.print("YES");return;}
		if(inds.size()>30) {out.print("NO");return;}
		//System.out.println(Arrays.toString(cumsum));
		
		for(int i:inds) {
			//System.out.println("inds i " + i);
			for(int j:inds) {
				int tempi = nums[i];
				int tempj = nums[j];
				nums[i] = tempj;
				nums[j] = tempi;
				boolean good = true;
				for(int k:inds) {
					//System.out.println("inds j" + j);
					int lower = k-10;
					int upper = k+10;
					while(lower<0) lower++;
					while(upper>=N) upper--;
					
					//System.out.println(lower + " " + upper);
					
					for(int l = lower+1; l<=upper; l++) {
						if(nums[l] < nums[l-1]) {
							good = false;
						}
						//System.out.print(" " + nums[l]);
					}
					//System.out.println();
					if(!good) break;
				}
				
				//System.out.println(Arrays.toString(nums));
				if(good) {out.print("YES");return;}
				
				nums[i] = tempi;
				nums[j] = tempj;
			}
		}
		out.print("NO");
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
*/
