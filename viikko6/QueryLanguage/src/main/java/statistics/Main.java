package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        // seuraavassa osoitteessa 27.11.2019 päivitetyt tilastot
        String url = "https://nhl27112019.herokuapp.com/players.txt";
        // ajan tasalla olevat tilastot osoitteessa
        //String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
        
        /*
        Matcher m = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );*/
        /*
        Matcher m = new And(
                new Not(new HasAtLeast(1, "goals")),
                new PlaysIn("NYR")
        );
        */
        /*
        Matcher m = new And(
                new HasFewerThan(1, "goals"),
                new PlaysIn("NYR")
        );*/
        /*
        Matcher m = new All();
        System.out.println(stats.matches(m).size());
        */
        /*
        Matcher m = new Or(
                new HasAtLeast(20, "goals"),
                new HasAtLeast(20, "assists")
        );
        */
        /*
        Matcher m = new Not(new Or(
                new HasAtLeast(20, "goals"),
                new HasAtLeast(20, "assists")
        ));
        */
        /*
        Matcher m = new And(
                new HasAtLeast(20, "points"),
                new Or(
                    new PlaysIn("NYR"),
                    new PlaysIn("NYI"),
                    new PlaysIn(("NJD"))
                )
        );*/
        
        QueryBuilder query = new QueryBuilder();
        /*
        Matcher m = query.playsIn("NYR")
                         .hasAtLeast(5, "goals")
                         .hasFewerThan(10, "goals")
                         .build();
        */
        /*
        Matcher m1 = query.playsIn("PHI")
                          .hasAtLeast(10, "assists")
                          .hasFewerThan(8, "goals")
                          .build();
        
        Matcher m2 = query.playsIn("EDM")
                          .hasAtLeast(20, "points")
                          .build();
        
        Matcher m = query.oneOf(m1, m2).build();
        */
        Matcher m = query.oneOf(
                query.playsIn("PHI")
                    .hasAtLeast(10, "assists")
                    .hasFewerThan(8, "goals").build(),
                
                query.playsIn("EDM")
                    .hasAtLeast(20, "points").build()
        ).build();
        /*
        Matcher m = query.oneOf(
                query.playsIn("PHI")
                    .hasAtLeast(10, "assists")
                    .hasFewerThan(8, "goals").build(),
                
                query.playsIn("EDM")
                    .hasAtLeast(20, "points").build(),
                
                query.playsIn("NYR")
                    .hasAtLeast(10, "goals").build()
        ).build();
        */
        
        stats.matches(m).forEach((player) -> {
            System.out.println(player);
        });
        
    }
}
