package oppg1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import mengdeADT.MengdeADT;

public class JavaSetToMengde<T> implements MengdeADT<T> {

	private Set<T> s = new HashSet<>();

	@Override
	public boolean erTom() {
		return s.isEmpty();
	}

	@Override
	public boolean inneholder(T element) {
		return s.contains(element);
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		Iterator<T> i = s.iterator();

		while (i.hasNext()) {
			if (!annenMengde.inneholder(i.next())) {
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
		if (!this.erDelmengdeAv(annenMengde) && !annenMengde.erDelmengdeAv(this) || this.erTom()
				|| annenMengde.erTom()) {
			return true;
		}
		return false;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		if (erDisjunkt(annenMengde)) {
			return null;
		}
		MengdeADT<T> snittMengde = new JavaSetToMengde<>();

		Iterator<T> i = s.iterator();

		T[] amTab = annenMengde.tilTabell();

		int j = 0;
		while (j < amTab.length && i.hasNext()) {

			if (this.inneholder(amTab[j])) {
				snittMengde.leggTil(amTab[j]);
			}
			j++;
		}
		return snittMengde;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		MengdeADT<T> unionMengde = new JavaSetToMengde<>();

		unionMengde.leggTilAlleFra(this);
		unionMengde.leggTilAlleFra(annenMengde);

		return unionMengde;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		MengdeADT<T> minusMengde = new JavaSetToMengde<>();

		if (this.erDisjunkt(annenMengde)) {
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
		s.add(element);
	}

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		T[] amTabell = annenMengde.tilTabell();

		for (T t : amTabell) {
			s.add(t);
		}
	}

	@Override
	public T fjern(T element) {
		if (!inneholder(element)) {
			return null;
		}

		T fjernet = element;
		s.remove(fjernet);

		return fjernet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] tilTabell() {
		return (T[]) s.toArray();
	}

	@Override
	public int antallElementer() {
		return s.size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] tilTabell(int newSize) {
		return (T[]) s.toArray();
	}

}
