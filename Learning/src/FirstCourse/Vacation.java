///package FirstCourse;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Vacation {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new Vacation().solve(scan, out);
		
		scan.close();
		out.close();
	}
	
	class Pair {
		int x,y,ride;
		public Pair(int x, int y, int ride) {
			this.x = x;
			this.y = y;
			this.ride = ride;
		}
		@Override
		public String toString() {
			return x + ", " + y + " | ";
		}
	}
	
	int[][] adj;
	ArrayList<Pair[]> permutations;
	int R;
	Pair[] rides;
	private void solve(Scanner scan, PrintWriter out) {
		int N = scan.nextInt();
		for(int n=0; n<N; n++) {
			
			R = scan.nextInt();
			int B = scan.nextInt();
			rides = new Pair[R];
			
			for(int r=0; r<R; r++) {
				int x = scan.nextInt();
				int y = scan.nextInt();
				rides[r] = new Pair(x,y,r);
			}
			
			// 0 = adj, 1 otherwise
			adj = new int[R][R];
			for(int b=0; b<B; b++) {
				int ride1 = scan.nextInt()-1;
				int ride2 = scan.nextInt()-1;
				adj[ride1][ride2] = 1;
				adj[ride2][ride1] = 1;
			}
			
			permutations = new ArrayList<>();
			getPermutations(0, new boolean[R], new Pair[R]);
			//checkPermutations();
			
			double minDistance = Integer.MAX_VALUE;
			for(int i=0; i<permutations.size(); i++) {
				
				boolean possible = true;
				double distance = 0;
				for(int j=0; j<permutations.get(i).length; j++) {
					if(j>=1 && adj[permutations.get(i)[j-1].ride][permutations.get(i)[j].ride] == 1) {
						possible = false;
						break;
					}
					if(j==0) {
						int x1 = permutations.get(i)[j].x;
						int y1 = permutations.get(i)[j].y;
						distance += Math.sqrt(x1*x1 + y1*y1);
					}
					else {
						int x1 = permutations.get(i)[j-1].x;
						int y1 = permutations.get(i)[j-1].y;
						int x2 = permutations.get(i)[j].x;
						int y2 = permutations.get(i)[j].y;
						distance += Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
						}
				}
				distance += 120*R;
				if(distance < minDistance && possible) {
					minDistance = distance;
				}
			}
			out.println("Vacation #" + (n+1)+":");
			if (minDistance==Integer.MAX_VALUE) {
				out.println("Jimmy should plan this vacation a different day.");
			} else {
				out.printf("Jimmy can finish all of the rides in %.3f seconds.\n", minDistance);
			}
			out.println();
		}
		
	}

	private void getPermutations(int size, boolean[] used, Pair[] perm) {
		if(R == size) {
			Pair[] copy = new Pair[size];
			for(int p=0; p<size; p++) {
				copy[p] = perm[p];
			}
			permutations.add(copy);
			
			return;
		}
		for(int i=0; i<R; i++) {
			if(!used[i]) {
				perm[size] = rides[i];
				used[i] = true;
				getPermutations(size+1, used, perm);
				used[i] = false;
			}
		}
		
	}
	/*private void getPermutations(int size, boolean validPerm, boolean[] used, Pair[] perm) {
		if(R == size) {
			if(validPerm) {
				Pair[] copy = new Pair[size];
				for(int p=0; p<size; p++) {
					copy[p] = perm[p];
				}
				permutations.add(copy);
			}
			return;
		}
		for(int i=0; i<R; i++) {
			if(!used[i]) {
				if(size >=1 && adj[i][perm[size-1].ride] == 1) {
					validPerm = false;
				}
				perm[size] = rides[i];
				used[i] = true;
				getPermutations(size+1, validPerm, used, perm);
				used[i] = false;
			}
		}
		
	}*/
}
