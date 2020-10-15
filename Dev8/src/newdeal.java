import java.util.Scanner;

public class newdeal {
    
	static int[] boxes;
	//boolean hitTarget;
	static int BUFFER2 = 0;
	static Boolean[][] memo;
    public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		//PrintWriter out = new PrintWriter(System.out);
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int K = in.nextInt();
			boxes = new int[K];
			int BUFFER1 = 0;
			BUFFER2 = 0;
			for(int k=0; k<K; k++) {
				boxes[k] = in.nextInt();
				if(boxes[k]>0) BUFFER1 += boxes[k];
				else BUFFER2 -= boxes[k];
			}
			memo = new Boolean[K][BUFFER2+BUFFER1+1];
			int target = in.nextInt();
			boolean ans = dp(0,0, target);
			if(ans) {
				System.out.println("Test case #"+(n+1)+": You can hit the target "+target+" and win $10M!");
			} else {
				System.out.println("Test case #"+(n+1)+": You can not hit the target "+target+", sorry!");
			}
		}
		
		in.close();
		//out.close();
	}

	
	/*
	 * 
2
5 2 3 4 -1 -2 1
6 1 2 3 4 5 6 30
	 */
	//boolean found = false;
	private static boolean dp(int i, int t, int target) {
		//if(found) return 1;
		if(target==t) return true;
		if(i==memo.length) return false;
		
		//if(target<0) return -1;
		
		//System.out.println(target + " " + BUFFER);
		if(memo[i][t+BUFFER2]!=null) {
			return memo[i][t+BUFFER2];
		}
		boolean leave = dp(i+1, t, target);
		if(leave) return memo[i][t+BUFFER2]=true;
		boolean choose = dp(i+1, t+boxes[i], target);
		if(choose) return memo[i][t+BUFFER2]=true;
		
		return memo[i][t+BUFFER2]=false;
	}

}
