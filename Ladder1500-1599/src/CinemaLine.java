import java.io.PrintWriter;
import java.util.Scanner;

public class CinemaLine {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new CinemaLine().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		int tfs = 0;
		int fys = 0;
		//int ohs = 0;
		for(int n=0; n<N; n++) {
			int bill = in.nextInt();
			if(bill == 25) {
				tfs++;
			}
			if(bill == 50) {
				fys++;
				if(tfs<=0) {
					out.println("NO");
					return;
				} else {
					tfs--;
				}
			}
			if(bill == 100) {
				//ohs++;
				if(fys>=1 && tfs>=1) {
					fys--;
					tfs--;
				} else if(tfs>=3){
					tfs-=3;
				} else {
					out.println("NO");
					return;
				}
			}
		}
		out.print("YES");
		
	}
}
