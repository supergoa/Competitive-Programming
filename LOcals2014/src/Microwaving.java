import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Microwaving {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Microwaving().solve(in,out);
		in.close();out.close();
	}

	class Time implements Comparable<Time>{
		int moments, closeness;
		String pressed;
		public Time(int a, int b, String c) {
			moments = a;
			closeness = b;
			pressed = c;
		}
		@Override
		public int compareTo(Time o) {
			if(moments == o.moments) {
				return closeness - o.closeness;
			}
			return moments - o.moments;
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			String[] time = in.next().split(":");
			//int minutes = Integer.parseInt(time[0]);
			//int seconds = Integer.parseInt(time[1]);
			int totalSeconds =  Integer.parseInt(time[0])*60 +Integer.parseInt(time[1]);
			int percent = in.nextInt();
			int lower = (int) Math.ceil(totalSeconds - totalSeconds*(percent/100.0));
			int higher = (int) Math.floor(totalSeconds + totalSeconds*(percent/100.0));
			//System.out.println(totalSeconds + " " + lower + " " + higher);
			
			ArrayList<Time> times = new ArrayList<>();
			for(int i=lower; i<=higher; i++) {
				
				int minutes = 0;
				int seconds = i;
				while(seconds > 99) {
					minutes++;
					seconds -= 60;
				}
				do  {
					int moments = 1;
					
					String curTime;
					String toAdd = "";
					if(seconds <= 9) toAdd = "0";
					if(minutes==0) curTime = "" + toAdd + seconds;
					else curTime = minutes + "" + toAdd + seconds;
					
					for(int t=1; t<curTime.length(); t++) {
						if(curTime.charAt(t)==curTime.charAt(t-1)) {
							moments += 1;
						} else {
							moments += 2;
						}
					}
					int closeness = Math.abs(totalSeconds-i);
					//System.out.println("i: " + i + " " + moments + " " + closeness + " " + curTime);
					times.add(new Time(moments, closeness, curTime));
					minutes++;
					seconds -= 60;
				} while(seconds >= 0);
			}
			Collections.sort(times);
			for(Time t:times) {
				//out.println(t.moments + " " + t.closeness + " " + t.pressed);
			}
			out.println("Case #"+(n+1)+": "+times.get(0).pressed);
		}
		
	}
}
