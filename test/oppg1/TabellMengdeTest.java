package oppg1;

import mengdeADT.MengdeADT;

class TabellMengdeTest extends AbstractMengdeADTTest {

	@Override
	MengdeADT<Integer> opprettNyMengde() {
		return new TabellMengde<Integer>();
	}
}
