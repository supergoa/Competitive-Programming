import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class ChopChop2 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new ChopChop2().solve(in,out);
		in.close();
		out.close();
	}

	final int oo = (int) (1e9+123);
	int[] ids;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0;t<T;t++) {
			int N = in.nextInt();
			ids = new int[N];
			for(int n=0; n<N; n++) {
				ids[n] = in.nextInt();
			}
			ids = compress(ids);
			if(containsSingle(0, N-1)) {
				out.println(1);
			} else {
				out.println(0);
			}
		}
		
	}

	private boolean containsSingle(Integer a, Integer b) {
		HashSet<Integer> uniqueNums = new HashSet<>();
		int singles = 0;
		for(int i=a.intValue(); i<=b.intValue(); i++) {
			if(!uniqueNums.contains(ids[i])) {
				uniqueNums.add(ids[i]);
				singles++;
			} else {
				singles--;
			}
			if(singles<=0) return false;
		}
		return true;
	}

	private int[] compress(int[] ids) {
		HashMap<Integer, Integer> nums = new HashMap<>();
		int[] ret = new int[ids.length];
		for(int i:ids) {
			if(nums.get(i)==null) {
				nums.put(i, nums.size());
			}
		}
		for(int i=0; i<ids.length; i++) {
			ret[i] = nums.get(ids[i]);
		}
		return ret;
	}
}
