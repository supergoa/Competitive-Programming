import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class knockout {
	static PrintWriter out;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		//out = new PrintWriter(new File("C://Users/Nick/Desktop/knockout.txt"));
		new knockout().solve(in,out);
		in.close();
		//out.close();
		
	}

	double[][][] bestExpectedScore;
	ArrayList<Integer>[] digitsRemovedMax;
	ArrayList<Integer>[] digitsRemovedMin;
	private void solve(Scanner in, PrintWriter out) {
		String digits = in.next();
		int curMask = 0;
		for(int i=0; i<digits.length(); i++) {
			int num = Integer.parseInt(digits.substring(i, i+1));
			curMask |= (1<<num);
		}
		
		bestExpectedScore = new double[2][(1<<10)+1][37];
		for(int i=0; i<2; i++) {
			for(int j=0; j<(1<<10)+1; j++) {
				Arrays.fill(bestExpectedScore[i][j], -1);
			}
		}
		
		digitsRemovedMax = new ArrayList[(1<<10)+1];
		for(int i=0; i<(1<<10)+1; i++) digitsRemovedMax[i] = new ArrayList<>();
		digitsRemovedMin = new ArrayList[(1<<10)+1];
		for(int i=0; i<(1<<10)+1; i++) digitsRemovedMin[i] = new ArrayList<>();
		
		int a = in.nextInt();
		int b = in.nextInt();
		//out.println(curMask);
		
		double ansMax = dp(curMask, a, b, true);
		double ansMin = dp(curMask, a, b, false);
		
		String minStr = "";
		String maxStr = "";
		for(int i=0; i<digitsRemovedMin[curMask].size(); i++) minStr += digitsRemovedMin[curMask].get(i);
		for(int i=0; i<digitsRemovedMax[curMask].size(); i++) maxStr += digitsRemovedMax[curMask].get(i);
		if(digitsRemovedMin[curMask].size()==0) minStr = "-1";
		if(digitsRemovedMax[curMask].size()==0) maxStr = "-1";
		
		System.out.printf(minStr + " %.5f\n", ansMin);
		System.out.printf(maxStr + " %.5f\n", ansMax);
		
	}
	private double dp(int curMask, int a, int b, boolean maximize) {
		if(curMask==0) return 0;
		int sum = a+b;
		
		double max = Integer.MIN_VALUE;
		double min = Integer.MAX_VALUE;
		if(bestExpectedScore[maximize?1:0][curMask][sum] != -1) return bestExpectedScore[maximize?1:0][curMask][sum];
		boolean atLeastOnce = false;
		for(int i=curMask; i>0; i=(curMask & (i-1))) {
			
			int tempSum = 0;
			int tempMask = 0;
			
			for(int j=1; j<10; j++) {
				if((i & (1<<j)) != 0) {
					tempSum += j;
					tempMask += (1<<j);
				}
			}
			
			double ans = 0;
			if(tempSum == sum) {
				atLeastOnce = true;
				for(int x=1; x<=6; x++) {
					for(int y=1; y<=6; y++) {
						ans += 1.0/36.0*dp(curMask-tempMask, x, y, maximize);
					}
				}
			}
			
			if(ans > max && atLeastOnce && maximize && tempSum == sum) {
				digitsRemovedMax[curMask].clear();
				for(int j=1; j<10; j++) {
					if((tempMask & (1<<j)) != 0) {
						digitsRemovedMax[curMask].add(j);
					}
				}
				max = ans;
			}
			if(ans < min && atLeastOnce && !maximize && tempSum == sum) {
				//System.out.println("in set min " + ansMax);
				digitsRemovedMin[curMask].clear();
				for(int j=1; j<10; j++) {
					if((tempMask & (1<<j)) != 0) {
						digitsRemovedMin[curMask].add(j);
					}
				}
				min = ans;
			}	
		}
		if(!atLeastOnce) {
			String ret = "";
			for(int j=1; j<10; j++) {
				if((curMask & (1<<j)) != 0) {
					ret += j;
				}
			}
			return Double.parseDouble(ret);
		}
		
		//bestExpectedScore[0][curMask] = min;
		//bestExpectedScore[1][curMask] = max;
		if(maximize) return bestExpectedScore[1][curMask][sum]=max;
		else return bestExpectedScore[0][curMask][sum]=min;
		
		
	}
}
