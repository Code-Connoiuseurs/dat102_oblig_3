package oppg1;

import mengdeADT.MengdeADT;

class LenketMengdeTest extends AbstractMengdeADTTest{

	@Override
	MengdeADT<Integer> opprettNyMengde() {
		return new LenketMengde<Integer>();
	}	
	
}
