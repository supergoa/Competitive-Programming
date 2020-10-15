import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class BalanceParenthesis2 {
	
	public static void main(String[] args) throws IOException {
			Scanner s = new Scanner(System.in);
			int N = s.nextInt();
			Stack<Integer> stack = new Stack<>();
			int x = 0;
			int max = 0; int counter = 0;
			int[] arr = new int[N];
			for(int i=0; i<N; i++) {
				arr[i] = s.nextInt();
			}
			
			for(int i=0; i<N; i++) {
				if (arr[i] > 0) {
					stack.push(arr[i]);
					if(i != 0 && arr[i-1] > 0){
						counter = 0;
					}
				} else {
					if(!stack.isEmpty() && (arr[i] == -stack.peek())) {
						stack.pop();
						counter+=2;
						if(counter > max) {
							max = counter;
						}
					} else {
						//stack.clear();
						counter=0;
					}
				}
			}
			if(counter > max) {
				max = counter;
			}
			System.out.println(max);
	}
	
}
