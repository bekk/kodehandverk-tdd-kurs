package player;

import org.junit.Test;
import static org.junit.Assert.*;
public class PlayerTest {

    @Test
    public void shouldGetPlayerName(){
        Player player = new Player("Anonym Rakkefant");
        assertEquals(player.getName(), "Anonym Rakkefant");
    }
}
