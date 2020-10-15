import java.io.PrintWriter;
import java.util.Scanner;

public class rearrange {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new rearrange().solve(in, out);
		out.close();
	}

	class Trie {
		int below = 0;
		boolean isWord = false;
		Trie[] t = new Trie[26];
		public Trie() {}
		public Trie(int b, boolean i) {
			below = b;
			isWord = i;
		}
	}
	
	int[] order;
	private void solve(Scanner in, PrintWriter out) {
		int T = in.nextInt();
		order = new int[26];
		for(int t=0; t<T; t++) {
			out.println("Gift Exchange #"+(t+1)+":");
			Trie root = new Trie();
			Trie cur = root;
			
			for(int i=0; i<26; i++) {
				order[i] = i;
			}
			int N = in.nextInt();
			int Q = in.nextInt();
			
			for(int n=0; n<N; n++) {
				String s = in.next();
				cur = root;
				for(int i=0; i<s.length(); i++) {
					if(cur.t[s.charAt(i)-'a'] == null && i!=s.length()-1) cur.t[s.charAt(i)-'a'] = new Trie(0, false);
					if(cur.t[s.charAt(i)-'a'] == null && i==s.length()-1) cur.t[s.charAt(i)-'a'] = new Trie(0, true);
					cur.below++;
					cur = cur.t[s.charAt(i)-'a'];
				}
				cur.isWord = true;
				cur.below++;
			}

			for(int q=0; q<Q; q++) {
				if(in.nextInt()==1) {
					int a = in.next().charAt(0)-'a';
					int b = in.next().charAt(0)-'a';
					swap(a,b);
				} else {
					int K = in.nextInt();
					cur = root;
					StringBuilder ans = new StringBuilder("");
					while(K>0) {
						if(cur.isWord) K-=1;
						for(Integer c : order) {
							if(cur.t[c]==null) continue;
							if(cur.t[c]!=null && cur.t[c].below >= K) {
								ans.append((char)('a' + c));
								if(K==1 && cur.t[c].isWord) K=0;
								cur=cur.t[c];
								break;
							}
							else if(cur.t[c]!=null && cur.t[c].below < K) {
								K-=cur.t[c].below;
							}
						}
					}
					out.println(ans);
				}
			}
			out.println();
		}
	}
	void swap(int a, int b) {
        int first = 0, second = 0;
        for (int i = 0; i < 26; i++) {
           if (order[i] == a) first = i;
           else if (order[i] == b) second = i;
        }
        order[first] = order[second] ^ order[first] ^ (order[second] = order[first]);
    }
}