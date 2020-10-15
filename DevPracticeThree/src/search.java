import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class search {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new search().solve(in,out);
		in.close();
		out.close();
	}

	private void solve(Scanner in, PrintWriter out) {
		int counter = 1;
		while(true) {
			int n = in.nextInt();
			if(n==0) break;
			String[] words = new String[n];
			for(int nn=0;nn<n;nn++) {
				words[nn] = in.next();
			}
			int R = in.nextInt();
			int C = in.nextInt();
			String[] grid = new String[R];
			for(int r=0; r<R; r++) {
				grid[r] = in.next();
			}
			ArrayList<String> notFound = new ArrayList<>();
	   yo : for(String word : words) {
				for(int r=0; r<R; r++) {
					for(int c=0; c<C; c++) {
						if(grid[r].charAt(c) == word.charAt(0)) {
							for(int i=-1; i<2; i++) {
								for(int j=-1; j<2; j++) {
									if(i==0 && j==0) continue;
									StringBuilder s = new StringBuilder();
									int startR = r;
									int startC = c;
									for(int k=0; k<word.length(); k++) {
										if(startR<0 || startC<0 || startR>=R || startC >=C) break;
										s.append(grid[startR].charAt(startC));
										startR+=i;
										startC+=j;
									}
									if(word.equals(s.toString())) continue yo;
								}
							}					
						}
					}
				}
				notFound.add(word);		
			}
			out.println("Puzzle number "+(counter++)+":");
			if(notFound.size()!=0) {
				for(String s : notFound) {
					out.println(s);
				}
			} else {
				out.println("ALL WORDS FOUND");
			}
			out.println();
		}
	}
}
