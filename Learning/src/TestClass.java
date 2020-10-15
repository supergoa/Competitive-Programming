import java.util.Scanner;
import java.util.Stack;
 
public class TestClass {
public static void main(String[] args) {
	
	Scanner s  = new Scanner(System.in);
	int size = s.nextInt();
	Integer[] a = new Integer[size];
	Stack<Integer> stack = new Stack();
	for (int i = 0; i < size; i++) {
		a[i] = s.nextInt();
	}
	
//	for (int i = 0; i < size; i++) {
//		System.out.print(a[i]);
//	}
//	System.out.println();
	int count = 0, max = 0;
	for (int i = 0; i < size; i++) {
//		System.out.println("i= "+i);
 
		if(a[i] > 0 ){
			stack.push(a[i]);
//		System.out.println(" push: "+stack);
			if(i != 0 && a[i-1] > 0){
				count = 0;
			}
		}
		else if (a[i] < 0){
			if(!stack.isEmpty() && stack.peek() == (a[i]*-1)){
				stack.pop();
//				System.out.println("pop: "+stack);
				count+= 2;
				max = (count > max)? count : max;
			}
			else{
				stack.clear();
				count = 0;
			}
		}
	}
	System.out.println(max);
}
}