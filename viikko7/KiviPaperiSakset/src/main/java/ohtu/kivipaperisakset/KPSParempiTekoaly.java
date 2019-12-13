package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSParempiTekoaly extends KiviPaperSakset {

    public KPSParempiTekoaly(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void pelaa() {
        tekoaly = new TekoalyParannettuImpl(20);

        pelaaTekoalyaVastaan();
    }

}
