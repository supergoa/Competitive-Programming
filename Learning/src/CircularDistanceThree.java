import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CircularDistanceThree {
	public static void main(String[] args) throws NumberFormatException, IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));             // Reading input from STDIN
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    
	    /*class Point{
	    	int x;
	    	int y;
	    	Point(int x, int y) {
	    		this.x = x;
	    		this.y = y;
	    	}
	    }*/
	    
	    //ArrayList<Point> points = new ArrayList<>();
	    int N = Integer.parseInt(br.readLine());
	    
	    int[] xs = new int[N];
	    int[] ys = new int[N];
	    
	    //System.out.println(N);
	    for(int i=0; i<N; i++) {
	    	String line = br.readLine();
	    	//points.add(new Point(Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1])));
	    	xs[i] = Integer.parseInt(line.split(" ")[0]);
	    	ys[i] = Integer.parseInt(line.split(" ")[1]);
	    }
	    long Q = Integer.parseInt(br.readLine());
	    for(int i=0; i<Q; i++) {
	    	boolean[] soln = new boolean[N];
	    	int r = Integer.parseInt(br.readLine());
	    	double mid = r/2.0;
	    	int maxX = Integer.MIN_VALUE;
    		int maxY = Integer.MIN_VALUE;
	    	int count = 0;
	    	for(int j=0; j<xs.length; j++) {
	    		int x = Math.abs(xs[j]);
	    		int y = Math.abs(ys[j]);
	    		
	    		
	    		
	    		if(x < mid && y < mid) {
	    			count++;
	    			continue;
	    		}
	    		if(x<maxX && y<maxY) {
	    			count++;
	    			continue;
	    		}
	    		boolean skip = false;
	    		for(int k = 0; k<j; k++) {
	    			if(soln[k] && Math.abs(x) <= Math.abs(xs[k]) && Math.abs(y) <=Math.abs(ys[k])) {
	    				count++;
	    				skip = true;
	    				break;
	    			}
	    		}
	    		if(skip) {
	    			skip = false;
	    			continue;
	    		} else
	    		
	    		if(x <= r &&  y <= r && (Math.pow(x,2) + Math.pow(y,2) <= Math.pow(r,2))) {
	    				count++;
	    				soln[j] = true;
	    				
	    				if((x+y) > (maxX + maxY)) {
	    					maxX = x;
	    					maxY = y;
	    				}
	    		}
	    	}
	    	StringBuilder sb = new StringBuilder();
	    	sb.append(count);
	    	sb.append("\n");
	    	bw.write(sb.toString());
	    }
	    bw.flush();
	}
}
