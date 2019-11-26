package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Peruutus extends Komento {

    protected int edellinenArvo;
    
    public Peruutus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }
    
    @Override
    protected abstract void suorita();

    @Override
    public void peru() {
        sovellus.asetaTulos(edellinenArvo);
        disableUndoButton = true;
        paivitaTuloskentta();
    }
    
}
