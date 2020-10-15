import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int sum = 0;
			String check = in.next();
			for(int i=0; i<check.length(); i++) {
				if(check.charAt(i)=='a' 
						||check.charAt(i)=='e'
								||check.charAt(i)=='i'
										||check.charAt(i)=='o'
												||check.charAt(i)=='u'
														)
					sum++;
					else {
						sum--;
					}
			}
			if(sum>0) System.out.println(check + "\n1");
			else System.out.println(check + "\n0");
		}
	}
}
