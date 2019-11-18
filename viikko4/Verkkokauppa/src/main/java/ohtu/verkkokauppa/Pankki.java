package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Pankki implements InterfacePankki {
    
    @Autowired
    private InterfaceKirjanpito kirjanpito;

    /* TURHA, vaikka on interface?
     * näyttäisi olevan, jätetään nyt kuitenkin tähän
    @Autowired
    public Pankki(InterfaceKirjanpito kirjanpito) {
        this.kirjanpito = kirjanpito;
    }
    */

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpito.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
