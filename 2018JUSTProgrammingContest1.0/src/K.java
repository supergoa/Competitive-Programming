import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class K {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new K().solve(in,out);
		out.close();
	}

	class Student {
		int[] problems;
		int id;
		int firstAttemptSolves = 0;
		public Student(int id, int N) {
			this.id=id;
			problems = new int[N];
		}
	}
	class Submission implements Comparable<Submission> {
		int minute;
		int second;
		int problemID;
		int student;
		boolean solved;
		public Submission(int x, int y, int z, String t) {
			problemID = x;
			student = y;
			solved = (z==1)?true:false;
			minute = Integer.parseInt(t.split(":")[0]);
			second = Integer.parseInt(t.split(":")[1]);
		}
		
		
		@Override
		public int compareTo(Submission o) {
			if(this.minute == o.minute) {
				return Integer.compare(this.second, o.second);
			}
			return Integer.compare(this.minute, o.minute);
		}
		
	}
	private void solve(InputReader in, PrintWriter out) {
		int T = in.nextInt();
		for(int t=0; t<T; t++) {
			int N = in.nextInt();
			int M = in.nextInt();
			int K = in.nextInt();
			
			int[] firstSolved = new int[N];
			Arrays.fill(firstSolved, -1);
			
			int xpa = -1;
			int sga = -1;
			int spa = -1;
			int rpa = -1;
			int mostFirstSolves = 0;
			int mostIncorrectSubmissionsBeforeSolve = 0;
			
			Student[] students = new Student[M];
			for(int m=0; m<M; m++) {
				students[m] = new Student(m, N);
			}
			PriorityQueue<Submission> subs = new PriorityQueue<>();
			for(int k=0; k<K; k++) {
				subs.add(new Submission(in.nextInt()-1, in.nextInt()-1, in.nextInt(), in.next()));
			}
			while(!subs.isEmpty()) {
				Submission s = subs.poll();
				//System.out.println(s.minute+ ":"+s.second);
				if(s.solved) {
					if(xpa==-1) {
						xpa = s.student;
					}
					if(students[s.student].problems[s.problemID]==0) {
						students[s.student].firstAttemptSolves++;
						if(students[s.student].firstAttemptSolves >= mostFirstSolves) {
							if(students[s.student].firstAttemptSolves == mostFirstSolves) {
								if(s.student < spa || spa==-1) {
									spa = s.student;
									mostFirstSolves = students[s.student].firstAttemptSolves;
								}
							} else {
								spa = s.student;
								mostFirstSolves = students[s.student].firstAttemptSolves;
							}
						}
					}
					if(students[s.student].problems[s.problemID] >= mostIncorrectSubmissionsBeforeSolve) {
						if(students[s.student].problems[s.problemID] == mostIncorrectSubmissionsBeforeSolve) {
							if(s.student < rpa || rpa==-1) {
								rpa = s.student;
								mostIncorrectSubmissionsBeforeSolve = students[s.student].problems[s.problemID];
							}
						} else {
							rpa = s.student;
							mostIncorrectSubmissionsBeforeSolve = students[s.student].problems[s.problemID];
						}
					}
					if(firstSolved[s.problemID]==-1) {
						firstSolved[s.problemID] = s.student;
					}
					sga = s.student;
					
				} else {
					students[s.student].problems[s.problemID]++;
				}
			}
			if(spa==-1) spa=0;
			if(rpa==-1) rpa=0;
			if(sga==-1) sga=0;
			if(xpa==-1) xpa=0;
			for(int a: firstSolved) {
				out.print((a==-1?-1:(a+1)) + " ");
			}
			out.println();
			out.println((xpa+1) + " " + (sga+1) + " " + (spa+1) + " " + (rpa+1));
			
		}
		
	}

	public static class InputReader {
	    public BufferedReader reader;
	    public StringTokenizer tokenizer;

	    public InputReader(InputStream stream) {
	        reader = new BufferedReader(new InputStreamReader(stream), 32768);
	        tokenizer = null;
	    }

	    public String next() {
	        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
	            try {
	                tokenizer = new StringTokenizer(reader.readLine());
	            } catch (IOException e) {
	                throw new RuntimeException(e);
	            }
	        }
	        return tokenizer.nextToken();
	    }

	    public BigInteger nextBigInt() {
	        return new BigInteger(next());
	    }

	    public int nextInt() {
	        return Integer.parseInt(next());
	    }

	    public double nextDouble() {
	        return Double.parseDouble(next());
	    }

	    public long nextLong() {
	        return Long.parseLong(next());
	    }

	}
}
