package ohtu.verkkokauppa;

public class Pankki implements InterfacePankki {
    /*
    private static Pankki instanssi;

    public static Pankki getInstance() {
        if (instanssi == null) {
            instanssi = new Pankki();
        }

        return instanssi;
    }
    */
    private InterfaceKirjanpito kirjanpito;

    public Pankki(InterfaceKirjanpito kirjanpito) {
        //kirjanpito = Kirjanpito.getInstance();
        this.kirjanpito = kirjanpito;
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
