import java.util.Scanner;

public class testtt {
	static class Node {
		Node next;
		int value;
		
		public Node(int a) {
			this.value = a;
		}
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		
		Node ll = new Node(in.nextInt());
		Node first = ll;
		Node last = null;
		for(int n=1; n<N; n++) {
			Node newNode = new Node(in.nextInt());
			ll.next = newNode;
			ll = ll.next;
			if(n==N-1) last = newNode;
		}
		ll.next = first;
		
		int insert = in.nextInt();
		// 2 4 6 8
		ll = first;
		if(insert <= first.value || insert >= last.value) {
			Node insertNode = new Node(insert);
			insertNode.next = first;
			last.next = insertNode;
			
			if(insert<=first.value) {
				first = insertNode;
			}
			if(insert >= last.value) {
				last = insertNode;
			}
		} else {
			while(ll.next.value <= insert) {
				ll = ll.next;
				if(ll.next==first) {
					break;
				}
			}
			Node insertNode = new Node(insert);
			insertNode.next = ll.next;
			ll.next = insertNode;
		}
		
		ll = first;
		for(int n=0; n<N+1; n++) {
			System.out.print(ll.value + ", ");
			ll = ll.next;
		}
		in.close();
	}
}
