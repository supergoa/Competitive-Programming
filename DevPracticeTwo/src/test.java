
public class test {
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		int num = 0;
		for(int i=0; i<1000000000; i++) {
			num+=i;
		}
		System.out.println(num);
		System.out.println((System.currentTimeMillis()-time)/1000.0);
	}
}
