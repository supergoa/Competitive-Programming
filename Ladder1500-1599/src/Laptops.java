import java.util.Arrays;
import java.util.Scanner;

public class Laptops {
	
	class Pair implements Comparable<Pair>{
		int price;
		int quality;
		public Pair(int price, int quality) {
			this.price=price;
			this.quality=quality;
		}
		@Override
		public int compareTo(Pair o) {
			return Integer.compare(this.quality, o.quality);
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new Laptops().solve(in);
	}
	private void solve(Scanner in) {
		int N = in.nextInt();
		int[] prices = new int[N];
		Pair[] laptops = new Pair[N];
		for(int n=0; n<N; n++) {
			int price = in.nextInt();
			int quality = in.nextInt();
			prices[n] = price;
			laptops[n] = new Pair(price, quality);
		}
		Arrays.sort(prices);
		Arrays.sort(laptops);
		for(int p=0; p<laptops.length; p++) {
			if(laptops[p].price!=prices[p]) {
				System.out.println("Happy Alex");
				return;
			}
		}
		System.out.println("Poor Alex");
	}
}
