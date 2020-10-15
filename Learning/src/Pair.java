class Pair2 implements Comparable<Pair>{
	double x;
	double y;
	
	public Pair(int posR, int posC) {}
	
	@Override
	public String toString() {
		return x + ","+y;
	}

	@Override
	public int compareTo(Pair b) {
		if(x == b.x) {
			if(y == b.y) {
				return 0;
			}
			else if (y>b.y){
				return 1;
			}
			else {
				return -1;
			}
		}
		if(x > b.x) {
			return 1;
		} else {
			return -1;
		}
	}
	
	
}