import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class ChopChop {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new ChopChop().solve(in,out);
		in.close();
		out.close();
	}

	final int oo = (int) (1e9+123);
	int[] ids;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0;t<T;t++) {
			int N = in.nextInt();
			boolean good = true;
			ids = new int[N];
			for(int n=0; n<N; n++) {
				ids[n] = in.nextInt();
			}
			ids = compress(ids);
			TreeSet<Integer>[] ts = new TreeSet[N];
			for(int i=0; i<N; i++) ts[i] = new TreeSet<>();
			for(int i=0; i<N; i++) ts[ids[i]].add(i);
			for(int i=0; i<N; i++) {
				Integer first = ts[i].higher(-1);
				if(first==null) continue;
				Integer second = ts[i].higher(first);
				if(second==null) continue;
				
				int minDist = second-first;
				ArrayList<Integer> firstIds = new ArrayList<>();
				ArrayList<Integer> secondIds = new ArrayList<>();
				firstIds.add(first);
				secondIds.add(second);
				
				Integer next=null;
				while((next=ts[i].higher(second))!=null) {
					first = second.intValue();
					second = next.intValue();
					
					if(second-first < minDist) {
						firstIds.clear();
						secondIds.clear();
						
						firstIds.add(first);
						secondIds.add(second);
						minDist = second.intValue() - first.intValue();
					}
					else if(second-first == minDist) {
						firstIds.add(first);
						secondIds.add(second);
					}
				}
				
				for(int j=0; j<firstIds.size(); j++) {
					if(!containsSingle(firstIds.get(j), secondIds.get(j))) {
						out.println(0);
						good = false;
						break;
					}
				}
				if(!good) break;
			}
			if(good) {
				out.println(1);
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
		}
		return (singles>0);
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
