import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Hashing {
	static int[] disjSet;
	static int[] size;
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		BufferedWriter br = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = s.nextInt();
		int M = s.nextInt();
		DSU dsu= new DSU(N);
		for(int m=0; m<M; m++) {
			int x = s.nextInt()-1;
			int y = s.nextInt()-1;
			dsu.union(x,y);
			
			//MORE EFFICIENT WAY (with large input)
			HashMap<Integer, Integer> hm = new HashMap<>();
			for(int i=0; i<N; i++) {
				hm.put(dsu.root(i), hm.getOrDefault(dsu.root(i), 0)+1);
			}
			
			ArrayList<Integer> sizes = new ArrayList<Integer>(hm.values());
			Collections.sort(sizes);
			for(int i=0; i<sizes.size(); i++) {
				br.append(sizes.get(i) + " ");
			}
			
			br.append("\n");
			/*Set<Integer> roots = new HashSet<Integer>();
			
			for(int i=0; i<N; i++) {
				int root = root(i);
				if(!roots.contains(root)) {
					roots.add(root);
				}
			}
			int[] sizes = new int[roots.size()];
			for(int i=0; i<roots.size(); i++) {
				sizes[i] = size[(int) roots.toArray()[i]];
			}
			Arrays.sort(sizes);
			for(int i=0; i<sizes.length; i++) {
				br.append(sizes[i]+" ");
			}
			br.append("\n");
			*/
			
				
			
		}
		br.flush();
		br.close();
		s.close();
	}
}
