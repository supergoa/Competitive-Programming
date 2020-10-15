package FirstCourse;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;


public class Gastank {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		new Gastank().solve(scan, out);
		
		scan.close();
		out.close();
	}

	int N;
	double G;
	double M;
	double minCost;
	double[][] dist;
	double[] price;
	DecimalFormat df = new DecimalFormat("0.00");
	private void solve(Scanner scan, PrintWriter out) {
		int dataSet=1;
		while(true) {
			
			N = scan.nextInt();
			G = scan.nextDouble();
			M = scan.nextDouble();
			if(N==0&&G==0&&M==0) break;
			
			out.println("Data set #"+dataSet+":");
			double distanceLeft = G*M;
			dist = new double[N][N];
			price = new double[N];
			for(int n=0;n<N; n++) {
				for(int nn=0;nn<N; nn++) {
					dist[n][nn] = scan.nextDouble();
				}
				price[n] = scan.nextDouble();
			}
			int T = scan.nextInt();
			int start = 0, end = 0;
			for(int t=0; t<T; t++) {
				out.println("\tTrip #"+(t+1)+":");
				start = scan.nextInt()-1;
				end = scan.nextInt()-1;
				minCost=Double.MAX_VALUE;
				dp(start, end, new int[N], 0, distanceLeft);
				out.println("\t\tStarting gas station: "+(start+1));
				out.println("\t\tEnding gas station: "+(end+1));
				if(minCost==Double.MAX_VALUE) {
					out.println("\t\tThe trip is impossible to complete.");
				} else {
					out.println("\t\tThe minimum cost for gas is $"+df.format(minCost)+".");
				}
			}
			dataSet++;
			out.println("");
			out.flush();
		}
		
	}

	double nearestHun(double x) {
		  return ((int) ((x + 0.005) * 100)) / 100.0;
	}
	
	private void dp(int currStation, int end, int[] visited, double cost, double distanceLeft) {
		
		
		if(visited[currStation]>=3) {
			return;
		}
		if(currStation == end) {
			if(cost < minCost) {
				minCost = cost;
			}
			return;
		}
		if(dist[currStation][end]<=distanceLeft) {
			dp(end, end, visited, cost, distanceLeft-dist[currStation][end]);
		} else {
			//no fill up
			for(int i=0; i<dist[currStation].length; i++) {
				if(i!=currStation && dist[currStation][i]<=distanceLeft) {
					++visited[i];
					dp(i, end, visited, cost, distanceLeft-dist[currStation][i]);
					--visited[i];
				}
			}
	
			//fill up
			double priceToFill = nearestHun((price[currStation]*(G-distanceLeft/M)));
			distanceLeft=G*M;
			//System.out.println("pr"+priceToFill);
			
			for(int i=0; i<dist[currStation].length; i++) {
				if(i!=currStation && dist[currStation][i]<=distanceLeft) {
					++visited[i];
					dp(i, end, visited, cost + priceToFill, distanceLeft-dist[currStation][i]);
					--visited[i];
				}
			}
		}
	}
}
