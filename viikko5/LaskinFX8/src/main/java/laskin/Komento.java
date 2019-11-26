package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
    protected TextField tuloskentta; 
    protected TextField syotekentta; 
    protected Button nollaa;
    protected Button undo;
    protected boolean disableUndoButton;
    protected Sovelluslogiikka sovellus;
    

    public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
        this.disableUndoButton = true;
    }
    
    protected abstract void suorita();
    protected abstract void peru();
    
    protected void paivitaTuloskentta() {
        int laskunTulos = sovellus.tulos();
        
        this.syotekentta.setText("");
        this.tuloskentta.setText(String.valueOf(laskunTulos));
        
        if (laskunTulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        
        if (disableUndoButton) {
            undo.disableProperty().set(true);
        } else {
            undo.disableProperty().set(false);
        }
        
    }
    
}
