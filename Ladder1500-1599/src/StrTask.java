import java.util.Scanner;

public class StrTask {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String str = in.next().toLowerCase();
		for(int i=0; i<str.length(); i++) {
			if(!vowel(str.charAt(i))) {
				System.out.print("."+str.charAt(i));
			}
		}
	}

	private static boolean vowel(char a) {
		if(a=='a'||a=='i'||a=='o'||a=='u'||a=='y'||a=='e') {
			return true;
		}
		return false;
	}
}
