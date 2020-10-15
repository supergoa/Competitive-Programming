import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class circles {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new circles().solve(in,out);
		in.close();
		out.close();
	}
	final int INF = 987654321;
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int C = in.nextInt();
			int R = in.nextInt();
			int[] radii = new int[C];
			ArrayList<Integer>[] nodesOnRadii = new ArrayList[C];
			for(int c=0; c<C; c++) {
				radii[c]  =  in.nextInt();
				nodesOnRadii[c] = new ArrayList<>();
			}
			double[][] adj = new double[R*2+2][R*2+2];
			for(int r=0; r<R*2+2; r++) Arrays.fill(adj[r], INF);
			
			HashMap<String, Integer> nodeIDs = new HashMap<>();
			for(int r=0; r<R; r++) {
				int D = in.nextInt()-1;
				int A = in.nextInt();
				
				nodeIDs.putIfAbsent(D+","+A, nodeIDs.size());
				nodeIDs.putIfAbsent((D+1)+","+A, nodeIDs.size());
				
				adj[nodeIDs.get(D+","+A)][nodeIDs.get((D+1)+","+A)] = radii[D+1]-radii[D];
				adj[nodeIDs.get((D+1)+","+A)][nodeIDs.get(D+","+A)] = radii[D+1]-radii[D];
				
				nodesOnRadii[D].add(A);
				nodesOnRadii[D+1].add(A);
			}
			
			int sc = in.nextInt()-1;
			int sa = in.nextInt();
			int fc = in.nextInt()-1;
			int fa = in.nextInt();
			nodeIDs.putIfAbsent(sc+","+sa, nodeIDs.size());
			nodeIDs.putIfAbsent(fc+","+fa, nodeIDs.size());
			nodesOnRadii[sc].add(sa);
			nodesOnRadii[fc].add(fa);
			
			for(int i=0; i<C; i++) {
				for(int j=0; j<nodesOnRadii[i].size(); j++)  {
					for(int k=0; k<nodesOnRadii[i].size(); k++)  {
						if(j==k)  continue;
						int D = i;
						int A1 = nodesOnRadii[i].get(j);
						int A2 = nodesOnRadii[i].get(k);
						double angleDist = dist(radii[i], A1, A2);
						//System.out.println("connection on " + D+ " FROM " + A1 + " TO "  + A2+ " DIST "+ angleDist);
						adj[nodeIDs.get(D+","+A1)][nodeIDs.get(D+","+A2)] = angleDist;
						adj[nodeIDs.get(D+","+A2)][nodeIDs.get(D+","+A1)] = angleDist;
					}
				}
			}
			
			// flloyds
			for (int k = 0; k < R*2+2; ++k) {
			    for (int i = 0; i < R*2+2; ++i) {
			        for (int j = 0; j < R*2+2; ++j) {
			            if (adj[i][k] < INF && adj[k][j] < INF) 
			            	adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]); 
			        }
			    }
			}
			
			out.printf("%.2f\n",adj[nodeIDs.get(sc+","+sa)][nodeIDs.get(fc+","+fa)]);
		}
	}

	private double dist(double r, double a1, double a2) {
		if(a1==a2) return 0;
		double lower = Math.min(a1, a2);
		double higher = Math.max(a1, a2);
		double ret = Math.min((2*Math.PI*r*(higher-lower)/360.0), (2*Math.PI*r*(Math.abs(higher-(360+lower)))/360.0));
		return ret;
	}
}
