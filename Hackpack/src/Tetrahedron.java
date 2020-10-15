import java.util.Scanner;

public class Tetrahedron {
	final static int mod = (int)1e9+7;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int M = in.nextInt();
		long[][] adj = {{0,1,1,1},
						{1,0,1,1},
						{1,1,0,1},
						{1,1,1,0}};
		long[][] ans = {{1,0,0,0},
						{0,1,0,0},
						{0,0,1,0},
						{0,0,0,1}};
		while(M>0) {
			if(M%2==1) {
				ans = matMult2(ans,adj);
				M--;
			}
			adj = matMult2(adj,adj);
			M/=2;
		}
		System.out.println(ans[3][3]);
		in.close();
	}

	private static long[][] matMult(long[][] ans, long[][] adj) {
		int len = ans[0].length;
		long[][] ret = new long[len][len];
		for(int i=0; i<len; i++) {
			for(int j=0;j<len;j++) {
				for(int k=0; k<len; k++) {
					ret[i][j] = (ret[i][j] + (ans[i][k]*adj[k][j])%mod)%mod;
				}
				//ret[i][j] = ans[i][0]*adj[0][j] + ans[i][1]*adj[1][j] + ans[i][2]*adj[2][j] + ans[i][3]*adj[3][j];
			}
		}
		return ret;
	}
	
	private static long[][] matMult2(long[][] ans, long[][] base) {
		int len1 = ans[0].length;
		int len2 = base[0].length;
		long[][] ret = new long[len1][len2];
		for(int i=0; i<len1; i++) {
			for(int j=0;j<len2;j++) {
				for(int k=0; k<len1; k++) {
					ret[i][j] = (ret[i][j] + (ans[i][k]*base[k][j])%mod)%mod;
				}
				//ret[i][j] = ans[i][0]*adj[0][j] + ans[i][1]*adj[1][j] + ans[i][2]*adj[2][j] + ans[i][3]*adj[3][j];
			}
		}
		return ret;
	}
}
