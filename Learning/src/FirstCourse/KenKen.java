package FirstCourse;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class KenKen {
	static PrintWriter out;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		out = new PrintWriter(System.out);
		new KenKen().solve(scan);
		scan.close();
		out.close();
	}
	class Point {
		int row;
		int col;
		public Point(int x, int y) {
			row = x;
			col = y;
		}
	}
	class Operation {
		int num;
		char sign;
		public Operation(int x, char y) {
			num = x;
			sign = y;
		}
	}
	
	int N, G;
	HashMap<Character, ArrayList<Point>> adj;
	HashMap<Character, Operation> operations;
	boolean[][] colsValues;
	boolean[][] rowsValues;
	char[][] grid;
	int[][] soln;
	HashMap<Character, Integer> groupPositions;
	private void solve(Scanner scan) {
		
		for(int t=0;t>=0;t++) {
			N = scan.nextInt();
			if(N==0) {
				break;
			}
			G = scan.nextInt();
			out.println("KenKen Puzzle #"+(t+1)+":");
			
			colsValues = new boolean[N][N];
			rowsValues = new boolean[N][N];
			adj = new HashMap<>();
			grid = new char[N][N];
			
			for(int n=0; n<N; n++) {
				String line = scan.next();
				for(int i=0; i<N; i++) {
					char a = line.charAt(i);
					grid[n][i] = a;
					
					if(!adj.containsKey(a)) {
						adj.put(a, new ArrayList<>());
					}
					adj.get(a).add(new Point(n,i));
				}
			}
			operations = new HashMap<>();
			groupPositions = new HashMap<>();
			int counter=0;
			for(int g=0; g<G; g++) {
				char a = (char) scan.next().charAt(0);
				int num = scan.nextInt();
				char sign = scan.next().charAt(0);
				operations.put(a, new Operation(num,sign));
				if(!groupPositions.containsKey(a)) {
					groupPositions.put(a, counter);
					counter++;
				}
			}
			
			
			soln = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					Operation op = operations.get(grid[i][j]);
					if(op.sign == '.') {
						soln[i][j] = op.num;
						colsValues[j][op.num-1] = true;
						rowsValues[i][op.num-1] = true;
					}
				}
			}
			//System.out.println("okokokook");
			backtrack(0,0,new boolean[N],new int[G]);	
			//System.out.println("wtf");
		}
		
	}
	private boolean backtrack(int row, int col, boolean[] valUsed, int groupUsed[]) {
		//System.out.println("N: "+N);
		if(col==N) {
			//System.out.println("SOLUTION");
			printSolution();
			return true;
		}
		
		if(soln[row][col] != 0) {
			if(row!=N-1) {
				if(backtrack(row+1,col,valUsed,groupUsed)) {
					return true;
				}
			} else {
				if(backtrack(0,col+1,valUsed,groupUsed)) {
					return true;
				}
			}
			return false;
		}
		
		char group = grid[row][col];
		int groupPos = groupPositions.get(group);
		
		for(int val=0; val<N; val++) {
			//System.out.println("asdlfjkasfd");
			if(!colsValues[col][val] && !rowsValues[row][val] && !groupFull(group, groupPos, groupUsed) && !conflict(row, col, val+1, group, groupPos, groupUsed)) {
				//System.out.println("madeit");
				soln[row][col] = val+1;
				colsValues[col][val] = true;
				rowsValues[row][val] = true;
				groupUsed[groupPos] += 1;
				
				if(row!=N-1) {
					if(backtrack(row+1,col,valUsed,groupUsed)) {
						return true;
					}
				} else {
					if(backtrack(0,col+1,valUsed,groupUsed)) {
						return true;
					}
				}
				soln[row][col] = 0;
				colsValues[col][val] = false;
				rowsValues[row][val] = false;
				groupUsed[groupPos] -= 1;
			}
		}
		return false;
	}
	private boolean groupFull(char group, int groupPos, int groupUsed[]) {
		//System.out.println("group: "+group);
		//System.out.println("groupPos: "+groupPos);
		//System.out.println(t+1);
		boolean flag = (groupUsed[groupPos]<adj.get(group).size());
		//System.out.println("groupfull returned"+!flag);
		return !flag;
	}
	private boolean groupOneFromFull(char group, int groupPos, int groupUsed[]) {
		return (groupUsed[groupPos]==adj.get(group).size()-1);
	}
	private boolean conflict(int row, int col, int val, char group, int groupPos, int groupUsed[]) {
		char sign = operations.get(group).sign;
		int goal = operations.get(group).num;
		
		if(groupOneFromFull(group, groupPos, groupUsed) && sign =='.') {
			if(val!=goal) {
				//System.out.println("Conflict returned true1");
				return true;
			}
			//System.out.println("Conflict returned false2");
			return false;
		}
		
		if(groupOneFromFull(group, groupPos, groupUsed)) {
			
			ArrayList<Point> points = adj.get(group);
			//TreeSet<Integer> pq = new TreeSet<>();
			
			//ArrayList<Integer> values = new ArrayList<>();
			int[] values = new int[points.size()];
			for(int point=0; point<points.size(); point++) {
				int r=points.get(point).row;
				int c=points.get(point).col;
				int v = soln[r][c];
				if(v==0) {
					values[point] =val;
				} else {
					values[point] =v;
				}
			}
			
			Arrays.sort(values);
			
			double total = 0;
			boolean first = true;
			//int lower = Integer.MAX_VALUE;
			for(int v=values.length-1; v>=0; v--) {
				if(values[v]!=0) {
					if(first) {
						total = values[v];
						first = false;
					} else {
						if(sign == '*') {
							total*= values[v];
						}
						if(sign == '/') {
							total/= values[v];
						}
						if(sign == '+') {
							total+= values[v];
						}
						if(sign == '-') {
							total-= values[v];
						}
					}
				}
			}
			if(Math.abs(total-goal) > .000001) {
				//System.out.println("Conflict returned true");
				return true;
				
			}
		}
		//System.out.println("Conflict returned false");
		return false;
	}
	private void printSolution() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				out.print(soln[i][j]);
			}
			out.println();
		}
		out.println();
		out.flush();
	}
}
