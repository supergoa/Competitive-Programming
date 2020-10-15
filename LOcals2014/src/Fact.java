import java.math.BigInteger;
import java.util.Scanner;

public class Fact {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int i=0; i<12; i++) {
			System.out.print(i+"L*");
		}
		System.out.println(1L*2L*3L*4L*5L*6L*7L*8L*9L*10L*11L*12L*13L*14L*15L);
		BigInteger bi = new BigInteger("1.628888424e7411");
	}
}
