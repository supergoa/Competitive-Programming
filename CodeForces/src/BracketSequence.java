import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Scanner;

public class BracketSequence {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new BracketSequence().solve(in,out);
		in.close();out.close();
	}
	

	private void solve(Scanner in, PrintWriter out) {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		stack.add('a');
		stack.add('b');
		stack.add('c');
		out.print(stack.poll());
		for (char c : stack) out.print(c);
		out.flush();
		int N = in.nextInt();
		int K = in.nextInt();
		char[] s = in.next().toCharArray();
		/*int open = 0;
		int closed = 0;
		int ind = -1;
		for(int i=0; i<N; i++) {
			if(s[i]=='(') open++;
			else {
				closed++;
				if(open>=K/2 && closed>=K/2) {
					ind = i;
					break;
				}
			}
		}*/
		int open = K/2;
		int closed = K/2;
		StringBuilder ans = new StringBuilder("");
		for(int i=N-1; i>=0; i--) {
			if(s[i]=='(' && open>0 && open>closed) {
				open--;
				ans.append("(");
			} else if(closed>0) {
				closed--;
				ans.append(")");
				
			}
		}
		ans.reverse();
		out.println(ans);
		
	}
}
