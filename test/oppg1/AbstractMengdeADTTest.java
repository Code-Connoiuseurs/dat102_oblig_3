package oppg1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mengdeADT.MengdeADT;

abstract class AbstractMengdeADTTest {

	private MengdeADT<Integer> m0; // Tom mengde
	private MengdeADT<Integer> m1; // Mengde med 1 element
	private MengdeADT<Integer> m2; // Mengde med 2 elementer
	private MengdeADT<Integer> m3; // Mengde med 3 elementer
	private MengdeADT<Integer> m4; // Mengde med 4 elementer
	private MengdeADT<Integer> m5; // Mengde med 4 elementer lik m4, men elementene lagt til i annen rekkefølge
	private MengdeADT<Integer> m6; // Mengde med 5 elementer, disjunkt
	private MengdeADT<Integer> m7; // Mengde med 9 elementer, m5 union m6

	abstract MengdeADT<Integer> opprettNyMengde();

	@BeforeEach
	void setup() {
		m0 = opprettNyMengde();

		m1 = opprettNyMengde();
		m1.leggTil(1);

		m2 = opprettNyMengde();
		m2.leggTil(1);
		m2.leggTil(2);

		m3 = opprettNyMengde();
		m3.leggTil(1);
		m3.leggTil(3);
		m3.leggTil(4);

		m4 = opprettNyMengde();
		m4.leggTil(1);
		m4.leggTil(2);
		m4.leggTil(4);
		m4.leggTil(5);

		m5 = opprettNyMengde();
		m5.leggTil(2);
		m5.leggTil(4);
		m5.leggTil(1);
		m5.leggTil(5);

		m6 = opprettNyMengde();
		m6.leggTil(6);
		m6.leggTil(7);
		m6.leggTil(8);
		m6.leggTil(9);
		m6.leggTil(10);

		m7 = opprettNyMengde();
		m7.leggTil(1);
		m7.leggTil(2);
		m7.leggTil(4);
		m7.leggTil(5);
		m7.leggTil(6);
		m7.leggTil(7);
		m7.leggTil(8);
		m7.leggTil(9);
		m7.leggTil(10);
	}

	@Test
	void testErTom() {
		assertTrue(m0.erTom());
		assertFalse(m1.erTom());
	}

	@Test
	void testInneholder() {
		assertTrue(m1.inneholder(1));
		assertTrue(m2.inneholder(1));
		assertTrue(m2.inneholder(2));
		assertTrue(m3.inneholder(1));
		assertTrue(m3.inneholder(3));
		assertTrue(m3.inneholder(4));
		assertFalse(m0.inneholder(1));
	}

	@Test
	void testErDelmengdeAv() {
		assertTrue(m0.erDelmengdeAv(m1));
		assertTrue(m2.erDelmengdeAv(m4));
		assertTrue(m4.erDelmengdeAv(m4));
		assertFalse(m3.erDelmengdeAv(m4));
		assertFalse(m2.erDelmengdeAv(m0));
		assertFalse(m6.erDelmengdeAv(m2));

	}

	@Test
	void testErLik() {
		assertTrue(m0.erLik(m0));
		assertTrue(m6.erLik(m6));
		assertTrue(m4.erLik(m5));
		assertFalse(m1.erLik(m4));
		assertFalse(m2.erLik(m4));
		assertFalse(m6.erDelmengdeAv(m0));
	}

	@Test
	void testErDisjunkt() {
		assertTrue(m0.erDisjunkt(m0));
		assertTrue(m0.erDisjunkt(m1));
		assertTrue(m6.erDisjunkt(m5));
		assertFalse(m1.erDisjunkt(m2));
		assertFalse(m1.erDisjunkt(m5));
	}

	@Test
	void testSnitt() {
		assertTrue(m1.erLik(m1.snitt(m2)));
		assertTrue(m1.erLik(m1.snitt(m4)));

		assertEquals(null, m4.snitt(m6));
		assertEquals(null, m0.snitt(m3));
	}

	@Test
	void testUnion() {

		assertTrue(m1.erLik(m0.union(m1)));
		assertTrue(m6.erLik(m0.union(m6)));
		assertTrue(m1.erLik(m1.union(m1)));

		m0 = m0.union(m0);
		assertTrue(m0.erTom());

		assertTrue(m2.erLik(m1.union(m2)));
		assertTrue(m7.erLik(m5.union(m6)));
	}

	@Test
	void testMinus() {
		assertTrue(m0.erLik(m4.minus(m5)));
		assertTrue(m5.erLik(m5.minus(m6)));
		assertTrue(m5.erLik(m7.minus(m6)));

		assertFalse(m5.erDelmengdeAv(m5.minus(m5)));
		assertFalse(m0.erLik(m2.minus(m1)));

	}

	@Test
	void testLeggTil() {
		m0.leggTil(1);
		assertTrue(m0.erLik(m1));
		m0.leggTil(3);
		m0.leggTil(4);
		assertTrue(m0.erLik(m3));
		assertTrue(m3.erDelmengdeAv(m0));

	}

	@Test
	void testLeggTilAlleFra() {
		m0.leggTilAlleFra(m6);
		assertTrue(m0.erLik(m6));
		m4.leggTilAlleFra(m5);
		assertTrue(m4.erLik(m5));

	}

	@Test
	void testFjern() {
		m1.fjern(1);
		assertTrue(m1.erTom());

		m5.fjern(1);
		m5.fjern(2);
		m5.fjern(4);
		m5.fjern(5);
		assertTrue(m5.erTom());

		m4.fjern(4);
		m4.fjern(5);
		assertTrue(m4.erLik(m2));

		assertEquals(10, m6.fjern(10));
	}

	@Test
	void testAntallElementer() {
		assertEquals(0, m0.antallElementer());
		assertEquals(1, m1.antallElementer());
		assertEquals(2, m2.antallElementer());
		assertEquals(3, m3.antallElementer());
		assertEquals(4, m4.antallElementer());

		m0.leggTil(99);
		assertEquals(1, m0.antallElementer());
		m0.leggTil(99);
		assertEquals(1, m0.antallElementer());
		m0.leggTil(11);
		assertEquals(2, m0.antallElementer());
		m4.fjern(1);
		assertEquals(3, m4.antallElementer());
		m4.fjern(2);
		assertEquals(2, m4.antallElementer());
	}
}
