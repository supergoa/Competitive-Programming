import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class space {
	
	static ArrayList<Long> facts;
	static HashMap<Long, Long> exps;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int count = 1;
		while(true) {
			int N = in.nextInt();
			int M = in.nextInt();
			if(N==0 && M==0) break;
			int Z = 0;
			
			factorize(N);
			ArrayList<Long> factsN = facts;
			HashMap<Long, Long> expsN = exps;
			
			factorize(M);
			ArrayList<Long> factsM = facts;
			HashMap<Long, Long> expsM = exps;
			
			int ans = 0;
			for(int i=0, j=0; i<factsN.size() || j<factsM.size();) {
				boolean useN = i<factsN.size();
				boolean useM = j<factsM.size();
				
				long fact1=Long.MAX_VALUE, fact2=Long.MAX_VALUE;
				if(useN) fact1 = factsN.get(i);
				if(useM) fact2 = factsM.get(j);
				if(!useN || fact2 < fact1) {
					ans += expsM.get(fact2);
					j++;
					Z++;
					continue;
				}
				if(!useM || fact1 < fact2) {
					ans += expsN.get(fact1);
					i++;
					Z++;
					continue;
				}
				if(fact1==fact2) {
					ans += Math.abs(expsN.get(fact1)-expsM.get(fact2));
					Z++;
					i++;
					j++;
					continue;
				}
			}
			out.println((count++)+". "+Z+":"+ans);
			
		}
		in.close();
		out.close();
	}

	private static void factorize(long l) {
		facts = new ArrayList<>();
		exps = new HashMap<>();
		for(long i=2; i*i<=l; i++) {
			long exp = 0;
			while(l%i==0) {
				l/=i;
				exp++;
			}
			if(exp>0) {
				facts.add(i);
				exps.put(i, exp);
			}
		}
		if(l != 1) {
			facts.add(l);
			exps.put(l, 1L);
		}
		
	}

	private static long LCM(long a, long b) {
		return (a*b)/GCD(a,b);
	}

	private static long GCD(long A, long B) {
		if(B==0)
	        return A;
	    else
	        return GCD(B, A % B);
	}
}
