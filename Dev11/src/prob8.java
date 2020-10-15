import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class prob8 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new prob8().solve(in);
	}
	
	private void solve(Scanner in) {
		
		int T = in.nextInt();
		bfs(123456780);
		for(int t=0; t<T; t++) {
			int mult = (int) 1e8;
			int start = 0;
			for(int i=0; i<9; i++) {
				start += in.nextInt() * mult;
				mult /= 10;
			}
			System.out.println(depth.get(start));
		}
		
	}
	
	final int oo = (int) 1e9;
	HashSet<Integer> visited = new HashSet<>();
	HashMap<Integer, Integer> depth = new HashMap<>();
	private void bfs(int a) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(a);
		visited.add(a);
		depth.put(a,0);

		while(!q.isEmpty()) {
			int c = q.poll();
			int[] board = intCharAt(c);
			int zeroI = 0;
			int zeroJ = 0;
			int zeroInd = 0;
			for(int b=0; b<9; b++) {
				if(board[b]==0) {
					zeroI = b/3;
					zeroJ = b%3;
					zeroInd = b;
					break;
				}
			}

			for(int b=0; b<9; b++) {
				int curI = b/3;
				int curJ = b%3;
				if(Math.abs(curI-zeroI) + Math.abs(curJ-zeroJ) == 1) {
					int tmp = board[b];
					board[b] = board[zeroInd];
					board[zeroInd] = tmp;
					
					int toSend = toInt(board);
					if(!visited.contains(toSend)) {
						q.add(toSend);
						visited.add(toSend);
						depth.put(toSend, depth.get(c)+1);
					}
					
					tmp = board[b];
					board[b] = board[zeroInd];
					board[zeroInd] = tmp;
				}
			}
		}
	}
	
	private int toInt(int[] board) {
		int start = 0;
		int mult = (int) 1e8;
		for(int i=0; i<9; i++) {
			start += board[i] * mult;
			mult /= 10;
		}
		return start;
	}
	private int[] intCharAt(int a) {
		int[] ret = new int[9];
		for(int i=8; i>=0; i--) {
			ret[i] = a%10;
			a/=10;
		}
		return ret;
	}
}
