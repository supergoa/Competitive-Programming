import java.io.PrintWriter;
import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new C().solve(in, out);
		in.close();
		out.close();
	}

	boolean[] used;
	int[] lengths;
	int N, l1, l2;
	private void solve(Scanner in, PrintWriter out) {
		l1 = in.nextInt();
		l2 = in.nextInt();
		N = in.nextInt();
		used = new boolean[N];
		lengths = new int[N];
		for(int n=0; n<N; n++) {
			lengths[n] = in.nextInt();
		}
		
		//l1
		int max1 = Integer.MIN_VALUE;
		int ind1 = -1;
		int ind2 = -1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j)continue;
				int sum =lengths[i] + lengths[j];
				if(sum <= l1 && sum > max1) {
					max1 = lengths[i] + lengths[j];
					ind1 = i;
					ind2 = j;
				}
			}
		}
		if(ind1==-1 || ind2==-1) {
			out.println("Impossible");
			return;
		}
		//System.out.println("max1 "+max1);
		//l2
		used[ind1]=true;
		used[ind2]=true;
		int max2 = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j || used[i] || used[j])continue;
				int sum =lengths[i] + lengths[j];
				if(sum <= l2 && sum > max2) {
					max2 = sum;
				}
			}
		}
		int ans1 = 0;
		if(max1 != Integer.MIN_VALUE && max2 != Integer.MIN_VALUE) {
			ans1 = max1 + max2;
		} else {
			out.println("Impossible");
			return;
		}
		
		// same but switch l1 and l2
		
		int temp = l2;
		l2 = l1;
		l1 = temp;
		used = new boolean[N];
		
		max1 = Integer.MIN_VALUE;
		ind1 = -1;
		ind2 = -1;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j)continue;
				int sum =lengths[i] + lengths[j];
				if(sum <= l1 && sum > max1) {
					max1 = lengths[i] + lengths[j];
					ind1 = i;
					ind2 = j;
				}
			}
		}
		if(ind1==-1 || ind2==-1) {
			out.println("Impossible");
			return;
		}
		//System.out.println("max1 "+max1);
		//l2
		used[ind1]=true;
		used[ind2]=true;
		max2 = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==j || used[i] || used[j])continue;
				int sum =lengths[i] + lengths[j];
				if(sum <= l2 && sum > max2) {
					max2 = sum;
				}
			}
		}
		int ans2 = 0;
		if(max1 != Integer.MIN_VALUE && max2 != Integer.MIN_VALUE) {
			ans2 = max1 + max2;
		}
		out.println(Math.max(ans1, ans2));
	}

}
