package ohtu.intjoukkosovellus;

import java.util.HashSet;

public class IntJoukko {
    public final static int KAPASITEETTI = 5;
    public final static int OLETUSKASVATUS = 5;
    
    private int kasvatuskoko;
    private int[] alkiot;
    
    //montako alkiota taulukossa on, kaytetaan myos uuden
    //alkion lisaamisessa indeksimuuttujana!
    private int alkioidenLkm;

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kapasiteetti2");//heitin vaan jotain :D
        }
        
        this.kasvatuskoko = kasvatuskoko;
        
        this.alkiot = new int[kapasiteetti];
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }
    
    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }
    
    public int mahtavuus() {
        return this.alkioidenLkm;
    }
    
    public boolean lisaa(int luku) {
        if (kuuluu(luku)) {
            return false;
        }
        if (onTaynna()) {
            kasvataTaulukkoa();
        }

        this.alkiot[alkioidenLkm++] = luku;
        
        return true;
    }
    
    public boolean poista(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (alkiot[i] == luku) {
                poistaKohdastaJaSiirraLoputVasemmalle(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (alkiot[i] == luku) {
                return true;
            }
        }
        return false;
    }
    
    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(alkiot, 0, taulu, 0, taulu.length);
        return taulu;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < alkioidenLkm; i++) {
            if (i == 0) {
                sb.append(alkiot[i]);
            } else {
                sb.append(", ");
                sb.append(alkiot[i]);
            }
        }
        sb.append("}");
        return sb.toString();
    }
    
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteJoukko = new IntJoukko();
        
        for (int i : a.toIntArray()) {
            yhdisteJoukko.lisaa(i);
        }
        for (int i : b.toIntArray()) {
            yhdisteJoukko.lisaa(i);
        }
        
        return yhdisteJoukko;
    }
    
    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausJoukko = new IntJoukko();
        HashSet<Integer> joukonAJasenet = muodostaHajautusjoukko(a.toIntArray());
        
        for (int i : b.toIntArray()) {
            if (joukonAJasenet.contains(i)) {
                leikkausJoukko.lisaa(i);
            }
        }
        
        return leikkausJoukko;
    }
    
    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotusJoukko = new IntJoukko();
        HashSet<Integer> joukonBJasenet = muodostaHajautusjoukko(b.toIntArray());
        
        for (int i : a.toIntArray()) {
            if (!joukonBJasenet.contains(i)) {
                erotusJoukko.lisaa(i);
            }
        }
        
        return erotusJoukko;
    }
    
    public static HashSet<Integer> muodostaHajautusjoukko(int[] taulukko) {
        HashSet<Integer> joukko = new HashSet<>();
        for (int i : taulukko) {
            joukko.add(i);
        }
        return joukko;
    }
    
    private boolean onTaynna() {
        return alkioidenLkm == alkiot.length;
    }
    
    private void poistaKohdastaJaSiirraLoputVasemmalle(int kohdasta) {
        if (kohdasta >= alkioidenLkm) {
            return;
        }
        
        for (; kohdasta < alkioidenLkm-1; kohdasta++) {
            alkiot[kohdasta] = alkiot[kohdasta+1];
        }
        
        alkiot[alkioidenLkm-1] = 0;
        alkioidenLkm--;
    }
    
    private void kasvataTaulukkoa() {
        int[] uusiTaulukko = new int[alkioidenLkm + kasvatuskoko];
        System.arraycopy(alkiot, 0, uusiTaulukko, 0, alkioidenLkm);
        alkiot = uusiTaulukko;
    }
    
    
}
