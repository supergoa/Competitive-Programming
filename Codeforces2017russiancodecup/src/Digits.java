import java.util.*; 
  
class Digits { 
    // Utility method to get first digit of x 
    public static long getFirstDigit(long x) 
    { 
        while (x >= 10) 
            x /= 10; 
        return x; 
    } 
  
    // method to return count of numbers with same 
    // starting and ending digit from 1 upto x 
    public static long getCountWithSameStartAndEndFrom1(long end) 
    { 
        if (end < 10) 
            return end; 
  
        // get ten-spans from 1 to x 
        long tens = end / 10; 
  
        // add 9 to consider all 1 digit numbers 
        long res = tens + 9; 
  
        // Find first and last digits 
        long firstDigit = getFirstDigit(end); 
        long lastDigit = end % 10; 
  
        // If last digit is greater than first 
        // digit then decrease count by 1 
        if (lastDigit < firstDigit) 
            res--; 
  
        return res; 
    } 
  
    // Method to return count of numbers with same 
    // starting and ending digit between start and end 
    public static long getCountWithSameStartAndEnd(long start, 
                                                  long end) 
    { 
        return getCountWithSameStartAndEndFrom1(end)  
        - getCountWithSameStartAndEndFrom1(start - 1); 
    } 
  
    // driver code 
    public static void main(String[] args) 
    {
    	Scanner in = new Scanner(System.in);
    	int T = in.nextInt();
        while(T-->0) {
	    	long start = in.nextLong(), end = in.nextLong(); 
	        System.out.println(getCountWithSameStartAndEnd(start, end));
        }
    } 
} 