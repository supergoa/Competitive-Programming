import java.util.ArrayDeque;
import java.util.Scanner;

public class editor {
	
	public static void main(String[] args) {
		
		class pair {
			
			public int row;
			public int col;
			
			public pair(int r, int c) {
				row = r;
				col = c;
			}
		}
		
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		for(int n=0; n<N; n++) {
			int F = s.nextInt();
			// -1 = not visited
			// 0 = DNE
			// 1 = visited
			int visited[][] = new int[F][81];
			int distance[][] = new int[F][81];
			int rowLength[] = new int[F];
			for(int f = 0; f<F; f++) {
				int C = s.nextInt();
				rowLength[f] = C+1;
				for(int c = 0; c<=C; c++) {
					visited[f][c] = -1;
				}
			}
			
			int posR = s.nextInt() - 1;
			int posC = s.nextInt();
			
			int endR = s.nextInt() - 1;
			int endC = s.nextInt();
			
			// BFS from pos -> end
			visited[posR][posC] = 1;
			distance[posR][posC] = 0;
			
			ArrayDeque<pair> q = new ArrayDeque<>();
			q.add(new pair(posR, posC));
			
			while(!q.isEmpty()) {
				
				int currR = q.peek().row;
				int currC = q.peek().col;
				q.pop();
	
				// figure out how to fucking use pair class without shitting myself
					
				//get up
				if((currR-1) > -1) { //can go up
					//System.out.println("CurrR-1: "+(currR-1));
					//System.out.println("Rowl: " + (rowLength[currR-1]-1));
					if(visited[currR-1][currC] == 0 && visited[currR-1][rowLength[currR-1]-1] == -1) { //snaps left
						q.add(new pair(currR-1, rowLength[currR-1]-1));
						distance[currR-1][rowLength[currR-1]-1] = distance[currR][currC] + 1;
						visited[currR-1][rowLength[currR-1]-1] = 1;
						
					} else if(visited[currR-1][currC] == -1) { //doesn't snap left
						q.add(new pair(currR-1, currC));
						distance[currR-1][currC] = distance[currR][currC] + 1;
						visited[currR-1][currC] = 1;
					}
				}
				
				//get down
				if((currR+1) < F) { //can go down
					if(visited[currR+1][currC] == 0 && visited[currR+1][rowLength[currR+1]-1] == -1) { //snaps left
						q.add(new pair(currR+1, rowLength[currR+1]-1));
						distance[currR+1][rowLength[currR+1]-1] = distance[currR][currC] + 1;
						visited[currR+1][rowLength[currR+1]-1] = 1;
						
					} else if(visited[currR+1][currC] == -1) { //doesn't snap left
						q.add(new pair(currR+1, currC));
						distance[currR+1][currC] = distance[currR][currC] + 1;
						visited[currR+1][currC] = 1;
					}
				}
				
				//get left
				if((currC-1) > -1 && visited[currR][currC-1] == -1) { //can go left and unvisited
					q.add(new pair(currR, currC-1));
					distance[currR][currC-1] = distance[currR][currC] + 1;
					visited[currR][currC-1] = 1;
						
				} else if ((currC-1) <= -1 && currR > 0 && visited[currR-1][rowLength[currR-1]-1] == -1) { // not at beginning of file
					q.add(new pair(currR-1, rowLength[currR-1]-1));
					distance[currR-1][rowLength[currR-1]-1] = distance[currR][currC] + 1;
					visited[currR-1][rowLength[currR-1]-1] = 1;
				}
				
				
				// get right
				if((currC+1) < rowLength[currR] && visited[currR][currC+1] == -1) { //can go right and unvisited
					q.add(new pair(currR, currC+1));
					distance[currR][currC+1] = distance[currR][currC] + 1;
					visited[currR][currC+1] = 1;
						
				} else if ((currC+1) >= rowLength[currR] && currR != F-1 && visited[currR+1][0] == -1) { // not at beginning of file
					q.add(new pair(currR+1, 0));
					distance[currR+1][0] = distance[currR][currC] + 1;
					visited[currR+1][0] = 1;
				}
				
				if(visited[endR][endC] == 1) {
					break;
				}
			}
			
			
			System.out.println(distance[endR][endC]);
		}
	}
}
