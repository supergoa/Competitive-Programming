import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;

public class aa {
	public static void main(String[] args) throws FileNotFoundException {
		JS in = new JS();
		PrintWriter out = new PrintWriter(System.out);
		new aa().solve(in,out);
		out.close();
	}

	
	private void solve(JS in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		long[][] d = new long[N][N];
		final long INF = (long) 1e15;
		for(int i=0; i<N; i++) Arrays.fill(d[i], INF);
		for(int i=0; i<N; i++) d[i][i] = 0;
		for(int m=0; m<M; m++) {
			int a = in.nextInt()-1;
			int b = in.nextInt()-1;
			int c = in.nextInt();
			d[a][b] = -c;
		}
		for (int ii = 0; ii < N; ++ii) {
		    for (int jj = 0; jj < N; ++jj) {
		        for (int kk = 0; kk < N; ++kk) {
	        	  if (d[jj][ii] < INF && d[ii][kk] < INF) //maybe remove this if statement
		                d[jj][kk] = Math.min(d[jj][kk], d[jj][ii] + d[ii][kk]); 
		        }
		    }
		}
		for(int i=0; i<N; i++) {
			if(d[i][i] < 0) d[i][i]= -INF;
		}
		for (int ii = 0; ii < N; ++ii) {
		    for (int jj = 0; jj < N; ++jj) {
		        for (int kk = 0; kk < N; ++kk) {
		        	if (d[jj][ii] < INF && d[ii][kk] < INF)
		        		d[jj][kk] = Math.min(d[jj][kk], d[jj][ii] + d[ii][kk]); 
		        }
		    }
		}
		
		ArrayList<Integer> ans = new ArrayList<>();
		for(int i=0; i<N; i++) {
			if(d[i][i] < 0) ans.add(i);
		}
		
		out.println(ans.size());
		for(int i=0; i<ans.size(); i++) out.print(ans.get(i)+1 + " ");
	}
	
	
	static class JS {
		public int BS = 1<<16;
		public char NC = (char)0;
		byte[] buf = new byte[BS];
		int bId = 0, size = 0;
		char c = NC;
		double num = 1;
		BufferedInputStream in;

		public JS() {
			in = new BufferedInputStream(System.in, BS);
		}

		public JS(String s) throws FileNotFoundException {
			in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
		}

		public char nextChar(){
			while(bId==size) {
				try {
					size = in.read(buf);
				}catch(Exception e) {
					return NC;
				}
				if(size==-1)return NC;
				bId=0;
			}
			return (char)buf[bId++];
		}

		public int nextInt() {
			return (int)nextLong();
		}

		public long nextLong() {
			num=1;
			boolean neg = false;
			if(c==NC)c=nextChar();
			for(;(c<'0' || c>'9'); c = nextChar()) {
				if(c=='-')neg=true;
			}
			long res = 0;
			for(; c>='0' && c <='9'; c=nextChar()) {
				res = (res<<3)+(res<<1)+c-'0';
				num*=10;
			}
			return neg?-res:res;
		}

		public double nextDouble() {
			double cur = nextLong();
			return c!='.' ? cur:cur+nextLong()/num;
		}

		public String next() {
			StringBuilder res = new StringBuilder();
			while(c<=32)c=nextChar();
			while(c>32) {
				res.append(c);
				c=nextChar();
			}
			return res.toString();
		}

		public String nextLine() {
			StringBuilder res = new StringBuilder();
			while(c<=32)c=nextChar();
			while(c!='\n') {
				res.append(c);
				c=nextChar();
			}
			return res.toString();
		}

		public boolean hasNext() {
			if(c>32)return true;
			while(true) {
				c=nextChar();
				if(c==NC)return false;
				else if(c>32)return true;
			}
		}
	}
}
