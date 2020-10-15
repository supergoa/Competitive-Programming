import java.io.PrintWriter;
import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int T = in.nextInt();
        while(T-->0) {
            int r,g,b;
            r = in.nextInt();
            g = in.nextInt();
            b = in.nextInt();
            int low = Math.min(r,b);
            low = Math.min(low, g);
            int high = Math.max(r,b);
            high = Math.max(high, g);
            int mid = r+g+b-low-high;
            if(low+mid>high) {
            	int diff = high-mid;
            	low-=diff;
            	mid+=diff;
            	high+=low/2;
            	mid+=low/2;
            	out.println(high);
            } else {
            	out.println(low+mid);
            }
        }
        in.close();
        out.close();
    }
}