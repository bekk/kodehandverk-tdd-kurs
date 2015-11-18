package storage;

import org.json.simple.parser.ParseException;
import org.junit.*;
import player.Player;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

// Her skal dere skrive integrasjonstester
public class FileHighScoreRepositoryTest {
    @Test
    public void test_database_should_contain_two_items() throws IOException, ParseException {

        assertEquals(new FileHighScoreRepositoryConnector().readDatabase().size(), 2);
    }

    @Test
    public void test_database_should_store_items() throws IOException, ParseException, URISyntaxException {
        //Tester per nå at ingen exceptions blir kastet...
        FileHighScoreRepositoryConnector reader = new FileHighScoreRepositoryConnector();
        Map<Player, Integer> updatedHighscores = new HashMap<Player, Integer>();
        updatedHighscores.put(new Player("Esben"), 299);
        updatedHighscores.put(new Player("Torstein"), 22);
        reader.storeHighscores(updatedHighscores);
    }
}
