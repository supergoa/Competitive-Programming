import java.util.Scanner;

public class Contest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		int d = in.nextInt();
		int vaysa = point(b,d);
		int misha = point(a,c);
		if(vaysa==misha) {
			System.out.println("Tie");
		} else if(vaysa>misha) {
			System.out.println("Vasya");
		} else {
			System.out.println("Misha");
		}
	}
	static int point(int p, int t) {
		return Math.max(3*p/10, p-p/250*t);
	}
}
