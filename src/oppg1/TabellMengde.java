package oppg1;

import java.util.Arrays;

import mengdeADT.MengdeADT;

public class TabellMengde<T> implements MengdeADT<T> {

	private static final int DEFAULT_CAPACITY = 10;

	private T[] tabell;
	private int antall;

	public TabellMengde() {
		this(DEFAULT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public TabellMengde(int capacity) {
		tabell = (T[]) new Object[capacity];
		antall = 0;
	}

	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public boolean inneholder(T element) {
		for (int i = 0; i < antall; i++) {
			if (tabell[i] == null) {

			} else if (tabell[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		for (int i = 0; i < antall; i++) {
			if (!annenMengde.inneholder(tabell[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		
		return this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		for (int i = 0; i < antall; i++) {
			if (annenMengde.inneholder(tabell[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		if (erDisjunkt(annenMengde)) {
			return null;
		}
		MengdeADT<T> snittMengde = new TabellMengde<>();

		for (int i = 0; i < antall; i++) {
			if (annenMengde.inneholder(tabell[i])) {
				snittMengde.leggTil(tabell[i]);
			}
		}
		return snittMengde;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {

		MengdeADT<T> unionMengde = new TabellMengde<>();

		unionMengde.leggTilAlleFra(this);
		unionMengde.leggTilAlleFra(annenMengde);

		return unionMengde;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		MengdeADT<T> minusMengde = new TabellMengde<>();

		if (erDisjunkt(annenMengde)) {
			minusMengde.leggTilAlleFra(this);
			return minusMengde;
		}
		minusMengde.leggTilAlleFra(this);

		T[] amTab = annenMengde.tilTabell();

		for (T t : amTab) {
			if (minusMengde.inneholder(t)) {
				minusMengde.fjern(t);
			}
		}

		return minusMengde;
	}

	@Override
	public void leggTil(T element) {
		if (antall == tabell.length) {
			tabell = Arrays.copyOf(tabell, tabell.length * 2);
		}

		if (element == null || inneholder(element)) {
			return;
		} else {
			tabell[antall] = element;
			antall++;
		}

	}

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		if (this.erLik(annenMengde)) {
			return;
		}

		int newSize = antall + annenMengde.antallElementer();
		tabell = Arrays.copyOf(tabell, newSize);

		T[] amTab = annenMengde.tilTabell(newSize);
		for (int i = 0; i < newSize; i++) {
			leggTil(amTab[i]);
		}
	}

	@Override
	public T fjern(T element) {
		if (!inneholder(element)) {
			return null;
		}
		for (int i = 0; i < antall; i++) {
			if (tabell[i] == null) {

			} else if (tabell[i].equals(element)) {
				tabell[i] = tabell[antall - 1];
				tabell[antall - 1] = null;
				antall--;
				return element;
			}
		}
		return null;
	}

	@Override
	public T[] tilTabell() {
		return Arrays.copyOf(tabell, antall);
	}

	public T[] tilTabell(int length) {
		return Arrays.copyOf(tabell, length);
	}

	@Override
	public int antallElementer() {
		return antall;
	}

}
