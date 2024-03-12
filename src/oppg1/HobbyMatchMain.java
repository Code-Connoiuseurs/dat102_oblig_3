package oppg1;

public class HobbyMatchMain {
	static double match(Person a, Person b) {
		int alleHobbyer = a.hobbyer.union(b.hobbyer).antallElementer();
		int hobbyerKunA = a.hobbyer.minus(b.hobbyer).antallElementer();
		int hobbyerKunB = b.hobbyer.minus(a.hobbyer).antallElementer();
		int fellesHobbyer = a.hobbyer.snitt(b.hobbyer).antallElementer();
		return fellesHobbyer - (hobbyerKunA + hobbyerKunB) / alleHobbyer;
	}
	
	public static void main(String[] args) {
		Person p1 = new Person("Arne", "fisking", "jakting", "dykking");
		Person p2 = new Person("Bjarne", "fisking", "jakting", "klatring");
		Person p3 = new Person("Carne", "fisking", "camping", "kjøring");
		
		System.out.println(match(p1, p1));
		System.out.println(match(p1, p2));
		System.out.println(match(p2, p1));
		System.out.println(match(p1, p3));
		
	}
}
