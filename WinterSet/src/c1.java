import java.util.PriorityQueue;
import java.util.Scanner;

public class c1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new c1().solve(in);
		in.close();
	}

	int GCD(int A, int B) {
	    if(B==0)
	        return A;
	    else
	        return GCD(B, A % B);
	}
	class Point implements Comparable<Point>{
		int num;
		int freq;
		public Point(int a, int b) {
			num = a;
			freq = b;
		}
		@Override
		public int compareTo(Point o) {
			return Integer.compare(freq, o.freq);
		}
	}
	private void solve(Scanner in) {
		int N = in.nextInt();
		Point[] p = new Point[N+1];
		for(int i=1; i<=N; i++) {
			p[i] = new Point(i, 1);
		}
		p[1].freq=0;
		for(int i = 2; i+i <= N; i++) {
            for(int j=i+i; j <= N; j+=i) {
                p[j].freq++;
                p[i].freq++;
            }
        }
		PriorityQueue<Point> pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++) {
			pq.add(p[i]);
		}
		int currGCD = -pq.peek().num;
		int[] gcds = new int[N+1];
		int ind = N;
		boolean[] removed = new boolean[N+1];
		int[] order = new int[N+1];
		while(!pq.isEmpty()) {
			Point pop = pq.poll();
			removed[pop.num] = true;
			order[N+1-ind--] = pop.num;
			//System.out.println();
			System.out.println(pop.num);
			
			for(int i=pop.num*2; i<=N; i+=pop.num) {
				if(!removed[i]) {
					
					pq.remove(p[i]);
					p[i].freq--;
					pq.add(p[i]);
					//System.out.println(i+" "+p[i].freq); 
				}
			}
		}
		System.out.println("st");
		for(int i=N; i>=1; i--) {
			System.out.println(i);
			gcds[i] = currGCD = GCD(currGCD, p[order[i]].num);
		}
		for(int i=1; i<=N; i++) {
			System.out.print(gcds[i] + " ");
		}
	}
}
