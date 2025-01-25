package no.hvl.data102.filmarkiv.klient;

import java.util.Scanner;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;
import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Sjanger;



public class Tekstgrensesnitt {

	// Leser inn opplysninger om en film fra tastatur og returnere et Film-objekt
	public static Film lesFilm(){
	
		// public Film(int filmnr, String produsent, String tittel, int lansering, Sjanger sjanger, String filmselskap)
		
		Scanner scanner = new Scanner(System.in);

        try {
            
            System.out.print("Oppgi filmnummer(Integer): ");
            int filmnr = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Oppgi produsent(String): ");
            String produsent = scanner.nextLine();

            System.out.print("Oppgi tittel(String): ");
            String tittel = scanner.nextLine();

            System.out.print("Oppgi lanseringsår(Integer): ");
            int lansering = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Oppgi sjanger (ACTION, DRAMA, EVENTYR, SCIFI, HISTORY): ");
            String sjangerStr = scanner.nextLine().toUpperCase();
            Sjanger sjanger = Sjanger.valueOf(sjangerStr);

            System.out.print("Oppgi filmselskap(String): ");
            String filmselskap = scanner.nextLine();

          
            // -scanner.close();
            
            return new Film(filmnr, produsent, tittel, lansering, sjanger, filmselskap);

        } catch (IllegalArgumentException e) {
            System.out.println("Ugyldig input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Det oppstod en feil: " + e.getMessage());
        }
        return null; // Returner null hvis noe gikk galt
	}
	
	
	// TODO
	// Skriver ut en film med alle opplysninger på skjerm (husk tekst for sjanger)	
	public void skrivUtFilm(Film film) {
		
	}
	
	// TODO
	// Skriver ut alle filmer med en spesiell delstreng i tittelen
	public void skrivUtFilmDelstrengITittel(FilmarkivADT arkiv, String delstreng) {
		
		
	}
	// TODO
	// Skriver ut alle Filmer av en produsent (produsent er delstreng)
	public void skrivUtFilmProdusent(FilmarkivADT arkiv, String delstreng) {
		
	}
	
	// TODO
	// Skriver ut en enkel statistikk som inneholder antall filmer totalt
	// og hvor mange det er i hver sjanger.
	public void skrivUtStatistikk(FilmarkivADT arkiv) {
		
	}
	
}
