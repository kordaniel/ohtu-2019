/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Request;
import java.io.IOException;
import java.util.Arrays;
import java.util.GregorianCalendar;


/**
 *
 * @author mluukkai
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        String bodyText = Request.Get(url).execute().returnContent().asString();
        
        //System.out.println("json-muotoinen data:");
        //System.out.println(bodyText);
        
        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        
        printSortedPlayersFromCountry(players, "FIN");
        //System.out.println("Oliot:");
        //for (Player player : players) {
        //    if (player.getNationality().toLowerCase().equals("fin")) {
        //        System.out.println(player);
        //    }
        //}
        
        
    }
    
    static void printSortedPlayersFromCountry(Player[] players, String country) {
        System.out.println("Players from " + country + " " + new GregorianCalendar().getTime());
        System.out.println("");
        
        Arrays.stream(players)
                .filter(p -> p.getNationality().toLowerCase().equals(country.toLowerCase()))
                .sorted((p1,p2) -> p2.getTotalScore() - p1.getTotalScore())                
                .forEach(p -> {
                    
                    String playerStr = "%-" + 25 + "s %3s %2d + %2d = %3d%n";
                    System.out.format(
                            playerStr,
                            p.getName(),
                            p.getTeam(),
                            p.getGoals(),
                            p.getAssists(),
                            p.getTotalScore()
                    );
                });
    }
    
}
