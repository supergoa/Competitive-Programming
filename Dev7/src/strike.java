import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeMap;

public class strike {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new strike().solve(in,out);
		in.close();
		out.close();
	}
	
	class Point1 implements Comparable<Point1>{
		int id;
		double x,y;
		double dist;
		public Point1(double a, double b, int c) {
			x = a;
			y = b;
			dist = Math.sqrt(Math.abs(x-x1)*Math.abs(x-x1) + Math.abs(y-y1)*Math.abs(y-y1));
			id = c;
		}
		@Override
		public int compareTo(Point1 o) {
		
			return Double.compare(dist, o.dist);
		}
	}
	class Point2 implements Comparable<Point2>{
		int id;
		double x,y;
		double dist;
		public Point2(double a, double b, int c) {
			x = a;
			y = b;
			dist = Double.parseDouble(String.format("%.8f", Math.sqrt(Math.abs(x-x2)*Math.abs(x-x2) + Math.abs(y-y2)*Math.abs(y-y2))));
			id = c;
		}
		@Override
		public int compareTo(Point2 o) {
			
			return Double.compare(dist, o.dist);
		}
	}

	final double PI = 3.141;
	double x1;
	double y1;
	double x2;
	double y2;
	private void solve(Scanner in, PrintWriter out) {
		int counter = 1;
		while(true) {
			int N = in.nextInt();
			if(N==0) break;
			x1 = in.nextDouble();
			y1 = in.nextDouble();
			x2 = in.nextDouble();
			y2 = in.nextDouble();
			double T = in.nextDouble();
			Point1[] p1s = new Point1[N];
			Point2[] p2s = new Point2[N];
			//double[] xs = new double[N];
			//double[] ys = new double[N];
			for(int n=0; n<N; n++) {
				double x = in.nextDouble();
				double y = in.nextDouble();
				p1s[n] =  new Point1(x,y,n);
				p2s[n] =  new Point2(x,y,n);
			}
			Arrays.sort(p1s);
			Arrays.sort(p2s);
			TreeMap<Double, HashSet<Integer>> t1 = new TreeMap<>();
			TreeMap<Double, HashSet<Integer>> t2 = new TreeMap<>();
		
			for(int n=0; n<N; n++) {
				double dist1 = p1s[n].dist;
				Double t1AddKey = t1.floorKey(dist1);
				HashSet<Integer> num = new HashSet<>();
				if(t1AddKey!=null) num = (HashSet<Integer>) t1.get(t1AddKey).clone();
				num.add(p1s[n].id);
				//System.out.println(dist1 + " " + num);
				t1.put(dist1, (HashSet<Integer>) num.clone());
				//System.out.println(dist1 + " " + num);
				
				double dist2 = p2s[n].dist;
				Double t2AddKey = t2.floorKey(dist2);
				HashSet<Integer> num2 = new HashSet<>();
				if(t2AddKey!=null) num2 = (HashSet<Integer>) t2.get(t2AddKey).clone();
				num2.add(p2s[n].id);
				t2.put(dist2, (HashSet<Integer>) num2.clone());
				//System.out.println(dist2 + " " + num2);
				//System.out.println();
				//System.out.println();
				
			}
			
			//double area = 0;
			//double area2 = 0;
			int best = 987654321;
			//boolean done = false;
			for(double e=0; e<=T; e+=.005) {
				double r = Math.sqrt(e/PI);
				//area2 = T-area;
				//System.out.println(area + " " + area2 + " " +(area+area2));
				double r2 = Math.sqrt((T-e)/PI);
				int count = N;
				Double key1 = 0.0;
				key1 = t1.floorKey(r);
				HashSet<Integer> found = new HashSet<>();
				//System.out.println(key1+ " "+ t1.lowerKey(r));
				if(key1!=null) found.addAll(t1.get(key1));
				
				Double key2 = 0.0;
				key2 = t2.floorKey(r2);
				
				if(key2!=null) found.addAll(t2.get(key2));
				//if(found.size()!=1) System.out.println("asjkdhfjhfd");
				
				//if(found.size()==5 && !done) {System.out.println(r + " " + r2 + " " + found + " " + key1 + " " + t1.get(key1)+" "+ t2.get(key2));done=true;}
				//System.out.println(found.size());
				best = Math.min(best, N-found.size());
			}
			System.out.println((counter++) + ". "+(best));
		}
		
	}
}
