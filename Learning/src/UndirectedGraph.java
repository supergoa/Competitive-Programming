import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UndirectedGraph {
	public static void main(String[] args) {
		/*Scanner scan = new Scanner(System.in);
		int N, M, Q;
		N = scan.nextInt();
		M = scan.nextInt();
		
		boolean[][] adjMatrix = new boolean[N][N];
		for(int i = 0; i < M; i++) {
			adjMatrix[scan.nextInt()][scan.nextInt()] = true;
		}
		
		Q = scan.nextInt();
		for(int i = 0; i < Q; i++) {
			if(adjMatrix[scan.nextInt()][scan.nextInt()] == true) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		scan.close();
	}*/
		
		class Person {
			String name;
			int age;
			char gender;

			Person(String name, int age, char gender) {
				this.name = name;
				this.age = age;
				this.gender = gender;
			}
			@Override
			public String toString() {
				return this.name;
			}
		}
		
		List<Person> people = new ArrayList<>();
		people.add(new Person("Nick", 19, 'M'));
		people.add(new Person("Emily", 17, 'F'));
		people.add(new Person("Mom", 49, 'F'));
		people.add(new Person("Dad", 54, 'M'));
		people.add(new Person("La Quisha", 3, 'F'));
		
		//System.out.println(people.stream().count());
		//System.out.println(people.size());
		//System.out.println(people.stream().toString());
		//System.out.println(people.toString());
		//System.out.println(people.get(0).toString());
		System.out.println(Arrays.asList(people.stream().filter(w -> w.age >= 20 && w.gender=='M').toArray()));
	}
}
