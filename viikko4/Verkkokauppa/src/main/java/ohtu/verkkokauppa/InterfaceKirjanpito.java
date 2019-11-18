package ohtu.verkkokauppa;

import java.util.ArrayList;

public interface InterfaceKirjanpito {

    ArrayList<String> getTapahtumat();

    void lisaaTapahtuma(String tapahtuma);
    
}
