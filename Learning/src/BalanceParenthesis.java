import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class BalanceParenthesis {
	
	public static void main(String[] args) throws IOException {
			Scanner s = new Scanner(System.in);
			int N = s.nextInt();
			Stack<Integer> stackp = new Stack<>();
			int x = 0;
			int max = 0;
			int counter = 0;
			while(s.hasNextInt()) {
				x = s.nextInt();
				if (x > 0) {
					//System.out.println("Pushing: "+ x);
					stackp.push(x);
				} else {
					
					while(!stackp.isEmpty()) {
						//System.out.println("Negative x is:" + x);
						//System.out.println("Stack has:" + stackp.peek());
						if(stackp.pop() == Math.abs(x)) {
							//System.out.println("Match: " + Math.abs(x));
							//System.out.println("Stack size" + stackp.size());
							counter++;
							x=0;
							//System.out.println("counter:"+counter);
						}
						else {
							stackp.clear();
							counter = 0;
						}
						if(counter > max) {
							max = counter;
						}
						if(!stackp.isEmpty() && s.hasNextInt()) {
							//System.out.println("Reading next negative...");
							x = s.nextInt();
							while(x>0) {
								stackp.push(x);
								if(s.hasNextInt()) {
									x = s.nextInt();
								} else {
									break;
								}
							}
						}
					}
				}
			}
			/*System.out.println();
			for(int j = 0; j < stackp.size(); j++) {
				System.out.println(stackp.pop());
			}*/
			System.out.println(max*2);
	}
	
}
