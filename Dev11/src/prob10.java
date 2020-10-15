import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class prob10 implements Runnable {
	public void run() {
		Scanner in = new Scanner(System.in);
		new prob8().solve(in);
		
	}
	public static void main(String[] args) {
        new Thread(null, new prob8(), "lol", (1L<<30)).start();
    }
	class MyArr {
		int[] arr;
		public MyArr(int[] a) {
			arr = a;
		}
		@Override
		public boolean equals(Object o) {
			//if(this == o) return true;
			if(o==null) return false;
			if(Arrays.equals(arr, ((MyArr)o).arr)) return true;
			else return false;
		}
		
		@Override
		public int hashCode() {
			int result = 0;
	        result = 9 * result + arr[0];
	        result = 9 * result + arr[1];
	        result = 9 * result + arr[2];
	        result = 9 * result + arr[3];
	        result = 9 * result + arr[4];
	        result = 9 * result + arr[5];
	        result = 9 * result + arr[6];
	        result = 9 * result + arr[7];
	        result = 9 * result + arr[8];
	        return result;
			//return Objects.hash(arr);
		}
		
	}
	HashMap<MyArr, Integer> memo;
	private void solve(Scanner in) {
		
		int T = in.nextInt();
		memo = new HashMap<>();
		
		check(0, new int[9], new boolean[9]);
		for(int t=0; t<T; t++) {
			int[] arr = new int[9];
			for(int i=0; i<9; i++) {
				arr[i] = in.nextInt();
			}
			MyArr a = new MyArr(arr);
			int ans = dp(a);
			System.out.println(ans);
		}
		
	}
	MyArr a = new MyArr(new int[9]);
	private void check(int ind, int perm[], boolean used[]) {
		if(ind==9) {
			//a = new MyArr(perm);
			a.arr = perm;
			//a.arr = a.arr.clone();
			
			if(memo.containsKey(a)) {
				System.out.println(Arrays.toString(a.arr));
				System.out.println("fuck");
			}
			memo.put(a, 34);
		}
		for(int i=0; i<9; i++) {
			if(!used[i]) {
				used[i] = true;
				int prev = perm[ind];
				perm[ind] = i;
				check(ind+1, perm, used);
				used[i] = false;
				perm[ind] = prev;
			}
		}
		
	}
	final int oo = (int) 1e9;
	int count = 0;
	private int dp(MyArr a) {
		//if(count++>=5) return 0;
		if(memo.containsKey(a) && memo.get(a)==-2) return oo;
		if(ans.(a)) return 0;
		if(memo.containsKey(a)) return memo.get(a);
		
		//System.out.println("at ");
		for(int i=0; i<9; i++) {
			//if(i%3==0 && i!=0) System.out.println();
			//System.out.print(a.arr[i] + " ");
		}
		//System.out.println();
		//a.arr = a.arr.clone();
		memo.put(a, -2);
		int zeroI = 0;
		int zeroJ = 0;
		int zeroInd = 0;
		for(int b=0; b<9; b++) {
			if(a.arr[b]==0) {
				zeroI = b/3;
				zeroJ = b%3;
				zeroInd = b;
				break;
			}
		}
		//System.out.println(zeroI + " " + zeroJ);		
		int best = Integer.MAX_VALUE/3;
		for(int b=0; b<9; b++) {
			int curI = b/3;
			int curJ = b%3;
			if(Math.abs(curI-zeroI) + Math.abs(curJ-zeroJ) == 1) {
				int tmp = a.arr[b];
				a.arr[b] = a.arr[zeroInd];
				a.arr[zeroInd] = tmp;
				
				int ans = oo;
				ans=1+dp(a);
				best = Math.min(best, ans);
				
				tmp = a.arr[b];
				a.arr[b] = a.arr[zeroInd];
				a.arr[zeroInd] = tmp;
			}
		}
		//a.arr = a.arr.clone();
		memo.put(a, best);
		//System.out.println("size" + memo.size());
		return best;
	}
}
