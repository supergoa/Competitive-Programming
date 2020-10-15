import java.math.BigDecimal;
import java.math.BigInteger;

public class Test {
	public static void main(String[] args) {
		double ans = 1;
		BigInteger num = new BigInteger("1");
		for(int i=1; i<=2500; i++) {
			num = num.multiply(new BigInteger(i+""));
		}
		
		BigInteger num2 = new BigInteger("1");
		for(int i=1; i<=1250; i++) {
			num2 = num2.multiply(new BigInteger(i+""));
		}
		num2 = num2.multiply(num2);
		System.out.println(num.divide(num2).toString());
	}
}
