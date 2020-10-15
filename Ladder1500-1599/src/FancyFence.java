import java.util.Scanner;

public class FancyFence {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int a = in.nextInt();
			boolean found = false;
			for(int i=3; i<=2000; i++) {
				if((i-2)*180 == i*a) {
					System.out.println("YES");
					found = true;
					break;
				}
			}
			if(!found) {
				System.out.println("NO");
			}
		}
	}
}
