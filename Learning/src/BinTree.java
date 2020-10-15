import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class BinTree {
	public static void main(String[] args) {
		class Node {
			/*private int data;
			private Node left;
			private Node right;
			Node(int data){
				this.data = data;
			}
			public void setLeft(Node left) {
				this.left = left;
			}
			public Node getRight() {
				return this.right;
			}
			public Node getLeft() {
				return this.left;
			}
			public void setRight(Node right) {
				this.right = right;
			}
			
			int maxDepth(Node node) 
		    {
		        if (node == null)
		            return 0;
		        else
		        {
//		             compute the depth of each subtree
		            int lDepth = maxDepth(node.left);
		            int rDepth = maxDepth(node.right);
		  
//		             use the larger one 
		            if (lDepth > rDepth)
		                return (lDepth + 1);
		             else
		                return (rDepth + 1);
		        }
		    }
			
			int diameter(Node tree) {
//			    base case where tree is empty 
			   if (tree == null)
			     return 0;
			 
//			   get the height of left and right sub-trees 
			  int lheight = maxDepth(tree.left);
			  int rheight = maxDepth(tree.right);
			 
//			   get the diameter of left and right sub-trees 
			  int ldiameter = diameter(tree.left);
			  int rdiameter = diameter(tree.right);
			 
//			   Return max of following three
//			   1) Diameter of left subtree
//			   2) Diameter of right subtree
//			   3) Height of left subtree + height of right subtree + 1 
			  return max(lheight + rheight + 1, max(ldiameter, rdiameter));
			}
			
			int max(int a, int b) {
				if(a > b) {
					return a;
				} else {
					return b;
				}
			}
		}*/
			HashSet<Node> nodes = new HashSet<>();
			int data;
			Node(int data) {
				this.data = data;
			}
		}
		Scanner scan = new Scanner(System.in);
		int T, X;
		T = scan.nextInt();
		X = scan.nextInt();
		
		Node[] graph = new Node[T];
		HashMap<String, Integer> hm = new HashMap<>();
		graph[0] = new Node(X);
		hm.put("", 0);
		for(int i = 1; i<T; i++) {
			hm.put(scan.next(), i);
			graph[i] = new Node(scan.nextInt()); // scan useless ints
		}
		for(String str : hm.keySet()) {
			for(int i = 0; i< str.length()-1; i++) {
				String parent = str.substring(0,i);
				String child = str.substring(0,i+1);
				Node p = graph[hm.get(parent)];
				Node c = graph[hm.get(child)];
				p.nodes.add(c);
				c.nodes.add(p);
			}
		}
		
	}

}

