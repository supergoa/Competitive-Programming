import java.util.Scanner;

public class A4Watermellon {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		System.out.println((x%2==0&&x>2)?"YES":"NO");
		in.close();
	}
}
