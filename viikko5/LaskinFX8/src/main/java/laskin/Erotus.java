package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Peruutus {
    
    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    
    @Override
    public void suorita() {
        edellinenArvo = sovellus.tulos();
        sovellus.miinus(Integer.parseInt(syotekentta.getText()));
        disableUndoButton = false;
        paivitaTuloskentta();
    }
    
}
