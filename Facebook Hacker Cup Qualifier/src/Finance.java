import java.util.HashMap;

public class Finance {
	public static void main(String[] args) {
		HashMap<Integer, Integer> levelsToCash = new HashMap<>();
		levelsToCash.put(3, 100000);
		levelsToCash.put(4, 150000);
		levelsToCash.put(5, 225000);
		levelsToCash.put(6, 325000);
		int end = 10;
		int currentLevel = 3;
		double currentCash = 100000;
		for(int year=0; year<end; year++) {
			if(year == 3) {
				currentLevel++;
			}
			if(year == 5) {
				currentLevel++;
			}
			if(year == 8) {
				currentLevel++;
			}
			currentCash = currentCash*1.11;
			currentCash += levelsToCash.get(currentLevel);
		}
		System.out.println(currentCash);
	}
}
