import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;


public class TreasureHunter {
	//public static void main(String[] args) {
     //   new Thread(null, new TreasureHunter(), "lol", (1L<<30)).start();
    //}
	static PrintWriter out;
	public static void main(String[] args) {	
		Scanner in = new Scanner(System.in);
		out = new PrintWriter(System.out);
		new TreasureHunter().solve(in,out);
		out.close();
	}
	
	//HashMap<Integer, HashMap<Integer, Integer>> memo = new HashMap<>();
	int N;
	int D;
	int[] gems;
	int lenght =  30002;
	int[][] memo = new int[500][30002];
	private void solve(Scanner in, PrintWriter out) {
		N = in.nextInt();
		D = in.nextInt();
		gems = new int[30002];
		for(int n=0; n<N; n++) {
			int x = in.nextInt();
			gems[x]++;
		}
		for(int i=0; i<500; i++) Arrays.fill(memo[i], -1);
		int ans = gems[0] + gems[D];
		ans += dp(D, D);
		out.println(ans);
	}
	private int dp(int ind, int lastJump) {
		//System.out.println(ind);
		if(lastJump == 1 && ind+2>=lenght) return 0;
		if(ind+(lastJump-1)>=lenght) return 0;
		//System.out.println(ind);
		
		// DP Step
		//if(memo.containsKey(lastJump)
		//&& memo.get(lastJump).containsKey(ind))
		//return memo.get(lastJump).get(ind);
		
		if(memo[lastJump-D+250][ind] != -1) return memo[lastJump-D+250][ind];
		
		int nextJump1 = lastJump-1;
		int nextJump2 = lastJump+1;
		int nextJump3 = lastJump;
		
		int ans1 = 0, ans2 = 0; int ans3 = 0;
		//out.println(nextJump1 + " " + ind);
		//out.flush();
		if(nextJump1>0 && nextJump1+ind<lenght) ans1 = gems[nextJump1+ind] + dp(ind+nextJump1, nextJump1);
		if(nextJump2+ind<lenght) ans2 = gems[nextJump2 + ind] + dp(ind+nextJump2, nextJump2);
		if(nextJump3+ind<lenght) ans3 = gems[nextJump3 + ind] + dp(ind+nextJump3, nextJump3);
		
		//if(!memo.containsKey(lastJump)) memo.put(lastJump, new HashMap<>());
		
		int maxAns = Math.max(ans1, ans2);
		maxAns = Math.max(maxAns, ans3);
		//memo.get(lastJump).put(ind, maxAns);
		
		return memo[lastJump-D+250][ind] = maxAns;
	}
	// omg ur and idiot, this state isn't unique because
	// your memo is saving entire paths curgems at the end an
	// not a subpath that can be added onto a curpath.
	// you can hit the same state again and get a lower answer returned to you
	// because that answer is from an entire path previously computed, not the segment left to compute in your curpath.
	// you're not building bottom-up
	/*private int dp(int curGems, int ind, int lastJump) {
		System.out.println(ind + " " + curGems);
		if(lastJump == 1 && ind+2>=lenght) return curGems;
		if(ind+(lastJump-1)>=lenght) return curGems;
		
		//System.out.println(ind);
		
		// DP Step
		if(memo.containsKey(ind)
		&& memo.get(ind).containsKey(curGems)
		&& memo.get(ind).get(curGems).containsKey(lastJump))
		return memo.get(ind).get(curGems).get(lastJump);
		
		int nextJump1 = lastJump-1;
		int nextJump2 = lastJump+1;
		
		int ans1 = 0, ans2 = 0;
		if(nextJump1>0 && nextJump1+ind<lenght) ans1 = dp(curGems + gems[nextJump1+ind], ind+nextJump1, nextJump1);
		if(nextJump2+ind<lenght) ans2 = dp(curGems + gems[nextJump2 + ind], ind+nextJump2, nextJump2);
		
		if(!memo.containsKey(ind)) memo.put(ind, new HashMap<>());
		if(!memo.get(ind).containsKey(curGems)) memo.get(ind).put(curGems, new HashMap<>());
		if(ans1>ans2) {
			memo.get(ind).get(curGems).put(lastJump, ans1);
			return ans1;
		} else {
			memo.get(ind).get(curGems).put(lastJump, ans2);
			return ans2;
		}
		
		
	}*/
	/*private void solve(JoltyScanner in, PrintWriter out) {
		int N = in.nextInt();
		int D = in.nextInt();
		int[] gems = new int[N];
		for(int n=0; n<N; n++) {
			gems[n] = in.nextInt();
		}
		long ans1 = 0;
		int lastJumpSize = 0;
		int lastJumpInd = 0;
		int ind=0;
		boolean oneHop = true;
		while(ind<N) {
			ans1 += gems[ind];
			
			if(oneHop) {
				ind++;
				oneHop = false;
				if(ind<N) lastJumpSize = 1;
			} else {
				ind+=2;
				oneHop = true;
				if(ind<N) lastJumpSize = 2;
			}
			if(ind<N) lastJumpInd = ind;
		}
		int lower = lastJumpSize-1;
		int higher = lastJumpSize+1;
		int maxLast = gems[lastJumpInd];
		if(lower>0 && lastJumpInd+lower>=0 && lastJumpInd+lower<N) maxLast=Math.max(gems[lastJumpInd+lower], maxLast);
		if(lastJumpInd+higher>=0 && lastJumpInd+higher<N) maxLast=Math.max(gems[lastJumpInd+higher], maxLast);
		ans1 = (ans1-gems[lastJumpInd] + maxLast);
		
		
		long ans2 = 0;
		lastJumpSize = 0;
		lastJumpInd = 0;
		ind=0;
		oneHop = false;
		while(ind<N) {
			ans1 += gems[ind];	
			if(oneHop) {
				ind++;
				oneHop = false;
				if(ind<N) lastJumpSize = 1;
			} else {
				ind+=2;
				oneHop = true;
				if(ind<N) lastJumpSize = 2;
			}
			if(ind<N) lastJumpInd = ind;
		}
		lower = lastJumpSize-1;
		higher = lastJumpSize+1;
		maxLast = gems[lastJumpInd];
		if(lower>0 && lastJumpInd+lower>=0 && lastJumpInd+lower<N) maxLast=Math.max(gems[lastJumpInd+lower], maxLast);
		if(lastJumpInd+higher>=0 && lastJumpInd+higher<N) maxLast=Math.max(gems[lastJumpInd+higher], maxLast);
		ans1 = (ans1-gems[lastJumpInd] + maxLast);
		
		out.println(Math.max(ans1, ans2));
		
	}*/
	/*static class JoltyScanner {
        public int BS = 1 << 16;
        public char NC = (char) 0;
        public byte[] buf = new byte[BS];
        public int bId = 0;
        public int size = 0;
        public char c = NC;
        public double num = 1;
        public BufferedInputStream in;

        public JoltyScanner(InputStream is) {
            in = new BufferedInputStream(is, BS);
        }

        public JoltyScanner(String s) throws FileNotFoundException {
            in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
        }

        public char nextChar() {
            while (bId == size) {
                try {
                    size = in.read(buf);
                } catch (Exception e) {
                    return NC;
                }
                if (size == -1) return NC;
                bId = 0;
            }
            return (char) buf[bId++];
        }

        public int nextInt() {
            return (int) nextLong();
        }

        public long nextLong() {
            num = 1;
            boolean neg = false;
            if (c == NC) c = nextChar();
            for (; (c < '0' || c > '9'); c = nextChar()) {
                if (c == '-') neg = true;
            }
            long res = 0;
            for (; c >= '0' && c <= '9'; c = nextChar()) {
                res = (res << 3) + (res << 1) + c - '0';
                num *= 10;
            }
            return neg ? -res : res;
        }

    }*/
}
