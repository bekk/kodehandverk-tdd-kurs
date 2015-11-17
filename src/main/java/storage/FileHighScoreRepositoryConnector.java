package storage;

import com.javafx.tools.doclets.internal.toolkit.util.DocFinder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import player.Player;

import javax.annotation.Resources;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class FileHighScoreRepositoryConnector {
    final String FILE_NAME = "HighScoreDatabase.json";

    private final JSONArray getCurrentHighscores() throws IOException, ParseException {
        File file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());
        JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(file));
        JSONArray scores = (JSONArray) data.get("scores");
        return scores;
    }

    public Map<Player, Integer> readDatabase() throws IOException, ParseException {
        Map<Player, Integer> highScores = new HashMap<Player, Integer>();
        JSONArray scores = getCurrentHighscores();
        for(int i = 0; i< scores.size(); i++) {
            JSONObject highScore = (JSONObject) scores.get(i);
            String name = (String) highScore.get("name");
            int score = ((Long)highScore.get("score")).intValue();
            highScores.put(new Player(name), score);
        }
        return highScores;
    }

    public boolean storeHighscores(Map<Player, Integer> highscores) throws URISyntaxException, IOException {
        JSONObject topLevelObject = new JSONObject();
        JSONArray scores = new JSONArray();

        for(Player player : highscores.keySet()) {
            JSONObject obj = new JSONObject();
            obj.put("name", player.getName());
            obj.put("score", highscores.get(player));
            scores.add(obj);
        }
        topLevelObject.put("scores", scores);
        OutputStream outputStream = new FileOutputStream(getClass().getClassLoader().getResource(FILE_NAME).getFile());

        outputStream.write(topLevelObject.toJSONString().getBytes());
        outputStream.close();
        return true;
    }
}