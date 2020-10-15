
public class Floyds {
	private static final int INF = 987654321;
	static int[][] d; // adj list of lengths between edges
	static int n; // size
	public static void main(String[] args) {
		for (int ii = 0; ii < n; ++ii) {
		    for (int jj = 0; jj < n; ++jj) {
		        for (int kk = 0; kk < n; ++kk) {
		            if (d[jj][ii] < INF && d[ii][kk] < INF) //maybe remove this if statement
		                d[jj][kk] = Math.min(d[jj][kk], d[jj][ii] + d[ii][kk]); 
		        }
		    }
		}
	}
}
