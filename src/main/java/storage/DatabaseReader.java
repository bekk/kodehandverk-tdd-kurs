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

public final class DatabaseReader {
        final String FILE_NAME = "HighScoreDatabase.json";

        private final JSONArray getCurrentHighscores() throws IOException, ParseException {
            File file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(file));
            JSONArray scores = (JSONArray) data.get("scores");
            return scores;
        }

        final Map<Player, Integer> readDatabase() throws IOException, ParseException {
            Map<Player, Integer> highScores = new HashMap<Player, Integer>();
            JSONArray scores = getCurrentHighscores();
            for(int i = 0; i< scores.size(); i++) {
                JSONObject highScore = (JSONObject) scores.get(i);
                String name = (String) highScore.get("name");
                int score = (Integer)highScore.get("score");
                highScores.put(new Player(name), score);
            }
            return highScores;
        }
    }