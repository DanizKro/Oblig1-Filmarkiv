package no.hvl.data102.filmarkiv.impl;

import java.util.ArrayList;
import java.util.List;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;

public class Filmarkiv implements FilmarkivADT {

	Film[] filmarkiv;

	private int antall;

	public Filmarkiv(int storrelse) {
		filmarkiv = new Film[storrelse];
		antall = 0;

	}

	@Override
	public Film finnFilm(int nr) {

		for (int i = 0; i < antall; i++) {
			if (filmarkiv[i].getFilmnr() == nr) {
				return filmarkiv[i];
			}
		}
		return null;
	}

	@Override
	public void leggTilFilm(Film nyFilm) {

		if (antall < filmarkiv.length) {
			filmarkiv[antall] = nyFilm;
			this.antall++;
		}

	}

	@Override
	public boolean slettFilm(int filmnr) {

		for (int i = 0; i < antall; i++) {
			if (filmarkiv[i].getFilmnr() == filmnr) {
				filmarkiv[i] = filmarkiv[antall - 1];
				filmarkiv[antall - 1] = null;
				antall--;
				return true;
			}
		}
		return false;
	}

	@Override
	public Film[] soekTittel(String delstreng) {
		
		// Den 'beste' løsningen ved å bruke en dynamisk liste (filmer)
		List<Film> filmer = new ArrayList<>();
		for (int i = 0; i < antall; i++) {
			if (filmarkiv[i].getTittel().toLowerCase().contains(delstreng.toLowerCase())) {
				filmer.add(filmarkiv[i]);
			}
		}
		return filmer.toArray(new Film[0]);
		
		// *** Hvor mange filmer har denne delstrengen?
		// int antallFilmerMedDelstrang = 0;
		// for (int i = 0; i < antall; i++) {
		// 	if (filmarkiv[i].getTittel().toLowerCase().contains(delstreng.toLowerCase())) {
		// 		antallFilmerMedDelstrang++;
		// 	}
		// }

		// *** Legg til filmer med denne delstrengen in en ny tabell
		// Film[] filmer = new Film[antallFilmerMedDelstrang];
		// int filmerIndex = 0;
		// for (int i = 0; i < antall; i++) {
		// 	if (filmarkiv[i].getTittel().toLowerCase().contains(delstreng.toLowerCase())) {
		// 		filmer[filmerIndex] = filmarkiv[i];
		// 		filmerIndex++;
		// 	}
		// }

		//return filmer;
	}

	@Override
	public Film[] soekProdusent(String delstreng) {

		int teller = 0;

		// teller opp hvor stor tabellen må være for å få plass til alle med samme
		// produsent
		for (int i = 0; i < antall; i++) {
			if (filmarkiv[i].getProdusent().equalsIgnoreCase(delstreng)) {
				teller++;
			}
		}
		// lager en ny tabell som er akkuratt stor nok til å ta imot alle med samme
		Film[] sjangerTab = new Film[teller];

		int index = 0;

		// kopierer inn alle objektene med samme produsent i ny tabell
		for (int i = 0; i < antall; i++) {
			if (filmarkiv[i].getProdusent().equalsIgnoreCase(delstreng)) {
				sjangerTab[index] = filmarkiv[i];
				index++;
			}
		}
		return sjangerTab;
	}

	@Override
	public int antallSjanger(Sjanger sjanger) {

		int teller = 0;

		for (int i = 0; i < antall; i++) {
			if (filmarkiv[i].getSjanger().equals(sjanger)) {
				teller++;
			}
		}
		return teller;
	}

	public int getAntall() {
		return antall;
	}

	public Film[] hentFilmTabell() {

		Film[] result = new Film[antall];
		for (int i = 0; i < antall; i++) {
			result[i] = filmarkiv[i];
		}
		return result;
	}

	public void utvid(Film[] film) {

		Film[] temp = new Film[film.length * 2];

		if (antall == film.length) {
			for (int i = 0; i < antall; i++) {
				temp[i] = film[i];
			}
			filmarkiv = temp;
		}

	}

	private Film[] trimTab(Film[] tab, int n) {
		// n er antall elementer
		Film[] nytab = new Film[n];
		int i = 0;
		while (i < n) {
			nytab[i] = tab[i];
			i++;
		}
		return nytab;
	}

}
