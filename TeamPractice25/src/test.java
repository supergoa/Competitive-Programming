import java.util.HashMap;
import java.util.HashSet;

public class test {
	/*public static void main(String[] args) {
		HashSet<String> newTrains = new HashSet<>();
		//boolean[][] newTrains = new boolean
		long time = System.currentTimeMillis();
		int K = 500000;
		for(int k=0; k<K; k++) {
			newTrains.add((k-1) + "," + (-k-1));
		}
		boolean[] ans = new boolean[K];
		for(int k=0; k<K; k++) {
			ans[k] = newTrains.contains(k+","+(-k+5));
		}
		boolean toEndAPrice = (newTrains.contains("0,"+(K-1)))?true:false;
		if(toEndAPrice) System.out.println("djafhsjkdfhalskfj");
		long time2 = System.currentTimeMillis();
		System.out.println((time2-time));
	}*/
	public static void main(String[] args) {
		HashMap<Integer,HashSet<Integer>> newTrains = new HashMap<>();
		//boolean[][] newTrains = new boolean
		long time = System.currentTimeMillis();
		int K = 500000;
		for(int k=0; k<K; k++) {
			if(newTrains.get(k-1)==null) newTrains.put(k-1, new HashSet<>());
			newTrains.get(k-1).add(-k-1);
		}
		boolean[] ans = new boolean[K];
		for(int k=0; k<K; k++) {
			if(newTrains.get(k)!=null)
				ans[k] = newTrains.get(k).contains((-k+5));
		}
		boolean toEndAPrice = (newTrains.get(0).contains((K-1)))?true:false;
		if(toEndAPrice) System.out.println("djafhsjkdfhalskfj");
		long time2 = System.currentTimeMillis();
		System.out.println((time2-time));
	}
}
