package ohtu.kivipaperisakset;

public class TekoalyImpl implements Tekoaly {

    int siirto;

    public TekoalyImpl() {
        siirto = 0;
    }

    @Override
    public String annaSiirto() {
        siirto++;
        siirto = siirto % KiviPaperSakset.TUETUT_KOMENNOT.length;
        return String.valueOf((KiviPaperSakset.TUETUT_KOMENNOT[siirto]));
    }

    @Override
    public void asetaSiirto(String ekanSiirto) {
        // ei tehdä mitään 
    }

}
