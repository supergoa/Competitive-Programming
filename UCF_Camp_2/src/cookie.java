import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class cookie {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		new cookie().solve(in);
		in.close();
	}

	HashMap<Integer, ArrayDeque<Integer>> hm;
	int nums;
	int[] newToOrig;
	TreeSet<Integer> ts = new TreeSet<>();
	private void solve(Scanner in) {
		int[] input = new int[600001];
		int indx=0;
		hm = new HashMap<>();
		nums=0;
		while(in.hasNext()) {
			String c = in.next();
			int intVal = -1;
			if(c.charAt(0)=='@') break;
			if(c.charAt(0)!='#') {
				nums++; 
				intVal = Integer.parseInt(c);
				input[indx++] = intVal;
			} else {
				input[indx++] = '#';
			}
			//System.out.println((int)c);
			//hm.put(hm.si, arg1)
		}
		newToOrig = new int[nums];
		coor(input, indx);
		
		Integer median = -1;
		int below = 0;
		int above = 0;
		//System.out.println("here");
		//TreeMap<Integer, Integer> tm = new TreeMap<>();
		//tm.get
		for(int i=0; i<indx; i++) {
			if(input[i]=='#') {
				System.out.println(newToOrig[median]);
				int oldMedian = median;
				if(above==0 && below==0) {
					median = -1;
				} else if(above >= below) {
					median = ts.higher(oldMedian);
					above--;
				} else if(below > above) {
					median = ts.lower(oldMedian);
					below--;
				}
				ts.remove(oldMedian);
			} else {
				int parse = Integer.parseInt(input[i]+"");
				int num = hm.get(parse).poll();
				ts.add(num);
				
				if(median==-1) {
					median = num;
				} else if(num > median) {
					above++;
				} else {
					below++;
				}
				
				
				if(above > below) {
					median = ts.higher(median);
					above--;
					below++;
				} else if(below > above+1) {
					median = ts.lower(median);
					below--;
					above++;
				}
				//System.out.println("~"+newToOrig[median]+"~");
			}
		}
	}
	private void coor(int[] input, int size) {
		int[] arr = new int[nums];
		int indx = 0;
		for(int i=0; i<size; i++) {
			if(input[i]=='#') continue;
			arr[indx++] = input[i];
		}
		Arrays.sort(arr);
		int unique=0;
		for(int i=0; i<arr.length; i++) {
			if(!hm.containsKey(arr[i])) hm.put(arr[i], new ArrayDeque<>());
			hm.get(arr[i]).add(unique);
			//System.out.println(arr[i] + " " + unique);
			//hm.put(unique, arr[i]);
			newToOrig[unique++] = arr[i];
		}
		//System.out.println("done");
	}
}