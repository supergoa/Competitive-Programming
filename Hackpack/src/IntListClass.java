import java.util.Arrays;

public class IntListClass {
	static class IntList {
        static int[] EMPTY = {};
        int n = 0;
        int[] a = EMPTY;

        public void add(int v) {
            if (n >= a.length)
                a = Arrays.copyOf(a, (n << 2) + 8);
            a[n++] = v;
        }

        public int get(int idx) {
            return a[idx];
        }

        public int size() {
            return n;
        }

    }
}
