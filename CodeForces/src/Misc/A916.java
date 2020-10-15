
package Misc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class A916 {
	
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();
		int hh = scan.nextInt();
		int mm = scan.nextInt();
		boolean contains = false;
		int counter =0;
		while(true) {
			int tmp = mm;
			for(int i=0; i<(tmp+"").length(); i++) {
				if (tmp%10 == 7) {
					contains = true;
				}
				tmp/=10;
			}
			int tmp1 = hh;
			for(int i=0; i<(tmp1+"").length(); i++) {
				if (tmp1%10 == 7) {
					contains = true;
				}
				tmp1/=10;
			}
			
			if (contains) {
				break;
			}
			
			mm = mm-x;
			if(mm<0) {
				mm+=60;
				hh--;
			}
			if(hh<0) {
				hh+=24;
			}
			
			counter++;
			
			
		}
		System.out.println(counter);
	}
}
