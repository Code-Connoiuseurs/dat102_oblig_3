package oppg1;

import mengdeADT.MengdeADT;

public class JavaSetToMengdeTest extends AbstractMengdeADTTest {
	@Override
	MengdeADT<Integer> opprettNyMengde() {
		return new JavaSetToMengde<Integer>();
	}

}
