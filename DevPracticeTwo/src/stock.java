import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class stock {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new stock().solve(in,out);
		in.close();
		out.close();
	}

	/*class stocks implements Comparable<stocks>{
		ArrayList<Integer> ids = new ArrayList<>();
		double profit;
		public stocks(double b) {
			//id = a;
			profit = b;
		}
		@Override
		public int compareTo(stocks arg0) {
			return Double.compare(profit/ids.size(), arg0.profit/arg0.ids.size());
		}
	}*/
	/**
2
3 50 10000
759.68 765.74 770.17
443 457.82 457.04
2 5.99 29999.99

59.99 58.99
22.67 20.73
	 * @param in
	 * @param out
	 */
	int D ;
	double T ;
	double[] days1;
	double[] days2 ;
	double M ;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			D = in.nextInt();
			T = in.nextDouble();
			days1 = new double[D];
			days2 = new double[D];
			M = in.nextDouble();
			for(int d=0; d<D; d++) {
				days1[d] = in.nextDouble();
			}
			for(int d=0; d<D; d++) {
				days2[d] = in.nextDouble();
			}
			//long time = System.currentTimeMillis();
			double ans = rec(0,0,M,0);
			//System.out.println((System.currentTimeMillis()-time)/1000.0);
			/*PriorityQueue<stocks> pq = new PriorityQueue<>();
			for(int d=0; d<D; d++) {
				for(int dd=d; dd<D; dd++) {
					if(d==dd) continue;
					int canBuy = (int) Math.floor((M-T)/days1[d]);
					double profit = canBuy * (days1[dd]-days1[d]) - 2*T;
					//profit = Double.parseDouble(df.format(profit));
					stocks s = new stocks(-profit);
					for(int a=d+1; a<=dd; a++) 
						s.ids.add(a);
					pq.add(s);
				}
			}
			for(int d=0; d<D; d++) {
				for(int dd=d; dd<D; dd++) {
					if(d==dd) continue;
					int canBuy = (int) Math.floor((M-T)/days2[d]);
					double profit = canBuy * (days2[dd]-days2[d]) - 2*T;
					//profit = Double.parseDouble(df.format(profit));
					stocks s = new stocks(-profit);
					for(int a=d+1; a<=dd; a++) 
						s.ids.add(a);
					pq.add(s);
				}
			}
			double canMake = dp(0, 0);
			boolean[] used = new boolean[D+1];
			while(!pq.isEmpty()) {
				stocks s = pq.poll();
				//System.out.println(s.profit);
				if(-s.profit < 0) break;
				boolean good = true;
				for(int id : s.ids) {
					if(used[id]) {
						good = false;
						break;
					}
				}
				if(good) {
					canMake += -s.profit;
					for(int id : s.ids) {
						used[id] = true;
					}
				}
			}*/
			//System.out.println("asjkdf");
			System.out.printf("%.2f\n",ans);
		}
		
	}

	/*double retMin(double[] arr) {
		double min = -oo;
		for(double a:arr) {
			min = Math.max(min, a);
		}
		return min;
	}*/
	//1498400.00
	//1499800.00
	//1498400.00
	final int oo = (int) (1e9+7);
	private double rec(int ind, int instock, double money, int numStocks) {
		if(ind==D) {
			return money;
		}
		double choose1 = -oo, choose2 = -oo, choose3 = -oo;
		choose1 = rec(ind+1, instock, money, numStocks);
		if(instock==0) {
			int canBuy = (int) Math.floor((money-T)/days1[ind]);
			choose2 = rec(ind+1, 1, money-canBuy*days1[ind]-T, canBuy);
			
			int canBuy2 = (int) Math.floor((money-T)/days2[ind]);
			choose3 = rec(ind+1, 2, money-canBuy2*days2[ind]-T, canBuy2);
			
			//choose3 = rec(ind+1, 0, money, numStocks);
		} else if(instock==1) {
			//choose2 = rec(ind+1, 1, money, numStocks);
			choose3 = rec(ind+1, 0, money+days1[ind]*numStocks-T, 0);
			
			double curMoney = money+days1[ind]*numStocks-T;
			int canBuy2 = (int) Math.floor((curMoney-T)/days2[ind]);
			choose2 = rec(ind+1, 2, curMoney-canBuy2*days2[ind]-T, canBuy2);
			
		} else if(instock==2) {
			//choose2 = rec(ind+1, 2, money, numStocks);
			choose3 = rec(ind+1, 0, money+days2[ind]*numStocks-T, 0);
			
			double curMoney = money+days2[ind]*numStocks-T;
			int canBuy = (int) Math.floor((curMoney-T)/days1[ind]);
			choose2 = rec(ind+1, 1, curMoney-canBuy*days1[ind]-T, canBuy);
		}
		return Math.max(choose1, Math.max(choose2, choose3));
	}
}
