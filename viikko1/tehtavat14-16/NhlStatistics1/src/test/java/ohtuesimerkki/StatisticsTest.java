package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;

public class StatisticsTest {
    
    Reader readerStub = new Reader() {
        
        @Override
        public List<Player> getPlayers() {
            List<Player> players = new ArrayList<>();
            
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
    
    Statistics stats;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
}
