package storage;

import player.Player;
import java.util.Map;

public interface HighScoreRepository {
    Map<Player, Integer> getHighScores() throws Exception;
    boolean isNewHighScore(int score) throws Exception;
    void updateHighScore(Player player, int score) throws Exception;
}
