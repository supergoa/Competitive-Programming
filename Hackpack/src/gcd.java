
public class gcd {
	public static void main(String[] args) {
		System.out.println("GCD" + GCD(10,15));
	}
	
	int x,y;
	int extendedGCD(int a, int b) {
	    if (a == 0) {
	        x = 0;
	        y = 1;
	        return b;
	    }
	    int x1 = 0, y1 = 0;
	    int d = extendedGCD(b % a, a);
	    x = y1 - (b / a) * x1;
	    y = x1;
	    return d; // returns gcd, ans in global x,y
	}
	
	static int GCD(int A, int B) {
	    if(B==0)
	        return A;
	    else
	        return GCD(B, A % B);
	}
}
