import java.util.Scanner;

public class MRTee {
	static Pair[] arr;
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		for (int n = 0; n < N; n++) {
			int P = s.nextInt();

			arr = new Pair[P];

			for (int p = 0; p < P; p++) {
				arr[p] = new Pair();
				arr[p].x = s.nextDouble(); // x
				arr[p].y = s.nextDouble(); // y
			}
			
			int count = 0;
			for (int a = 0; a < arr.length; a++) {
				for (int b = 0; b < arr.length; b++) {
					for (int c = 0; c < arr.length; c++) {
							for (int d = 0; d < arr.length; d++) {
								if (a != d && b != d && c != d && a != c && b != c && a!=b) {
	
									/*Pair[] temp = new Pair[4];
									temp[0] = arr[a];
									temp[1] = arr[b];
									temp[2] = arr[c];
									temp[3] = arr[d];
									//Arrays.sort(temp);
									String str = new String(""+temp[0]+" "+temp[1]+" "+temp[2]+" "+temp[3]);
									System.out.println(str);*/
									//if(hm.getOrDefault(str, 0) != 1) {
										if (checkT(a,b,c,d)) {
											//System.out.println(str);
												count++;
										}
										//hm.put(str, 1);
										//str = new String(""+temp[3]+" "+temp[1]+" "+temp[2]+" "+temp[1]);
										//hm.put(str, 1);
									//}
								}
							}
						
					}
				}
			}
			System.out.println("Set #" + (n + 1) + ": " + count/2);
			System.out.println();
		}
		s.close();
	}

	private static boolean checkT(int a, int m, int c, int b) {
		if (midpoint(arr[a], arr[b], arr[m]) && equal(length(arr[a], arr[b]), length(arr[m], arr[c])) && perpendicular(arr[a], arr[m], arr[c])) {
			return true;
		}
		return false;
	}

	private static boolean perpendicular(Pair a, Pair b, Pair c) {
		 double l1 = length(a,b);
		 double l2 = length(b,c);
		 return (equal(Math.pow(l1, 2) + Math.pow(l2, 2), Math.pow(length(a,c),2)));
	}

	private static boolean midpoint(Pair pair1, Pair pair2, Pair pair3) {
		 //System.out.println("In midpoint");
		if (equal((pair1.x + pair2.x) / 2.0, pair3.x) && equal((pair1.y + pair2.y) / 2.0, pair3.y)) {
			 //System.out.println("returned true");
			return true;
		}
		 //System.out.println("returned false");
		return false;
	}

	private static double length(Pair pair1, Pair pair2) {
		 //System.out.println("In length");
		return Math.sqrt(Math.pow(pair2.x - pair1.x, 2) + Math.pow(pair2.y - pair1.y, 2));
	}

	static boolean equal(double a, double b) {
		boolean flag = (Math.abs(a - b) <= .00001 || Math.abs(b - a) <= .00001);
		 //System.out.println("In equal");
		 //System.out.println(a + " " + b);
		 //System.out.println(flag);
		return flag;
	}
}
