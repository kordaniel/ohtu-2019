package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Peruutus {

    public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        edellinenArvo = sovellus.tulos();
        sovellus.nollaa();
        disableUndoButton = false;
        paivitaTuloskentta();
    }
    
}
