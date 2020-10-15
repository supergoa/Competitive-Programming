import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class SubMatrixSum {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new SubMatrixSum().solve(in,out);
		in.close();
		out.close();
	}

	class Pair {
		int r,c;
		public Pair(int a, int b) {
			r = a;
			c = b;
		}
	}
	//long shift = (long) (1e9 + 1);
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int R = in.nextInt();
			int C = in.nextInt();
			long S = in.nextInt();
			long[][] mat = new long[R][C];
			long[][] cumsum = new long[R][C];
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					mat[r][c] = in.nextLong();
				}
			}
			TreeMap<Long, Pair> closestSum = new TreeMap<>();
			for(int r=0; r<R; r++) {
				cumsum[r][0] = mat[r][0];
				if(r>0) cumsum[r][0] += cumsum[r-1][0];
				closestSum.put(cumsum[r][0], new Pair(r,0));
			}
			for(int c=0; c<C; c++) {
				cumsum[0][c] = mat[0][c];
				if(c>0) cumsum[0][c] += cumsum[0][c-1];
				closestSum.put(cumsum[0][c], new Pair(0,c));
			}
			//System.out.println();
			
			for(int r=1; r<R; r++) {
				for(int c=1; c<C; c++) {
					cumsum[r][c] = mat[r][c];
					cumsum[r][c] += cumsum[r-1][c];
					cumsum[r][c] += cumsum[r][c-1];
					cumsum[r][c] -= cumsum[r-1][c-1];
					closestSum.put(cumsum[r][c], new Pair(r,c));
				}
			}
			long min = Long.MAX_VALUE;
			for(int r=0; r<R; r++) {
				for(int c=0; c<C; c++) {
					//out.print(cumsum[r][c]+ " ");
					//if(cumsum[r][c] >= S) {
					long canSubtract = cumsum[r][c] - S;
					Pair p = null;
					//System.out.println(r + " " + c + " " + canSubtract + " " + cumsum[r][c]);
					if(canSubtract!=0) {
						
						Entry<Long, Pair> e = closestSum.floorEntry(canSubtract);
						if(e!=null) p = e.getValue();
					}
					
					if(p != null && ((r>=p.r && c>p.c) || (r>p.r && c>=p.c))) min = Math.min(min, (r-p.r+1)*(c-p.c+1));
					if(cumsum[r][c] >= S) min = Math.min(min, (r+1)*(c+1));
					if(mat[r][c] >= S) min = 1;
					//}
				}
				//out.println();
			}
			if(min == Long.MAX_VALUE) {
				out.println(-1);
			} else {
				out.println(min);
			}
		}
		
	}
}
