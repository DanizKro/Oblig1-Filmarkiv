package no.hvl.data102.filmarkiv.klient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;
import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Filmarkiv;
import no.hvl.data102.filmarkiv.impl.Sjanger;

public class FilmarkivMain {

	public static void main(String[] args) {

		// Laster opp filmer fra tidligere dokument

		Filmarkiv arkiv = new Filmarkiv(50);
		Film[] filmer = lesInnFraFil(
				"/Users/dkron/Documents/GitHub/Oblig1-Filmarkiv/src/no/hvl/data102/filmarkiv/klient/Filmarkiv.txt");

		for (Film film : filmer) {
			if (film != null) {
				arkiv.leggTilFilm(film); // Legg filmer inn i arkivet
			}
		}

		// Spør om du vil skrive ut filmer du har, eller om du vil legge til en ny fil

		System.out.println("Velkommen til ditt filmarkiv! :)");

		boolean avslutt = false;

		while (!avslutt) {
			System.out.println("Velg en av følgende alternativer:");
			System.out.println("1: Print ut filmarkiv til skjermen");
			System.out.println("2: Legg til ny film");
			System.out.println("3: Slett film");
			System.out.println("0: Lagre og avslutt");

			Scanner scanner = new Scanner(System.in);
			String valg = scanner.nextLine();

			switch (valg) {
			case "1":
				skrivUt(arkiv);
				break;

			case "2":
				arkiv.leggTilFilm(lesFilm());
				break;

			case "3":
				System.out.println("Slett Filmnr: ");
				int nr = scanner.nextInt();
				scanner.nextLine();
				arkiv.slettFilm(nr);
				break;

			case "0":
				printFil(arkiv, "/Users/dkron/Documents/GitHub/Oblig1-Filmarkiv/src/no/hvl/data102/filmarkiv/klient/Filmarkiv.txt");
				avslutt = true;
				System.out.println("Avslutter programmet. Ha en fin dag!");
				break;

			default:
				System.out.println("Ugyldig valg. Prøv igjen.");
				break;
			}
		}


//		//--------------------------------------------------------------------------------
//		// Opprett et nytt Filmarkiv og fyll det med filmer fra fil
//		Filmarkiv arkiv = new Filmarkiv(50);
//		Film[] filmer = lesInnFraFil("/Users/dkron/Documents/GitHub/Oblig1-Filmarkiv/src/no/hvl/data102/filmarkiv/klient/Filmarkiv.txt");
//
//		for (Film film : filmer) {
//			if (film != null) {
//				arkiv.leggTilFilm(film); // Legg filmer inn i arkivet
//			}
//		}
//
//		// Skriv ut filmene for å sjekke innholdet
//		for (Film film : arkiv.hentFilmTabell()) {
//			if (film != null) {
//				System.out.println(film);
//			}
//		}
//		//--------------------------------------------------------------------------------

	}

	private static void skrivUt(Filmarkiv arkiv) {
		Film[] filmer = arkiv.hentFilmTabell(); // Hent tabellen med filmer fra arkivet
		if (filmer.length == 0 || filmer[0] == null) {
			System.out.println("Filmarkivet er tomt.");
			return;
		}

		System.out.println("Innhold i filmarkivet:");
		for (Film film : filmer) {
			if (film != null) {
				System.out.println(film.toString());
			}
		}
		System.out.println();
	}

	private static Film lesFilm() {

		Filmarkiv filmer = new Filmarkiv(50);
		Meny meny = new Meny(filmer);
		meny.start();

		Film nyfilm = Tekstgrensesnitt.lesFilm();

		System.out.print("Du har lagret følgende film " + nyfilm.toString());
		return nyfilm;
	}

	// Hjelp fra ChattGPT kode for innlesing av fil:
	public static Film[] lesInnFraFil(String filnavn) {
		Film[] filmer = new Film[50]; // Anta maksimalt 100 filmer
		int antallFilmer = 0;

		try {
			File fil = new File(filnavn);
			BufferedReader leser = new BufferedReader(new FileReader(fil));

			String linje;
			while ((linje = leser.readLine()) != null && antallFilmer < filmer.length) {
				// Fjern de ytre klammene og del opp verdiene
				linje = linje.substring(1, linje.length() - 1); // Fjern '[' og ']'
				String[] data = linje.split(", ");

				// Ekstraher verdier fra hver del
				int filmnr = Integer.parseInt(data[0].split("=")[1]);
				String produsent = data[1].split("=")[1];
				String tittel = data[2].split("=")[1];
				int lansering = Integer.parseInt(data[3].split("=")[1]);
				Sjanger sjanger = Sjanger.valueOf(data[4].split("=")[1].toUpperCase());
				String filmselskap = data[5].split("=")[1];

				// Opprett filmobjekt og legg til i tabellen
				filmer[antallFilmer] = new Film(filmnr, produsent, tittel, lansering, sjanger, filmselskap);
				antallFilmer++;
			}

			leser.close();
			System.out.println("[" + antallFilmer + "]" + "Filmer lest inn fra fil: " + filnavn);

		} catch (Exception e) {
			System.out.println("Feil under lesing av fil: " + e.getMessage());
		}

		return filmer;
	}

	// Skriver ut filmer fra innlest tabell til gitt plassering
	public static void printFil(Filmarkiv arkiv, String filnavn) {
		try {
			// Sørg for at mappen eksisterer
			File dir = new File(filnavn).getParentFile();
			if (!dir.exists()) {
				dir.mkdirs(); // Opprett mappe hvis den ikke finnes
			}

			File nyFil = new File(filnavn);
			PrintWriter skriv = new PrintWriter(nyFil);

			// Hent filmer fra arkivet og skriv til fil
			Film[] filmer = arkiv.hentFilmTabell();
			for (Film e : filmer) {
				if (e != null) {
					skriv.println(e.toString());
				}
			}

			skriv.close();
			System.out.println("Lagret fil: " + filnavn);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
