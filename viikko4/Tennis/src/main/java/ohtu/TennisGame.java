package ohtu;

public class TennisGame {

    private final String[] POINTS_STRING_REPRESENTATIVE = {"Love", "Fifteen", "Thirty", "Forty"};
    private final String player1Name;
    private final String player2Name;
    private int player1Points;
    private int player2Points;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Points = 0;
        this.player2Points = 0;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Points++;
        } else {
            player2Points++;
        }
    }

    public String getScore() {
        if (playersHaveEvenPoints()) {
            return evenScoreRepresentation();
        }

        if (player1Points < 4 && player2Points < 4) {
            return bothPlayersHaveUnderFourPointsRepresentation();
        }
        
        if (gameIsWon()) {
            if (playerOneHasMorePoints()) {
                return "Win for " + player1Name;
            }
            return "Win for " + player2Name;
        }
        
        if (playerOneHasMorePoints()) {
            return "Advantage " + player1Name;
        }
        
        return "Advantage " + player2Name;
    }
    
    public boolean gameIsWon() {
        return Math.abs(pointDifference()) > 1;
    }
    
    private boolean playersHaveEvenPoints() {
        return pointDifference() == 0;
    }
    
    private int pointDifference() {
        return player1Points - player2Points;
    }

    private boolean playerOneHasMorePoints() {
        return pointDifference() > 0;
    }
    
    private String bothPlayersHaveUnderFourPointsRepresentation() {
        return POINTS_STRING_REPRESENTATIVE[player1Points] + "-"
                + POINTS_STRING_REPRESENTATIVE[player2Points];
    }
    
    private String evenScoreRepresentation() {
        return player1Points < 4 
                ? POINTS_STRING_REPRESENTATIVE[player1Points] + "-All"
                : "Deuce";
    }

}
