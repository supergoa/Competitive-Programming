import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class blocks {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("blocks.in"));//System.in);
		PrintWriter out = new PrintWriter(new File("blocks.out"));
		new blocks().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		
		int[] ans = new int[26];
		HashMap<Character, Integer> alpha = new HashMap<>();
		for(int i=0; i<26; i++) {
			alpha.put((char) ('a'+i), i);
		}
		for(int n=0; n<N; n++) {
			String top = in.next();
			String bot = in.next();
			HashMap<Character, Integer> countTop = new HashMap<>();
			HashMap<Character, Integer> countBot = new HashMap<>();
			for(Character a : top.toCharArray()) {
				countTop.put(a, countTop.getOrDefault(a,0) + 1);
			}
			for(Character a : bot.toCharArray()) {
				countBot.put(a, countBot.getOrDefault(a,0) + 1);
			}
			for(Character a : countTop.keySet()) {
				ans[alpha.get(a)] += Math.max(countBot.getOrDefault(a, 0), countTop.get(a));
			}
			for(Character a : countBot.keySet()) {
				if(countTop.getOrDefault(a, 0) == 0) {
					ans[alpha.get(a)] += countBot.get(a);
				}
			}
		}
		for(int i=0; i<26; i++) {
			out.println(ans[i]);
		}
	}
}
