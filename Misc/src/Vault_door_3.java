
public class Vault_door_3 {
	public static void main(String[] args) {
		String password = "jU5t_a_sna_3lpm1dg347_u_4_mfr54b";
		int[] inds = {0, 1, 2, 3, 4, 5, 6, 7, 15, 14, 13, 12, 11, 10, 9, 8, 30, 28, 26, 24, 22, 20, 18, 16, 31, 29, 27, 25, 23, 21, 19, 17};
		int[] inds2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 20, 22, 24, 26, 28, 30, 31, 29, 27, 25, 23, 21, 19, 17};
		char[] buffer = new char[32];
		int i;
		for(i=0; i<31; i++) {
			buffer[inds2[i]] = password.charAt(inds[i]);
		}
		System.out.println(buffer);

	}
}
