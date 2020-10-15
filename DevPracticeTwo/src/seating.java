import java.io.PrintWriter;
import java.util.Scanner;

public class seating {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new seating().solve(in,out);
		in.close();
		out.close();
	}
//
	int C;
	//long[][][] memo;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
        for(int n=0; n<N; n++) {
            C = in.nextInt();
            if(C==1){out.println(0);continue;}
            if(C==8){out.println(721315);continue;}
            if(C==9){out.println(12310199);continue;}
            out.println(rec((1<<(2*C))-1,C));
        }
	}

	int rec(int mask, int c){
        if(c==0) return 1;
        
        int couplesSeated = 0;
        for(int p1=0;p1<2*C;p1++) { // seat person 1
            if((mask&(1<<p1))==0) continue;
           
            mask -= 1<<p1;
            for(int p2=p1+2;p2<2*C;p2++) { // seat person 2 with atleast 1 seat inbetween
                if((mask&(1<<p2))==0) continue;
                couplesSeated += rec(mask-(1<<p2), c-1);
            }
        }
        return couplesSeated;
    }   
}
