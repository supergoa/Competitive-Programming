import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;

//︱︱     ┏┓︱︱ ︱︱︱︱ ︱┏┓︱ ︱︱︱︱     ︱┏┓︱ ┏┓︱︱ ︱︱ ︱︱︱︱     ┏━━┓ ︱︱︱ ︱︱︱︱ ┏┓︱︱ ┏┓︱ ︱︱︱︱ ︱︱︱︱
//︱︱     ┃┃︱︱ ︱︱︱︱ ┏┛┗┓ ︱︱︱︱     ┏┛┗┓ ┃┃︱︱ ︱︱ ︱︱︱︱     ┃┏┓┃ ︱︱︱ ︱︱︱︱ ┃┃︱︱ ┃┃︱ ︱︱︱︱ ︱︱︱︱
//┏┓     ┃┗━┓ ┏━━┓ ┗┓┏┛ ┏━━┓     ┗┓┏┛ ┃┗━┓ ┏┓ ┏━━┓     ┃┗┛┃ ┏━┓ ┏━━┓ ┃┗━┓ ┃┃︱ ┏━━┓ ┏┓┏┓
//┣┫     ┃┏┓┃ ┃┏┓┃ ︱┃┃︱ ┃┃━┫     ︱┃┃︱ ┃┏┓┃ ┣┫ ┃━━┫     ┃┏━┛ ┃┏┛ ┃┏┓┃ ┃┏┓┃ ┃┃︱ ┃┃━┫ ┃┗┛┃
//┃┃     ┃┃┃┃ ┃┏┓┃ ︱┃┗┓ ┃┃━┫     ︱┃┗┓ ┃┃┃┃ ┃┃ ┣━━┃     ┃┃︱︱ ┃┃︱ ┃┗┛┃ ┃┗┛┃ ┃┗┓ ┃┃━┫ ┃┃┃┃
//┗┛     ┗┛┗┛ ┗┛┗┛ ︱┗━┛ ┗━━┛     ︱┗━┛ ┗┛┗┛ ┗┛ ┗━━┛     ┗┛︱︱ ┗┛︱ ┗━━┛ ┗━━┛ ┗━┛ ┗━━┛ ┗┻┻┛
public class Array {
	public static void main(String[] args) {
		JoltyScanner in = new JoltyScanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Array().solve(in,out);
		out.close();
	}

	class Pair {
		int first;
		int last;
		public Pair(int first, int last) {
			this.first = first;
			this.last = last;
		}
	}
	private void solve(JoltyScanner in, PrintWriter out) {
		int N = in.nextInt();
		int K = in.nextInt();
		
		HashMap<Integer, Integer> inds = new HashMap<>(); //do a pair of first and last instead of treeset if too slow
		int[] arr = new int[N];
		for(int n=0; n<N; n++) {
			int x = in.nextInt();
			arr[n]=x;
			inds.put(x, inds.getOrDefault(x, 0)+1);
		}

		int l = 0;
		int r = N-1;
		if(inds.size()<K) {
			out.println("-1 -1");
			return;
		}
		
		while(inds.size()>=K) {
			//System.out.println("r "+r);
			inds.put(arr[r], inds.get(arr[r])-1);
			if(inds.get(arr[r])==0) {
				if(inds.size()==K) {
					break;
				} else {
					inds.remove(arr[r]);
				}
			}
			r--;
		}
	//	r++;
		//inds.put(arr[r],1);

		
		while(inds.size()==K && l!=r) {
			//System.out.println(l);
			inds.put(arr[l], inds.get(arr[l])-1);
			if(inds.get(arr[l])==0) {
				if(inds.size()==K) {
					break;
				} else {
					inds.remove(arr[l]);
				}
			}
			l++;
		}
		//inds.put(arr[l],1);
		
		out.println((l+1) + " " + (r+1));

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
