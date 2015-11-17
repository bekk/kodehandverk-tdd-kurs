package storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import player.Player;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public final class FileHighScoreRepositoryConnector {
    final String FILE_NAME = "/HighScoreDatabase.json";

    private final JSONArray getCurrentHighscores() throws IOException, ParseException {
        File file = new File(getDatabaseFile());
        JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(file));
        JSONArray scores = (JSONArray) data.get("scores");
        return scores;
    }

    public Map<Player, Integer> readDatabase() throws IOException, ParseException {
        Map<Player, Integer> highScores = new HashMap<Player, Integer>();

        JSONArray scores = getCurrentHighscores();
        for (int i = 0; i < scores.size(); i++) {
            JSONObject highScore = (JSONObject) scores.get(i);
            String name = (String) highScore.get("name");
            int score = ((Long) highScore.get("score")).intValue();
            highScores.put(new Player(name), score);
        }
        return highScores;
    }

    public void storeHighscores(Map<Player, Integer> highscores) throws URISyntaxException, IOException {
        JSONObject topLevelObject = new JSONObject();
        JSONArray scores = new JSONArray();

        // Creates a object structure like this:
        // [{
        //    name: "Player name 1",
        //    score: 200
        //  },
        //  {
        //    name: "Player name 2",
        //    score: 150
        // }]
        for (Player player : highscores.keySet()) {
            JSONObject obj = new JSONObject();
            obj.put("name", player.getName());
            obj.put("score", highscores.get(player));
            scores.add(obj);
        }

        topLevelObject.put("scores", scores);

        // Pro-tip: try (...) gjør at ressursen (filen i dette tilfellet uansett lukkes
        // etter den er lest (også om noe feiler)
        try ( OutputStream outputStream = new FileOutputStream(getDatabaseFile(), false) ) {
            outputStream.write(topLevelObject.toJSONString().getBytes());
        }
    }

    private String getDatabaseFile() {
        return getClass().getResource(FILE_NAME).getFile();
    }
}