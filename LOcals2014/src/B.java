import java.io.PrintWriter;
import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in,out);
		in.close();out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int G = in.nextInt();
			int P = in.nextInt();
			
			StringBuilder comb = new StringBuilder("");
			for(int g1=G; g1>=0; g1--) {
				for(int g2=0; g2<=G; g2++) {
					for(int g3=0; g3<=G; g3++) {
						if(g1+g2+g3==G && (g1*3 + g2*1)==P) {
							comb.append(g1+"-"+g2+"-"+g3+"\n");
						}
					}
				}
			}
			out.println("Team #"+(n+1));
			out.println("Games: "+(G));
			out.println("Points: "+(P));
			out.println("Possible records:");
			out.println(comb);
		}
		
	}
}
