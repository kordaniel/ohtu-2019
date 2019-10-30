package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Statistics {

    private List<Player> players;

    public Statistics(Reader reader) {
        players = reader.getPlayers();       
    }

    public Player search(String name) {
        for (Player player : players) {
            if (player.getName().contains(name)) {
                return player;
            }
        }

        return null;
    }

    public List<Player> team(String teamName) {
        ArrayList<Player> playersOfTeam = new ArrayList<Player>();
        
        for (Player player : players) {
            if ( player.getTeam().equals(teamName)) {
                playersOfTeam.add(player);
            }
        }
        
        return playersOfTeam;
    }

    public List<Player> topScorers(int howMany) {
        Collections.sort(players);
        ArrayList<Player> topScorers = new ArrayList<Player>();
        Iterator<Player> playerIterator = players.iterator();
        
        //this should probably return an list containing <howMany> players,
        //ie. an list containing the amount of players passed as an argument
        //and not <howmany>+1?
        //=> bugfix
        //while (howMany>=0) {
        while (howMany>0) {
            topScorers.add( playerIterator.next() );            
            howMany--;
        }
        
        return topScorers;
    }

}
