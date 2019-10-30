package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
    String playername;
    List<Player> playersSorted;
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
        playername = "Gretzky";
        playersSorted = new ArrayList<>();
        playersSorted.add(new Player("Gretzky", "EDM", 35, 89));
        playersSorted.add(new Player("Lemieux", "PIT", 45, 54));
        playersSorted.add(new Player("Yzerman", "DET", 42, 56));
        playersSorted.add(new Player("Kurri",   "EDM", 37, 53));
        playersSorted.add(new Player("Semenko", "EDM", 4, 12));
    }
    
    @Test
    public void searchReturnsNullWhenPlayerNotFound() {
        assertNull(stats.search("Koivu"));
    }
    
    @Test
    public void searchReturnsPlayerWithCorrectName() {
        Player p = stats.search(playername);
        assertEquals(playername, p.getName());
    }
    
    @Test
    public void invalidTeamHasNoPlayers() {
        List<Player> teamMembers = stats.team("JKR");
        assertEquals(0, teamMembers.size());
    }
    
    @Test
    public void existingTeamHasRightPlayers() {
        List<Player> expectedPlayers = new ArrayList<>();
        expectedPlayers.add(new Player("Semenko", "EDM", 4, 12));
        expectedPlayers.add(new Player("Kurri",   "EDM", 37, 53));
        expectedPlayers.add(new Player("Gretzky", "EDM", 35, 89));
        
        List<Player> teamMembers = stats.team("EDM");
        assertTrue(compareTeamLists(expectedPlayers, teamMembers));
    }
    
    @Test
    public void topScoresIsEmptyWhenPassedZero() {
        assertEquals(0, stats.topScorers(0).size());
    }
    
    @Test
    public void topScoresContainsCorrectPlayerWhenPassedOne() {
        List<Player> topScorer = stats.topScorers(1);
        assertEquals(1, topScorer.size());
        assertEquals(playersSorted.get(0).toString(),
                topScorer.get(0).toString());
    }
    
    @Test
    public void topScorerContainsCorrectPlayersWhenPassedTwo() {
        List<Player> topScorers = stats.topScorers(2);
        assertTrue(compareTopScorerLists(2, playersSorted, topScorers));
    }
    
    @Test
    public void topScorerContainsCorrectPlayersWhenPassedFive() {
        List<Player> topScorers = stats.topScorers(5);
        assertTrue(compareTopScorerLists(5, playersSorted, topScorers));
    }
        
    /**
     * Compares two List<Player> objects. Iterates trough the lists and compares
     * the players.
     * @param t1
     * @param t2
     * @return true, if both lists contains the same players, false otherwise
     */
    private boolean compareTeamLists(List<Player> t1, List<Player> t2) {
        if (t1.size() != t2.size()) return false;
        
        for (int i = 0; i < t1.size(); i++) {
            if (!t1.get(i).getName().equals(t2.get(i).getName())) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean compareTopScorerLists(int howMany, List<Player> t1, List<Player> t2) {
        if (t2.size() != howMany) return false;
        
        for (int i = 0; i < howMany; i++) {
            if (!t1.get(i).toString().equals(t2.get(i).toString())) {
                return false;
            }
        }
        
        return true;
    }
}
