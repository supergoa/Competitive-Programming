//package lpc2015;

import java.util.Scanner;

public class twins {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n=scan.nextInt();
		for(int N=0; N<n; N++) {
			boolean zack = false;
			boolean mack = false;
			int arr[] = new int[10];
			for(int num=0;num<10;num++) {
				arr[num] = scan.nextInt();
				if(arr[num]==18) {
					mack=true;
				}
				if(arr[num]==17) {
					zack = true;
				}
			}
			String string = "" ;
			for(int a:arr) {
				string += (a + " ");
			}
			string.trim();
			System.out.println(string);
			
			if(mack && zack) {
				System.out.println("both");
			} else if (mack){
				System.out.println("mack");
			} else if(zack) {
				System.out.println("zack");
			} else {
				System.out.println("none");
			}
		}
	}
}
