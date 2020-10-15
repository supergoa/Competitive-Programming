import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class q {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new q().solve(in,out);
		in.close();
		out.close();
	}
	

	private void solve(Scanner in, PrintWriter out) {
		/*{
			long[][] fib = new long[2][1];
			fib[0][0] = 1;
			fib[1][0] = 0;
			long[][] base = {{1,1},{1,0}};
			long[][] ans = {{1,0},{0,1}};
			long newPow = 21;
			while(newPow>0) {
				if(newPow%2==1) {
					ans = matMult(ans,base);
					newPow--;
				}
				base = matMult(base,base);
				newPow/=2;
			}
			fib = matMult(ans,fib);
			System.out.println(fib[0][0]);
		}*/
		
		
		int N = in.nextInt();
		int K = in.nextInt();
		Node st = new Node(0, N-1,  new long[N]);
		//st.print();
		for(int k=0; k<K; k++) {
			char c = in.next().toCharArray()[0];
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			if(c=='D') {
				st.add(a,b,1);
			} else {
				System.out.println(st.query(a, b));
			}
			st.print();
			System.out.println("~~~~~~");
		}
		
	}
	

	final int mod = (int) (1e9+7);
	private long[][] matMult(long[][] ans, long[][] base) {
		int len1 = ans[0].length;
		int len2 = base[0].length;
		long[][] ret = new long[len1][len2];
		for(int i=0; i<len1; i++) {
			for(int j=0;j<len2;j++) {
				for(int k=0; k<len1; k++) {
					ret[i][j] = (ret[i][j] + (ans[i][k]*base[k][j])%mod)%mod;
				}
			}
		}
		return ret;
	}
	
	final long oo = Long.MAX_VALUE;
	
	int uniId=0;
	class Node {
		int l, r;
		Node left, right;
		
		long pow, lazy;
		int id;
		long[][] fib = new long[2][1];
		public Node(int ll, int rr, long[] a) {
			l=ll;
			r=rr;
			fib[0][0] = 0;
			fib[1][0] = 1;
			id = uniId++;
			if(l==r) {
				pow = a[l];
				return;
			}
			
			int m = (l+r)/2;
			left = new Node(l,m,a);
			right = new Node(m+1, r, a);
			fib = matAdd(left.fib, right.fib); 
		}
		
		long[][] get() {
			long[][] base = {{1,1},{1,0}};
			long[][] ans = {{1,0},{0,1}};
			long newPow = pow+lazy;
			while(newPow>0) {
				if(newPow%2==1) {
					ans = matMult(ans,base);
					newPow--;
				}
				base = matMult(base,base);
				newPow/=2;
			}
			fib = matMult(ans,fib);
			prop();
			lazy=0;
			pow=0;
			return fib.clone();
			//return fib[0][0];
			
		}
		
		void prop() {
			if(lazy==0)return;
			pow+=lazy;
			if(left!=null) left.lazy+=lazy;
			if(right!=null) right.lazy+=lazy;
			lazy=0;
		}
		long query(int ll, int rr) {
			if(rr<l || ll>r) return 0;
			if(ll<=l && r<= rr) {
				//System.out.println(this.id + " ret " + ll + " " + rr + "get:" + get()[0][0]);
				return get()[0][0];
			}
			prop();
			//System.out.println(this.id + " " + ll + " " + rr);
			long ans = 0;
			ans = (left.query(ll, rr) + right.query(ll, rr))%mod;
			//System.out.println(this.id + "ans:"+ans);
			return ans;
		}
		void add(int ll, int rr, long d) {
			if(rr<l || ll>r) return;
			if(ll<=l && r<= rr) {
				lazy+=d;
				return;
			}
			prop();
			left.add(ll, rr, d);
			right.add(ll, rr, d);
			//fib = matAdd(fib, matAdd(left.get(), right.get()));
			fib = matAdd(left.get(), right.get());
		}
		private long[][] matAdd(long[][] a, long[][] b) {
			int len1 = a.length;
			int len2 = a[0].length;
			long[][] ret = new long[len1][len2];
			for(int i=0; i<len1; i++) {
				for(int j=0;j<len2;j++) {
						ret[i][j] = (a[i][j] + b[i][j])%mod;
				}
			}
			return ret.clone();
		}

		void print() {
			System.out.println(id + " " +fib[0][0] + " " + fib[1][0]  + " lazy:" + lazy + " get:" + get()[0][0]);
			if(left != null) {
				left.print();
			}
			if(right !=null) {
				right.print();
			}
		}
	}
	
}
