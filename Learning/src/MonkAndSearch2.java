import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeMap;

public class MonkAndSearch2 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
		
		String lineArr[] = br.readLine().split(" ");
		for(int i=0; i<lineArr.length; i++) {
			int a = Integer.parseInt(lineArr[i]);
			tm.put(a, tm.getOrDefault(a, 0)+1);
		}
		for(Integer i=tm.higherKey(tm.firstKey()); i!=null; i=tm.higherKey(i)) {
			Integer prevKey = tm.lowerKey(i);
			Integer valSoFar = tm.get(prevKey);
			tm.put(i, valSoFar+tm.get(i));
			//tm.put(i, tm.get(i) + tm.get(tm.lowerKey(i)));
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int Q = Integer.parseInt(br.readLine());
		for(int i=0; i<Q; i++) {
			String lineArr1[] = br.readLine().split(" ");
			int flag = Integer.parseInt(lineArr1[0]);
			int x = Integer.parseInt(lineArr1[1]);
			
			if(tm.lastKey()<x) {
				bw.write(0+"\n");
			}
			else if(tm.firstKey()>x) {
				bw.write(N+"\n");
			}
			else if(flag == 0) {
				Integer lowerKey = tm.lowerKey(x);
				int sum;
				if(lowerKey==null) {
					sum=N;
				} else {
					sum = N-tm.get(tm.lowerKey(x));
				}
				bw.write(sum+"\n");
			}
			// 1 2 2 2 3 3 3 4 5 6
			// 1 - 1
			// 2 - 4
			// 3 - 7
			// 4 - 8
			// 5 - 9
			// 6 - 10
			else if (flag == 1) {
				Integer floorKey = tm.floorKey(x);
				int sum;
				if(floorKey == null) {
					sum = N;
				} else {
					sum = N-tm.get(tm.floorKey(x));
				}
				bw.write(sum+"\n");
			}
			else {
				throw new NumberFormatException();
			}
		}
		bw.flush();
	}
}

//1 2 3 4 5
//6 7 8 9 10
