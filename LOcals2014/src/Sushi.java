import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Sushi {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		new Sushi().solve(in,out);
		in.close();out.close();
	}

	class Car implements Comparable<Car>{
		int id, speed;
		public Car(int a, int b) {
			id = a;
			speed = b;
		}
		
		@Override
		public int compareTo(Car o) {
			return this.speed - o.speed;
		}
		
		@Override
		public String toString() {
			return this.speed+"";
		}
	}
	
	class Person implements Comparable<Person>{
		int id, speed;
		public Person(int a, int b) {
			id = a;
			speed = b;
		}
		
		@Override
		public int compareTo(Person o) {
			return o.speed - this.speed;
		}
		
		@Override
		public String toString() {
			return this.speed+"";
		}
	}
	private void solve(Scanner in, PrintWriter out) {
		int N = in.nextInt();
		for(int n=0; n<N; n++) {
			int C = in.nextInt();
			Car[] cars = new Car[C];
			for(int c=0; c<C; c++) {
				cars[c] = new Car(c, in.nextInt());
			}
			Arrays.sort(cars);
			//System.out.println(Arrays.toString(cars));
			
			Person[] people = new Person[4*C];
			for(int c=0; c<4*C; c++) {
				people[c] = new Person(c, in.nextInt());
			}
			Arrays.sort(people);
			//System.out.println(Arrays.toString(people));
			
			int maxTime = 0;
			for(int c=0; c<C; c++) {
				int curPerson = c*4;
				maxTime = Math.max(maxTime, cars[c].speed + people[curPerson].speed);
			}
			out.println("Trip #"+(n+1)+": "+maxTime);
		}
		
	}
}
