package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class KiviPaperSakset implements KiviPaperSaksetPeli {

    // eri komennot tulee lisätä oikeassa järjestyksessä, pistelasku
    // perustuu niiden indekseihin!
    protected static final char[] TUETUT_KOMENNOT = new char[] {'k', 'p', 's'};
    protected final Scanner scanner;
    protected       Tuomari tuomari;
    protected       Tekoaly tekoaly;
    
    public static KiviPaperSaksetPeli luo(String komento, Scanner in) {
        if (komento != null) switch (komento) {
            case "a":
                return new KPSPelaajaVsPelaaja(in);
            case "b":
                return new KPSTekoaly(in);
            case "c":
                return new KPSParempiTekoaly(in);
            default:
                return null;
        }
        return null;
    }
    
    public KiviPaperSakset(Scanner scanner) {
        this.scanner = scanner;
        tuomari = new Tuomari();
    }
    
    @Override
    public abstract void pelaa();
    
    protected void pelaaTekoalyaVastaan() {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
        String tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);


        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();

            tokanSiirto = tekoaly.annaSiirto();
            System.out.println("Tietokone valitsi: " + tokanSiirto);
            tekoaly.asetaSiirto(ekanSiirto);
        }
        tulostaLopetus();
    }
    
    protected void tulostaLopetus() {
        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
    protected static boolean onkoOkSiirto(String siirto) {
        siirto = siirto.trim();
        if (siirto.length() != 1) {
            return false;
        }
        
        for (int i = 0; i < TUETUT_KOMENNOT.length; i++) {
            if (TUETUT_KOMENNOT[i] == siirto.charAt(0)) {
                return true;
            }
        }
        
        return false;
    }
    
}
