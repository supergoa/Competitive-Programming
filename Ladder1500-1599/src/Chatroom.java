import java.util.Scanner;

public class Chatroom {
	public static void main(String[] args) {
		String s = new Scanner(System.in).next();
		String match = "hello";
		int pos=0;
		for(int i=0;i<s.length(); i++) {
			if(s.charAt(i)==match.charAt(pos)) {
				pos++;
			}
			if(pos==5) {
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");
	}
}
