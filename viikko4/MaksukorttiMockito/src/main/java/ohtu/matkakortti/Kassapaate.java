package ohtu.matkakortti;

public class Kassapaate {
    private int myytyjaLounaita;
    public static final int HINTA = 5;

    public Kassapaate() {
        this.myytyjaLounaita = 0;
    }
    
    public void lataa(Maksukortti kortti, int summa){
        //oletetaan, etta 0 on positiivinen!
        if (summa < 0) {
            return;
        }

        kortti.lataa(summa);
    }
    
    public void ostaLounas(Maksukortti kortti) {
        if (kortti.getSaldo() < HINTA) {
            return;
        }

        kortti.osta(HINTA);
        myytyjaLounaita++;
    }

    public int getMyytyjaLounaita() {
        return myytyjaLounaita;
    }
}
