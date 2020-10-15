package FirstCourse;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CyberSecurityWar {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new CyberSecurityWar().solve(scan,out);
		scan.close();
		out.close();
	}

	private void solve(Scanner scan, PrintWriter out) {
		int N = scan.nextInt();
		for(int n=0; n<N; n++) {
			
			int S=scan.nextInt();
			int E = scan.nextInt();
			if(n==22) {
				//System.out.println(S+" "+E);
			}
			if(E==0) {
				out.println("YES");
				continue;
			}
			
			HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();
			for(int s=0; s<S; s++) {
				hm.put(s, new ArrayList<>());
			}
			for(int e=0; e<E; e++) {
				int x = scan.nextInt();
				int y = scan.nextInt();
				
				ArrayList<Integer> temp = hm.get(x);
				temp.add(y);
				hm.put(x, temp);
				temp = hm.get(y);
				temp.add(x);
				hm.put(y, temp);
			}
			
			int[] colorVisited = new int[S];
			ArrayDeque<Integer> q = new ArrayDeque<>();
			q.add(0);
			colorVisited[0] = 1;
			int numVisited = 1;
			
			boolean possible = true;
			int multiplier = 1;
			int multiplier2 = 1;
			while(numVisited<S) {
				
				while(!q.isEmpty()) {
					int student = q.poll();
					ArrayList<Integer> temp = hm.get(student);
					for(int s : temp) {
						//System.out.println("Student: "+student+" Enemy: "+s);
						if(colorVisited[s] == 0) {
							colorVisited[s] = colorVisited[student]==1*multiplier*multiplier2?-1*multiplier*multiplier2:1*multiplier*multiplier2;
							q.add(s);
							numVisited++;
						} else {
							if(colorVisited[s] == colorVisited[student]) {
								possible = false;
								break;
							}
						}
					}
					if(!possible) {
						break;
					}
				}
				
				if(!possible) {
					break;
				} else {
					//System.out.println("hello" + numVisited + " " + S);
					for(int i=0; i<colorVisited.length; i++) {
						if(colorVisited[i]==0) {
							numVisited++;
							q.add(i);
							multiplier++;
							multiplier2*=-1;
							colorVisited[i] = 1*multiplier*multiplier2;
							break;
						}
					}
				}
			}
			int count1=0;
			int count2=0;
			
			for(int s=0; s<S; s++) {
				//System.out.println(colorVisited[s]);
				if(colorVisited[s]>=1) {
					count1++;
				}
				if(colorVisited[s]<=-1) {
					//System.out.println("fuck");
					count2++;
					//System.out.println("Count2: "+count2);
				}
			}
			if(possible && count1>=2 && count2>=2) {
				out.println("YES");
			} else {
				out.println("NO");
			}
		}
		
	}
}
