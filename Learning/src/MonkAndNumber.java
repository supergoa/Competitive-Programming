import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MonkAndNumber {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			long series = 1;
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				series = ((Long.parseLong(st.nextToken()) % (1000000000+7)) * (series % (1000000000+7))) % (1000000000+7);
			}
			System.out.println(F(series)% (1000000000+7));
		}
	}
	
	static long F(long x) {
		long sum = 0;
		if(x == 1) {
			sum = G(1); 
		} else {
			sum = ( (G(x) % (1000000000+7) )+ ( G(1) % (1000000000+7) ) % (1000000000+7)); // x will always be divisible by itself and 1
		}
		
		/*if(Math.floor(Math.sqrt(x)) == (Math.sqrt(x))) {
			//System.out.println("x:"+x);
			sum += Math.sqrt(x) % (1000000000+7); //x has an integer sqrt
			//System.out.println("sum:"+sum);
		}*/
		for(long d=2; d<=Math.floor(Math.sqrt(x)); d++) {
			if(x%d == 0) {
				//System.out.println("D:"+d + "and:"+x/d);
				
				if(d != x/d) {
					sum += (G(d)% (1000000000+7) + G(x/d)% (1000000000+7)) % (1000000000+7);
				} else {
					sum += G(d) % (1000000000+7);
				}
			}
		}

		return sum% (1000000000+7);
	}
	static long G(long x) {
		long sum = 0;
		if(x == 1) {
			sum = 1; 
		} else {
			sum = x+1; // x will always be divisible by itself and 1
		}
		
		/*if(Math.floor(Math.sqrt(x)) == (Math.sqrt(x)) && x>1) {
			//System.out.println("x:"+x);
			sum += Math.sqrt(x) % (1000000000+7); //x has an integer sqrt
			//System.out.println("sum:"+sum);
		}*/
		for(long d=2; d<=Math.floor(Math.sqrt(x)); d++) {
			if(x%d == 0) {
				if(d != x/d) {
					sum += ((d%(1000000000+7)) + ((x/d)%(1000000000+7))) % (1000000000+7);
				} else {
					sum += d % (1000000000+7);
				}
			}
		}
		return sum% (1000000000+7);
	}
}
