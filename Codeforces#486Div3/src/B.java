import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class B {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new B().solve(in, out);
		in.close();
		out.close();
	}

	void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		String[] strs = new String[N];
		ArrayList<String> strsLeft = new ArrayList<>();
		ArrayList<String> ans = new ArrayList<String>();
		for(int n=0; n<N; n++) {
			strs[n] = in.next();
			strsLeft.add(strs[n]);
		}
		int count = 0;
		while(!strsLeft.isEmpty()) {
			for(int i=0; i<strsLeft.size(); i++) {
				boolean allGood = true;
				for(int j=0; j<strsLeft.size(); j++) {
					if(i!=j) {
						if(!strsLeft.get(i).contains(strsLeft.get(j))) {
							allGood = false;
							break;
						}
					}
				}
				if(allGood) {
					ans.add(strsLeft.remove(i));
					i=0;
				}
			}
			count++;
			if(count==1000) {
				break;
			}
		}
		if(count==1000) {
			out.println("NO");
			return;
		}
		out.println("YES");
		Collections.reverse(ans);
		for(String a:ans) {
			out.println(a);
		}
	}
}
