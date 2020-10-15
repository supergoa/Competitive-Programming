import java.util.ArrayDeque;
import java.util.Scanner;

public class atoi {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String s1 = in.next();
		String s2 = in.next();
		
		int carry = 0;
		int sum = 0;
		
		int ind = Math.max(s1.length(), s2.length())-1;
		
		ArrayDeque<Integer> ans = new ArrayDeque<>();
		while(ind >= 0) {
			sum = carry;
			//System.out.println(s1.charAt(ind) + " " + s1.charAt(ind););
			if(s1.length()-1 >= ind) {
				sum += s1.charAt(ind)-'0';
			}
			if(s2.length()-1 >= ind) {
				sum += s2.charAt(ind)-'0';
			}
			carry = sum/10;
			sum = sum%10;
			ans.push(sum);
			ind--;
		}
		ans.push(carry);
		while(!ans.isEmpty()) {
			System.out.println(ans.pop());
		}
	}
}
