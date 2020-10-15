import java.util.Arrays;

class Solution {

	class Subsolution implements Runnable {
		int[][] dp;
		int[][] matrixCopy;
		int[][] matrix;

		public Subsolution(int[][] matrix) {
			this.matrix = matrix;
		}

		public void run() {
            System.out.println("here");
			matrixCopy = Arrays.copyOf((matrix), matrix.length);
			dp = new int[matrix.length][matrix[0].length];
			for (int i = 0; i < matrix.length; i++) {
				Arrays.fill(dp[i], 987654321);
			}
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					if (matrix[i][j] == 1) {
						if (dp[i][j] == 987654321) {
							dp(i, j);
						}
					}
				}
			}
            System.out.println("here1");
			for (int i = 0; i < dp.length; i++) {
				for (int j = 0; j < dp[0].length; j++) {
					if (dp[i][j] == 987654321)
						dp[i][j] = 0;
				}
			}
		}

		private int dp(int i, int j) {
			//System.out.println(i+", "+j);
			dp[i][j] = 987654320;
			if (!inBounds(i, j))
				return 987654320;
			if (dp[i][j] != 987654321)
				return dp[i][j];
			if (matrixCopy[i][j] == 0) {
				return dp[i][j] = 0;
			}
			dp[i][j] = Math.min(dp(i + 1, j) + 1, dp[i][j]);
			dp[i][j] = Math.min(dp(i - 1, j) + 1, dp[i][j]);
			dp[i][j] = Math.min(dp(i, j - 1) + 1, dp[i][j]);
			dp[i][j] = Math.min(dp(i, j + 1) + 1, dp[i][j]);
			return dp[i][j];
		}

		private boolean inBounds(int i, int j) {
			if (i >= matrixCopy.length || i < 0 || j < 0 || j >= matrixCopy[0].length)
				return false;
			return true;
		}
	}

	public int[][] updateMatrix(int[][] matrix) {
		Subsolution s = new Subsolution(matrix);
		Thread t = new Thread(null, s, "lol", (1L << 27));
		t.start();
		try {
			t.join(2000);
            return s.dp;
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        return new int[matrix.length][matrix[0].length];
	}
	
	public static void main(String[] args) {
		int[][] mat = {{0,0,0},{0,1,0},{1,1,1}};
		new Solution().updateMatrix(mat);
	}

}