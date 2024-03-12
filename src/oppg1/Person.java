package oppg1;

import mengdeADT.MengdeADT;

public class Person {
	public String navn;
	public MengdeADT<String> hobbyer;
	
	public Person(String navn, String...hobbyer) {
		this.navn = navn;
		this.hobbyer = new TabellMengde<String>(hobbyer.length);
		for (String hobby : hobbyer) this.hobbyer.leggTil(hobby);
	}
}
