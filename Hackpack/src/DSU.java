
public class DSU {
	int[] id;
	int size;
	DSU(int x) { //x is size
		size = x + 1;
		id = new int[size]; // disj relation
		for (int i = 0; i < size; ++i) {
			id[i] = i;
		}
	}
	int root(int a) { //get root of a. "id[a] = get(id[a])" makes retrieval O(1) on successive calls
		return id[a] == a ? a : (id[a] = root(id[a]));
	}
	
	void union(int a, int b) {
		if(root(a) == root(b)) return;
		id[root(a)] = id[root(b)];
		--size;
	}
}
