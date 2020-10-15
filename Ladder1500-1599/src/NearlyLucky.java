import java.util.Scanner;

public class NearlyLucky {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long num = in.nextLong();
		boolean lucky = true;
		int numLucky = 0;
		while(num>0) {
			long next = num%10;
			if(next==4 || next==7) {
				numLucky++;
			}
			num/=10;
		}
		if(numLucky==0) lucky = false;
		while(numLucky>0) {
			long next = numLucky%10;
			if(next!=4 && next!=7) {
				lucky=false;
				break;
			}
			numLucky/=10;
		}
		System.out.println(lucky?"YES":"NO");
		in.close();
	}
}
