import java.util.HashMap;
import java.util.Scanner;

public class UCFPalindromes {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		for(int t =0; t<N; t++) {
			System.out.println("Test case #"+(t+1));
			int P = s.nextInt();
			HashMap<Character, Character> same = new HashMap<>();
			for(int p = 0; p<P; p++) {
				char a = s.next().charAt(0);
				char b = s.next().charAt(0);
				same.put(a, b);
				same.put(b, a);
			}
			
			int Q = s.nextInt();
			s.nextLine();
			
			for(int q = 0; q<Q; q++) {
				String str = s.nextLine();
				boolean isPal = true;
				for(int i=0; i<str.length()/2; i++) {
					char a = str.charAt(i);
					char b = str.charAt(str.length()-i-1);
					if(a!=b && same.getOrDefault(a, '0')!=b) {
						isPal = false;
						break;
					}
				}
				if(isPal) {
					System.out.println(str + " YES");
				} else {
					System.out.println(str + " NO");
				}
			}
			System.out.println();
		}
	}
}
