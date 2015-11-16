package storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import player.Player;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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
