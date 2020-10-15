
public class submask_trick {

	static String toBitString(long mask) {
		long l = Long.parseLong("" + Long.toBinaryString(mask));
		return String.format("%012d", l);
	}

	public static void main(String[] args) {

		long mask = 7;

		long submask = 5;
		while (submask > 0) {
			System.out.println(toBitString(submask));
			// magic
			// wooooof ~~~
			// gets next submask
			submask = (submask - 1) & mask;
		}

	}

}
