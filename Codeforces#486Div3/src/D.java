import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class D {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new D().solve(in, out);
		in.close();
		out.close();
	}

	HashSet<Long> nums;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		nums = new HashSet<>();
		for(int n=0;n<N; n++) {
			nums.add(in.nextLong());
		}
		//int ans = 1;
		ArrayList<Long> ans = new ArrayList<>();
		for(Long a:nums) {
			Long high = getHigher(a);
			Long low = getLower(a);
			
			if(high!=null && low!=null) {
				long diff = Math.abs(high-low);
				int bits = Long.bitCount(diff);
				if(bits==1) {
					ans.clear();
					ans.add(a);
					ans.add(high);
					ans.add(low);
					break;
				}
			}
			else if(high!=null && ans.size()<2) {
				ans.clear();
				ans.add(a);
				ans.add(high);
			}
			else if(low!=null && ans.size()<2) {
				ans.clear();
				ans.add(a);
				ans.add(low);
			}
		}
		if(ans.size()==0) {
			out.println(1);
			out.println(nums.stream().findFirst().get());
		} else {
			out.println(ans.size());
			for(Long a:ans) {
				out.print(a + " ");
			}
		}
		
	}

	private Long getLower(Long a) {
		for(long i=1; i<2000000001; i*=2) {
			//if(a==1)System.out.println(i + " " + (a-i));
			if(nums.contains(a-i)) {
				return a-i;
			}
		}
		return null;
	}

	private Long getHigher(Long a) {
		for(long i=1; i<2000000001; i*=2) {
			//if(a==1)System.out.println(i + " " + (a+i));
			if(nums.contains(a+i)) {
				return a+i;
			}
		}
		return null;
	}
}
