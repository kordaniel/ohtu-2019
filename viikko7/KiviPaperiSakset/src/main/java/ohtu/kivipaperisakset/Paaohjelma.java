package ohtu.kivipaperisakset;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Yksinkertainen Kivi-paperi-sakset toteutus. Tämä implementaatio sisältää
 * näkökulmasta riippuen ehkä hieman "turhaa" koodia, mutta syy siihen
 * on, että mahdollinen jatkokehittäminen olisi helpompaa. Esim. jos
 * halutaan toteuttaa (ohjasta?) tuttu Kivi-paperi-sakset-lizard-spock.
 * @author danielko
 */
public class Paaohjelma {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KiviPaperSaksetPeli peli;
        
        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine().trim();
            
            peli = KiviPaperSakset.luo(vastaus, scanner);
            if (peli == null) {
                break;
            }
            
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin " + Arrays.toString(KiviPaperSakset.TUETUT_KOMENNOT));
            peli.pelaa();
        }

    }
}
