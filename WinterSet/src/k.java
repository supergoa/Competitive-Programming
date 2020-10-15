import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class k {
	public static void main(String[] args) {
		JS in = new JS();
		PrintWriter out = new PrintWriter(System.out);
		new k ().solve(in,out);
		out.close();
	}

	class Point implements Comparable<Point>{
		int id, x, y;
		boolean l, r;
		public Point(int c, int a, int b) {
			x=a;
			y=b;
			id=c;
		}
		@Override
		public int compareTo(Point o) {
			return x-o.x;
		}
		
	}
	private void solve(JS in, PrintWriter out) {
		int N = in.nextInt();
		in.nextInt();
		
		int maxY = Integer.MIN_VALUE;
		int minY = Integer.MAX_VALUE;
		Point[] points = new Point[N];
		for(int i=0; i<N; i++) {
			int x = in.nextInt();
			int y = in.nextInt();
			points[i] = new Point(i, x, y);
		}
		Arrays.sort(points);
		
		// left to right
		int prevX = Integer.MIN_VALUE;
		HashSet<Integer> ys = new HashSet<>();
		for(Point p:points) {
			if(p.x == prevX) {
				ys.add(p.y);
				if(maxY > p.y && minY< p.y) p.l = true;
				continue;
			}

			for(Integer a:ys) {
				maxY = Math.max(maxY, a);
				minY = Math.min(minY, a);
			}
			ys.clear();
			
			if(maxY > p.y && minY< p.y) {p.l = true;}
			ys.add(p.y);
			prevX = p.x;
		}
		
		// right to left
		prevX = Integer.MAX_VALUE;
		maxY = Integer.MIN_VALUE;
		minY = Integer.MAX_VALUE;
		ys = new HashSet<>();
		Collections.reverse(Arrays.asList(points));
		for(Point p:points) {
			if(p.x == prevX) {
				ys.add(p.y);
				if(maxY > p.y && minY< p.y) p.r = true;
				continue;
			}

			for(Integer a:ys) {
				maxY = Math.max(maxY, a);
				minY = Math.min(minY, a);
			}
			ys.clear();
			
			if(maxY > p.y && minY< p.y) {p.r = true;}
			ys.add(p.y);
			prevX = p.x;
		}
		
		int count = 0;
		for(Point p : points) {
			if(p.l && p.r) count++;
		}
		System.out.println(count);
		
		
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
