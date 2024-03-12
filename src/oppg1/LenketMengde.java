package oppg1;

import mengdeADT.MengdeADT;

public class LenketMengde<T> implements MengdeADT<T> {

	private class Node {
		private T data;
		private Node neste;

		private Node(T data) {
			this.data = data;
			this.neste = null;
		}

	}

	private Node forste;
	private int antall;

	public LenketMengde() {
		forste = null;
		antall = 0;
	}

	@Override
	public boolean erTom() {
		return forste == null || antall == 0;
	}

	@Override
	public boolean inneholder(T element) {
		Node temp = forste;
		while (temp != null) {
			if (temp.data.equals(element)) {
				return true;
			}
			temp = temp.neste;
		}
		return false;
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		Node temp = forste;
		while (temp != null) {
			if (!annenMengde.inneholder(temp.data)) {
				return false;
			}
			temp = temp.neste;
		}
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		if (annenMengde == null && this.erTom()) {
			return true;
		} else if (annenMengde == null || antall != annenMengde.antallElementer()) {
			return false;
		}

		Node temp = forste;
		while (temp != null) {
			if (!annenMengde.inneholder(temp.data)) {
				return false;
			}
			temp = temp.neste;
		}
//		boolean lik = this.erDelmengdeAv(annenMengde);
		return true;

	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		Node temp = forste;
		while (temp != null) {
			if (annenMengde.inneholder(temp.data)) {
				return false;
			}
			temp = temp.neste;
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		MengdeADT<T> snittMengde = new LenketMengde<>();

		if (erDisjunkt(annenMengde)) {
			return snittMengde = null;
		}

		T[] amTab = annenMengde.tilTabell();

		for (int i = 0; i < annenMengde.antallElementer(); i++) {
			if (this.inneholder(amTab[i])) {
				snittMengde.leggTil(amTab[i]);
			}
		}
		return snittMengde;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		MengdeADT<T> unionMengde = new LenketMengde<>();

		unionMengde.leggTilAlleFra(this);
		unionMengde.leggTilAlleFra(annenMengde);

		return unionMengde;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		MengdeADT<T> minusMengde = new LenketMengde<>();

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
		if (this.inneholder(element)) {
			return;
		}
		Node ny = new Node(element);
		ny.neste = forste;
		forste = ny;
		antall++;
	}

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {

		if (this.erLik(annenMengde)) {
			return;
		}

		T[] amTab = annenMengde.tilTabell();

		for (int i = 0; i < annenMengde.antallElementer(); i++) {
			leggTil(amTab[i]);
		}

	}

	@Override
	public T fjern(T element) {
		if (!inneholder(element)) {
			return null;
		}
		Node skalFjernes = finnNode(element);
		T fjernet = skalFjernes.data;

		if (skalFjernes != null) {
			skalFjernes.data = forste.data;
			forste.data = null;
			forste = forste.neste;
			antall--;
		}
		return fjernet;
	}

	private Node finnNode(T element) {
		Node temp = forste;
		while (temp != null) {
			if (temp.data.equals(element)) {
				return temp;
			}
			temp = temp.neste;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] tilTabell() {
		T[] tab = (T[]) new Object[antall];

		Node temp = forste;
		for (int i = 0; i < antall; i++) {
			tab[i] = temp.data;
			temp = temp.neste;
		}
		return tab;
	}

	@Override
	public int antallElementer() {
		return antall;
	}

	@Override
	public T[] tilTabell(int newSize) {
		@SuppressWarnings("unchecked")
		T[] tab = (T[]) new Object[newSize];

		Node temp = forste;
		for (int i = 0; i < antall; i++) {
			tab[i] = temp.data;
			temp = temp.neste;
		}
		return tab;
	}
}
