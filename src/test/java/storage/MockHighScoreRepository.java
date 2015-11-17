package storage;

import player.Player;

import java.util.HashMap;
import java.util.Map;

public class MockHighScoreRepository implements HighScoreRepository {
    public int lowestHighScore;
    public boolean updatedHighScores;

    public Map<Player, Integer> getHighScores() throws Exception {
        return new HashMap<Player, Integer>();
    }

    public boolean isNewHighScore(int score) throws Exception {
        return score > lowestHighScore;
    }

    public void updateHighScore(int score) throws Exception {
        updatedHighScores = true;
    }

    public void updateHighScore(Player player, int score) throws Exception {
        updatedHighScores = true;
    }
}
