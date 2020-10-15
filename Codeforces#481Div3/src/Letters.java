import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

public class Letters {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Letters().solve(in,out);
		in.close();
		out.close();
	}
	
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int M = in.nextInt();
		TreeMap<Long, Integer> rooms = new TreeMap<>();
		
		long cumsum = 0;
		rooms.put(cumsum, 0);
		for(int n=1; n<=N; n++) {
			cumsum += in.nextLong();
			rooms.put(cumsum, n);
		}
		for(int m=0; m<M; m++) {
			long b = in.nextLong();
			
			long totRoom = rooms.ceilingKey(b);
			int dormNum = rooms.get(totRoom);
			long roomNum = b - rooms.lowerKey(totRoom);
			out.println(dormNum + " " + roomNum);
		}
	}
}
