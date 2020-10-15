import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Friday {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Friday().solve(in,out);
		in.close();
		out.close();
	}

	class Person implements Comparable<Person> {
		int id, haunt;
		public Person(int h, int i) {
			haunt = h;
			id = i;
		}
		@Override
		public int compareTo(Person o) {
			return Integer.compare(haunt, o.haunt);
		}
		public String toString() {
			return haunt + " ";
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		
		Person[] freshman = new Person[N];
		int[] haunted = new int[M];
		for(int n=0; n<N; n++) {
			freshman[n] = new Person(in.nextInt(), n);
		}
		Arrays.sort(freshman);
		for(int m=0; m<M; m++) {
			haunted[m] = in.nextInt();
		}
		Arrays.sort(haunted);
		
		int[] ans = new int[N];
		int ind = 0;
		for(int i=0; i<M; i++) {
			while(ind < N && freshman[ind].haunt <= haunted[i]) {
				ans[freshman[ind].id] = M-i;
				ind++;
			}
		}
		for(int i:ans) {
			out.print(i + " ");
		}
	}
}
