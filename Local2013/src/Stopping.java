import java.io.PrintWriter;
import java.util.Scanner;

public class Stopping {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Stopping().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int totalMiles = in.nextInt();
			int oftenGas = in.nextInt();
			int oftenFood = in.nextInt();
			
			int curMiles = totalMiles;
			int curGas = oftenGas;
			int curFood = oftenFood;
			
			int stops = 0;
			while(curMiles > 0) {
				int nextStop = Math.min(curGas, curFood);
				nextStop = Math.min(curMiles, nextStop);
				curFood-=nextStop;
				curGas-=nextStop;
				curMiles -= nextStop;
				if(curMiles==0) break;
				if(curGas == 0) curGas = oftenGas;
				if(curFood == 0) curFood = oftenFood;
				stops++;
			}
			out.println(totalMiles+ " " + oftenGas + " " + oftenFood);
			out.println(stops);
		}
		
	}
}
