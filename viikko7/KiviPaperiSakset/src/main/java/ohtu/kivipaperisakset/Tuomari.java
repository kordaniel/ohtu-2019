package ohtu.kivipaperisakset;

// Tuomari pitää kirjaa ensimmäisen ja toisen pelaajan pisteistä sekä tasapelien määrästä.
public class Tuomari {
    
    //indeksi 0: tasapelit
    //indeksi 1: pelaaja1 voitot
    //indeksi 2: pelaaja2 voitot
    private int[] pistelasku;
    
    public Tuomari() {
        this.pistelasku = new int[3];
    }

    public void kirjaaSiirto(String ekanSiirto, String tokanSiirto) {
        int pelinTulos = laskeKierroksenTulos(ekanSiirto.charAt(0),
                                                tokanSiirto.charAt(0));
        pistelasku[pelinTulos]++;
    }

    private int laskeKierroksenTulos(char ekanSiirto, char tokanSiirto) {
        char[] komennot = KiviPaperSakset.TUETUT_KOMENNOT;
        int p1Tulos = -1;
        int p2Tulos = -1;
        
        for (int i = 0; i < komennot.length; i++) {
            if (ekanSiirto == komennot[i]) {
                p1Tulos = i;
            }
            if (tokanSiirto == komennot[i]) {
                p2Tulos = i;
            }
        }
        
        return (p1Tulos - p2Tulos + komennot.length) % komennot.length;
    }

    public String toString() {
        String s = "Pelitilanne: " + pistelasku[1] + " - " + pistelasku[2] + "\n"
                + "Tasapelit: " + pistelasku[0];
        return s;
    }
}