package storage;

import player.Player;

import java.util.Map;

public class FileHighScoreRepository implements HighScoreRepository {

    // Hm... Hadde det bare vært en komponent som kunne hjulpet meg å lese og skrive JSON-filer...

    public Map<Player, Integer> getHighScores() throws Exception {
        return null;
    }

    public boolean isNewHighScore(int score) throws Exception {
        return false;
    }

    @Override
    public void updateHighScore(Player player, int score) throws Exception {

    }

    public void updateHighScore(int score) throws Exception {

    }
}
