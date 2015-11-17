package game;

import display.Display;
import lightning.Lightning;
import player.Player;
import scorer.BowlingScorer;
import storage.HighScoreRepository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Vi kunne sikkert hatt et bedre navn på denne klassen.
 * Denne klassen holder på tilstand og styrer spillet. Den orkestrerer
 * alt som skal skje i spillet.
 */
public class Game {
    public static final int STRIKE = 10;
    // Dependencies
    private Display display;
    private Lightning lightning;
    private HighScoreRepository highScoreRepository;
    private Clock clock;

    // State
    private Map<Player, BowlingScorer> playerScores;
    private List<Player> players;
    private int round = 0;

    /**
     * Lager et nytt spill. Krever at du angir en instans av alle avhengighetene.
     *
     * @param display
     * @param lightning
     * @param highScoreRepository
     */
    public Game(Display display, Lightning lightning, HighScoreRepository highScoreRepository) {
        this.display = display;
        this.lightning = lightning;
        this.highScoreRepository = highScoreRepository;
        this.clock = new SystemClock(); // Denne kan du override med settern i testen

        // Pro-tip: Initialiser lister o.l. Da slipper dere null-pointere.
        this.playerScores = new HashMap<Player, BowlingScorer>();
        this.players = new ArrayList<Player>();
    }

    public void addPlayer(Player player) {
        if (player == null)
            throw new IllegalArgumentException();

        // legg merke til at vi legger til i to lister; en for score og en for rekkefølge
        playerScores.put(player, new BowlingScorer());
        players.add(player);
    }

    public void startGame() {
        if (players.isEmpty())
            throw new IllegalStateException("kan ikke starte uten spillere, n00b");

        // Begynn med å vise velkomstskjermen
        display.showWelcomeScreen();


        LocalDateTime currentTime = this.clock.now();
        if (currentTime.getDayOfWeek() == DayOfWeek.FRIDAY && currentTime.getHour() >= 17) {
            // DISCO-MODE!!!
            this.lightning.turnOnDiscoLights();
        } else {
            // Normal (boring) mode...
            this.lightning.turnOnLights();
        }

        callPlayerToAction();
    }

    private void callPlayerToAction() {
        // Finner neste spiller med litt modulus-magi
        Player currentPlayer = players.get(round % players.size());
        display.callPlayerToAction(currentPlayer);
    }

    public void endGame() throws Exception {

        for (Map.Entry<Player, BowlingScorer> playerScore : playerScores.entrySet()) {
            int score = playerScore.getValue().score();
            Player player = playerScore.getKey();

            if (highScoreRepository.isNewHighScore(score)) {
                highScoreRepository.updateHighScore(player, score);
            }
        }

        lightning.turnOffLights();
        this.round = 0;
    }

    public void roll(int pinsHit) {
        Player currentPlayer = players.get(round % players.size());
        BowlingScorer scorer = playerScores.get(currentPlayer);
        boolean roundIsFinished = scorer.roll(pinsHit);

        if(pinsHit == STRIKE) {
            display.showStrikeAnimation();
        }

        if(roundIsFinished) {
            round = round + 1;
            callPlayerToAction();
        }
    }


    public void setDisplay(Display display) {
        this.display = display;
    }

    public void setLightning(Lightning lightning) {
        this.lightning = lightning;
    }

    public void setHighScoreRepository(HighScoreRepository highScoreRepository) {
        this.highScoreRepository = highScoreRepository;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

}
