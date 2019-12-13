package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends KiviPaperSakset {

    public KPSTekoaly(Scanner scanner) {
        super(scanner);
    }

    @Override
    public void pelaa() {
        tekoaly = new TekoalyImpl();

        pelaaTekoalyaVastaan();
    }
}