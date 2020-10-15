//package lpc2015;

import java.util.Scanner;

public class medal {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		
		for(int n=0; n<N; n++) {
			boolean count = false;
			boolean color = false;
			int[] usa = new int[3];
			int[] russia = new int[3];
			int countUsa = 0;
			int countRussia =0;
			for(int i=0; i<6; i++) {
				if(i<3) {
					usa[i] = scan.nextInt();
					countUsa += usa[i];
				} else {
					russia[i-3] = scan.nextInt();
					countRussia += russia[i-3];
				}
			}
			if(countUsa > countRussia) {
				count = true;
			}
			
			//color
			for(int i=0; i<3; i++) {
				if(usa[i]==russia[i]) {
					continue;
				} else {
					if(usa[i]>russia[i]) {
						color = true;
					}
					break;
				}
			}
			
			String string = "" ;
			for(int i=0; i<6; i++) {
				if(i<3) {
					string += usa[i] + " ";
				} else {
					string += russia[i-3] + " ";
				}
				
			}
			string.trim();
			System.out.println(string);
			
			if(color && count) {
				System.out.println("both");
			} else if (color){
				System.out.println("color");
			} else if(count) {
				System.out.println("count");
			} else {
				System.out.println("none");
			}
				
		}
	}
}
