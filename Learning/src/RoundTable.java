import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

public class RoundTable {
	static int N = 0;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		int Q = s.nextInt();
		
		// map (university, position)
		HashMap<Integer, TreeSet<Integer>> hm = new HashMap<>();
		
		// dp
		HashMap<String, Integer> hmSolns = new HashMap<>();
		
		// contains all positions of students who go to university x such that hm.get(x) = list of positions
		TreeSet<Integer> tempList = new TreeSet<>();
		for(int i=0; i<N; i++) {
			int pos = s.nextInt();
			tempList = hm.getOrDefault(pos, new TreeSet<>()); // add position (index) to list
			tempList.add(i);
			hm.put(pos, tempList);
		}
		for(int i=0; i<Q; i++) {
			int x = s.nextInt();
			int y = s.nextInt();
			if(x==y) {
				System.out.println(0);
				continue;
			}
			// get some dp up in this bitch
			if(hmSolns.getOrDefault(x+" "+y, null) != null) {
				System.out.println(hmSolns.get(x+" "+y));
			} else {
				int soln = distance(hm.get(x), hm.get(y));
				hmSolns.put(x+" "+y, soln);
				hmSolns.put(y+" "+x, soln);
				System.out.println(soln);
			}
		}
		
		s.close();
	}
	
	// Can brute force due to list size reduction
	public static int distance(TreeSet<Integer> positionsOfUni1, TreeSet<Integer> positionsOfUni2) {
		int min = Integer.MAX_VALUE;
		//System.out.println(positionsOfUni1);
		//System.out.println(positionsOfUni2);
		for(Integer x : positionsOfUni1) {
			
			Integer num1 = positionsOfUni2.higher(x);
			Integer num2 = positionsOfUni2.lower(x);
			
			if(num1 != null) {
				min = Math.min(min, Math.abs(x-num1)-1);
			} else {
				min = Math.min(min, N-Math.abs(x-positionsOfUni2.first())-1);
			}
			if(num2 != null) {
				min = Math.min(min, Math.abs(x-num2)-1);
			} else {
				min = Math.min(min, N-Math.abs(x-positionsOfUni2.last())-1);
			}
			
		}
		
		if(min%2==1) {
			return (min+1)/2;
		}
		return min/2;
	}
}
