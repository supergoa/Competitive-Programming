import java.util.HashMap;
import java.util.Scanner;

public class prob9 {
	public static void main(String[] args) {
		HashMap<Integer, Integer> hm1 = new HashMap<>();
		hm1.put(3, 99);
		hm1.put(1, 27);
		HashMap<Integer, Integer> hm2 = new HashMap<>();
		hm2.put(1, 27);
		hm2.put(3, 99);
		
		System.out.println(hm1.hashCode() + "  " + hm2.hashCode());
	}
}
