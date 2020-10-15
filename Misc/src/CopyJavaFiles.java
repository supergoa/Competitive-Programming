import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CopyJavaFiles {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String loc = in.next();
		String out = in.next();
		
		File[] locs = new File(loc).listFiles();
		ArrayList<File> files = (ArrayList<File>) Arrays.asList(locs);
		boolean stop = false;
		while(!stop) {
			for(File f: files) {
				if(f.isDirectory()) {
					files.addAll((ArrayList<File>) Arrays.asList(f.listFiles()));
				}
			}
		}
	}
}
