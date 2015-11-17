package storage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import player.Player;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Denne klassen har vi laget for dere.
 * Den kan lese og skrive til en JSON-fil.
 */
@SuppressWarnings("unchecked")
public final class FileHighScoreRepositoryConnector {
    // Filen finner dere i resources.
    private final String FILE_NAME = "/HighScoreDatabase.json";

    /**
     * Leser filen og returnerer en enkel datastruktur for å representere en highscore-tabell
     *
     * @throws IOException
     * @throws ParseException
     */
    public Map<Player, Integer> readDatabase() throws IOException, ParseException {
        Map<Player, Integer> highScores = new HashMap<>();

        JSONArray scores = getCurrentHighscores();
        for (Object score : scores) {
            JSONObject highScore = (JSONObject) score;
            String playerName = (String) highScore.get("name");
            int playerScore = ((Long) highScore.get("score")).intValue();
            highScores.put(new Player(playerName), playerScore);
        }
        return highScores;
    }

    /**
     * Lagrer (overskriver) en highscore-tabell til fil.
     * Her må du passe på å lese opp eksisterende tabell og putte inn ny score.
     *
     * @throws IOException
     */
    public void storeHighscores(Map<Player, Integer> highscores) throws IOException {
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

    private JSONArray getCurrentHighscores() throws IOException, ParseException {
        File file = new File(getDatabaseFile());
        JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(file));
        return (JSONArray) data.get("scores");
    }

    private String getDatabaseFile() {
        return getClass().getResource(FILE_NAME).getFile();
    }
}