package storage;

import player.Player;

import java.util.Map;

public class FileHighScoreRepository implements HighScoreRepository {



    public Map<Player, Integer> getHighScores() throws Exception {
        return null;
    }

    public boolean isNewHighScore(int score) throws Exception {
        return false;
    }

    public void updateHighScore(int score) throws Exception {

    }
}
