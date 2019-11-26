package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    
    private Button undo;
    private Sovelluslogiikka sovellus;
    private Map<Button, Komento> komennot;
    private Komento edellinen;
    
    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.komennot = new HashMap<>();
        this.komennot.put(plus, new Summa(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        this.komennot.put(nollaa, new Nollaa(tuloskentta, syotekentta, nollaa, undo, sovellus));
    }
    
    @Override
    public void handle(Event event) {
        if (event.getTarget() != undo) {
            Komento komento = komennot.get((Button) event.getTarget());
            komento.suorita();
            edellinen = komento;
        } else if (edellinen != null) {
            //the variable edellinen should actually never be null if the first
            //if-statement fails, might be good to check anyways,
            //especially considering future development.
            edellinen.peru();
            edellinen = null;
        }
    }
    
}
