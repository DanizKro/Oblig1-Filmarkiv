package no.hvl.data102.filmarkiv.klient;

import java.util.Scanner;

import no.hvl.data102.filmarkiv.adt.FilmarkivADT;
import no.hvl.data102.filmarkiv.impl.Film;
import no.hvl.data102.filmarkiv.impl.Sjanger;



public class Tekstgrensesnitt {

	public static Film lesFilm(){
	
		//Måten innput for film objekt er: public Film(int filmnr, String produsent, String tittel, int lansering, Sjanger sjanger, String filmselskap)
		
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

            //Måtte ta vekk pga feil i kode for switch setningene - tillatet ikke neste loop i while løkken hvis scanner ble lukket.
            //scanner.close();
            
            return new Film(filmnr, produsent, tittel, lansering, sjanger, filmselskap);

        } catch (IllegalArgumentException e) {
            System.out.println("Ugyldig input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Det oppstod en feil: " + e.getMessage());
        }
        return null;
	}
	
	
	// TODO
	// Skriver ut en film med alle opplysninger på skjerm (husk tekst for sjanger)	
	public void skrivUtFilm(Film film) {
			
		System.out.print(film.toString());
		
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
