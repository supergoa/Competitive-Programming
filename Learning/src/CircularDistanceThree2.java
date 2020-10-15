import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CircularDistanceThree2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

       //Scanner
       //Scanner s = new Scanner(System.in);
       //int n = s.nextInt();
       double radii[] = new double[N];
       StringTokenizer st; 
       for(int i=0;i<N;i++){
           st = new StringTokenizer(br.readLine());
           double x=Double.parseDouble(st.nextToken());
           double y=Double.parseDouble(st.nextToken());
           double z=x*x + y*y;
           radii[i]=Math.sqrt(z);
       }
       
	    Arrays.sort(radii);
	    int Q = Integer.parseInt(br.readLine());
	    for(int i=0; i<Q; i++) {
	    	int low = 0;
	    	int high = N;
	    	int mid = 0;
	    	double target = Double.parseDouble(br.readLine());
	    	
	    	/*while(high > low) {
		    	mid = (high+low)/2;
		    	if(radii[mid] <= target) {
		    		low = mid + 1;
		    	}
		    	else {
		    		high = mid;
		    	}
	    	}*/
	    	int ans = Arrays.binarySearch(radii, target);
	    	if(ans<0) {
	    		System.out.println(Math.abs(ans)-1);
	    	} else {
	    		System.out.println(Math.abs(ans)+1);
	    	}
    		
	    }	    
	}
}
