package ohtu.kivipaperisakset;

// "Muistava tekoäly"
import java.util.Random;

public class TekoalyParannettuImpl implements Tekoaly {

    private static final Random rand = new Random();

    private char[] muisti;
    private int vapaaMuistiIndeksi;
    private final char[] komennot;

    public TekoalyParannettuImpl(int muistinKoko) {
        this.muisti = new char[muistinKoko];
        this.vapaaMuistiIndeksi = 0;
        this.komennot = KiviPaperSakset.TUETUT_KOMENNOT;
    }

    @Override
    public void asetaSiirto(String siirto) {
        if (siirto.trim().length() != 1) {
            return;
        }
        // jos muisti täyttyy, unohdetaan viimeinen alkio
        if (muistiTaynna()) {
            unohdaVanhinKomento();
        }

        muisti[vapaaMuistiIndeksi] = siirto.charAt(0);
        vapaaMuistiIndeksi++;
    }

    private void unohdaVanhinKomento() {
        for (int i = 1; i < muisti.length; i++) {
            muisti[i-1] = muisti[i];
        }
        vapaaMuistiIndeksi--;
    }
    
    private boolean muistiTaynna() {
        return vapaaMuistiIndeksi == muisti.length;
    }
    
    @Override
    public String annaSiirto() {
        // palautetaan satunnainen siirto niin kauan,
        // kunnes meillä on riittävästi historiaa. Palautetaan myös
        // "satunnainen" komento, jos valittavia komentoja on vain 1 kpl.
        if (vapaaMuistiIndeksi < 2 || komennot.length < 2) {
            return String.valueOf(komennot[rand.nextInt(komennot.length)]);
        }
        
        int[] seuraavanSiirronKomentojenLkm = laskeSeuraavienKomentojenLkm();
        int komennonIndeksi = valitseSatunnainenPaitsiEnitenValittu(seuraavanSiirronKomentojenLkm);
        
        return String.valueOf(komennot[komennonIndeksi]);
    }
    
    private int[] laskeSeuraavienKomentojenLkm() {
        int[] seuraavanSiirronEriKomentojenLkm = new int[komennot.length];
        
        char viimeisinSiirto = muisti[vapaaMuistiIndeksi - 1];

        for (int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
            if (viimeisinSiirto == muisti[i]) {
                char seuraava = muisti[i + 1];

                for (int j = 0; j < seuraavanSiirronEriKomentojenLkm.length; j++) {
                    if (komennot[j] == seuraava) {
                        seuraavanSiirronEriKomentojenLkm[j]++;
                    }
                }
            }
        }
        
        return seuraavanSiirronEriKomentojenLkm;
    }
    
    private int valitseSatunnainenPaitsiEnitenValittu(int[] seuraavanSiirronKomentojenLkm) {
        int indeksiJossaEniten = -1;
        int suurinMaara = 0;
        
        for (int i = 0; i < seuraavanSiirronKomentojenLkm.length; i++) {
            if (seuraavanSiirronKomentojenLkm[i] > suurinMaara) {
                suurinMaara = seuraavanSiirronKomentojenLkm[i];
                indeksiJossaEniten = i;
            }
        }
        
        return arvoSatunnainenIndeksiPaitsi(indeksiJossaEniten);
    }
    
    private int arvoSatunnainenIndeksiPaitsi(int epakelpoIndeksi) {
        int arvottuIndeksi = 0;
        
        do {
            arvottuIndeksi = rand.nextInt(komennot.length);
        } while (arvottuIndeksi == epakelpoIndeksi);
        
        return arvottuIndeksi;
    }
    
}
