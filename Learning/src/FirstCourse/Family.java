package FirstCourse;

import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Family {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new Family().solve(scan, out);
		
		scan.close();
		out.close();
		
	}
	

	private void solve(Scanner scan, PrintWriter out) {
		
		int familyNumber = 1;
		while(true) {
			int N = scan.nextInt();
			if(N==0) break;
			
			HashMap<String, Integer> nameIDs = new HashMap<String, Integer>();
			HashMap<String, HashSet<String>> relationships = new HashMap<>();
			int id=0;
			for(int n=0; n<N; n++) {
				String parent1 = scan.next();
				String parent2 = scan.next();
				String child = scan.next();
				
				if(!nameIDs.containsKey(parent1)) {
					nameIDs.put(parent1, id++);
					relationships.put(parent1, new HashSet<>());
				}
				if(!nameIDs.containsKey(parent2)) {
					nameIDs.put(parent2, id++);
					relationships.put(parent2, new HashSet<>());
				}
				if(!nameIDs.containsKey(child)) {
					nameIDs.put(child, id++);
					relationships.put(child, new HashSet<>());
				}
				
				HashSet<String> temp = relationships.get(parent1);
				temp.add(child);
				relationships.put(parent1, temp);
				
				temp = relationships.get(parent2);
				temp.add(child);
				relationships.put(parent2, temp);
				
				temp = relationships.get(child);
				temp.add(parent1);
				temp.add(parent2);
				relationships.put(child, temp);
	
			}
			
			//BFS
			String start = scan.next();
			String goal = scan.next();
			if(!relationships.containsKey(start) || !relationships.containsKey(start)) {
				out.println("Family #"+familyNumber+": Not related.");
				familyNumber++;	
				continue;
			}
			boolean[] visited = new boolean[id];
			ArrayDeque<String> Q = new ArrayDeque<String>();
			
			visited[nameIDs.get(start)] = true;
			Q.add(start);
			boolean found = false;
			while(!Q.isEmpty()) {
				String curr = Q.poll();
				if(curr.equals(goal)) {
					found = true;
					break;
				}
				HashSet<String> adj = relationships.get(curr);
				for(String name : adj) {
					if(!visited[nameIDs.get(name)]) {
						Q.add(name);
						visited[nameIDs.get(name)] = true;
					}
				}
			}
			
			if(found) {
				out.println("Family #"+familyNumber+": Related.");
			} else {
				out.println("Family #"+familyNumber+": Not related.");
			}
			familyNumber++;	
		}
		
	}
}
