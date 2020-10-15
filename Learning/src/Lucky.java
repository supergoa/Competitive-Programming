
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class Lucky {
    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
        	HashMap<Integer, Integer> freq = new HashMap<>();
            int min = Integer.MAX_VALUE;
            int N = Integer.parseInt(br.readLine());
            String X[] = br.readLine().split(" ");
            for(int n = 0; n<N; n++) {
                int x = Integer.parseInt(X[n]);
                if(n==0) {
                    min = x;
                }
                if(x < min) {
                    min = x;
                }
                freq.put(x, freq.getOrDefault(x,0)+1);
            }
            if(freq.get(min)%2 == 0) {
                System.out.println("Unlucky");
            } else {
                System.out.println("Lucky");
            }
        }

    }
}
