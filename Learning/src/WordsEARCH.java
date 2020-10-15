import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WordsEARCH {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		for (int i = 0; i < N; i++) {
			int R = s.nextInt();
			int C = s.nextInt();
			char arr[][] = new char[R][C];
			for (int r = 0; r < R; r++) {
				String line = s.next();
				for (int c = 0; c < C; c++) {
					arr[r][c] = line.charAt(c);
				}
			}
			//precomp
			HashMap<String, ArrayList<String>> hm = new HashMap<>();
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					StringBuilder sbp = new StringBuilder();
					StringBuilder sbn = new StringBuilder();
					ArrayList<String> posWords = new ArrayList<>();
					
					//down & up
					int index = r;
					for (int a = 0; a < 100; a++) {
						if(index == -1) {
							index = R-1;
						}
						sbp.append(arr[(r+a)%R][c]);
						sbn.append(arr[index][c]);
						index--;
					}
					posWords.add(sbp.toString());
					posWords.add(sbn.toString());
					
					sbp = new StringBuilder();
					sbn = new StringBuilder();
					
					//right & left
					index = c;
					for (int a = 0; a < 100; a++) {
						if(index == -1) {
							index = C-1;
						}
						sbp.append(arr[r][(c+a)%C]);
						sbn.append(arr[r][index]);
						index--;
					}
					posWords.add(sbp.toString());
					posWords.add(sbn.toString());

					hm.put(new String(r+","+c), posWords);
				}
			}
			/*for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					for(int a=0; a<4; a++) {
						System.out.println(hm.get(""+r+c).get(a));
					}
				}
			}*/
			//System.out.println(hm.get("211"));
			System.out.println("Word search puzzle #"+(i+1)+":");
			int Q = s.nextInt();
			for (int q = 0; q < Q; q++) {
				String word = s.next();
				for(int r=0; r<R; r++) {
					for(int c=0; c<C; c++) {
						for(int a=0; a<4; a++) {
							if(hm.get(r+","+c).get(a).startsWith(word)) {
								if (a==0) {
									System.out.println("D " + (r+1) + " " + (c+1) + " " + word);
								}else if(a==1) {
									System.out.println("U " + (r+1) + " " + (c+1) + " " + word);
								} else if(a==2) {
									System.out.println("R " + (r+1) + " " + (c+1) + " " + word);
								}else if (a==3) {
									System.out.println("L " + (r+1) + " " + (c+1) + " " + word);
								}
							}
						}
					}
				}
			}
			System.out.println();
		}
		s.close();

	}
}
