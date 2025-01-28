package no.hvl.data102.filmarkiv.impl;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;

public class Filmarkiv2 implements FilmarkivADT {
	
	private int antall;
	private LinearNode<Film> start;
	
	public Filmarkiv2() {
        this.start = null;
        this.antall = 0;
    }

	@Override
    public Film finnFilm(int nr) {
		
        LinearNode<Film> sok = start;

        while (sok != null) {
            if (sok.data.getFilmnr() == nr) {
                return sok.data;
            }
            sok = sok.neste;
        }
        return null;
    }


	@Override
	public void leggTilFilm(Film nyFilm) {
		LinearNode<Film> nyNode = new LinearNode<>();
		nyNode.data = nyFilm;							//Legger til Film objekt i dataen til nye noden
		nyNode.neste = start;							//Får den nye noden sin neste peker til å peke på første node i linjen (slik at nye noden kommer først i rekken)
		start = nyNode;									//Setter den nye starten til å være den nye noden som er lagt til
		antall++;
		
	}

	@Override
	public boolean slettFilm(int filmnr) {
		
		LinearNode<Film> sok = start;
		boolean slettet = false;
		
		while (sok != null) {
            if (sok.data.getFilmnr() == filmnr) {
                
            }
            sok = sok.neste;
        }
		return slettet;
	}

	@Override
	public Film[] soekTittel(String delstreng) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film[] soekProdusent(String delstreng) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int antallSjanger(Sjanger sjanger) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAntall() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Film[] hentFilmTabell() {
		// TODO Auto-generated method stub
		return null;
	}

}
