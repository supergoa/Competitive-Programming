import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
// Prime Sieve
class Primes {
    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());                // Reading input from STDIN
        
        boolean[] isPrime = new boolean[N+1];
        for(int i=0; i < N+1; i++) {
            isPrime[i] = true;
        }
        for(int i = 2; i*i < N; i++) {
            if(isPrime[i]) {
                for(int j=i*i; j <= N; j+=i) {
                    isPrime[j] = false;
                }
            }
        }
        int count = 0;
        for(int i=2; i < N; i++) {
            if(isPrime[i]) {
                count++;
            }
        }
        System.out.println(count);
        HashMap<Integer, Integer> hm = new HashMap<>();
        //hm.getOrDefault(arg0, arg1)

    }
}
