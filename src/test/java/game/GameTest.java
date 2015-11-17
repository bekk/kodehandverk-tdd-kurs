package game;

import display.MockDisplay;
import lightning.LightningMock;
import org.junit.Test;
import player.Player;
import storage.MockHighScoreRepository;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class GameTest {
    private final String DEFAULT_PLAYER_NAME = "Torstein";

    /*

    Her vil vi teste at et spill ikke kan starte uten at spillere er "registrert" i Enterprise bowling systemet.

    @Test(expected = Exception.class)
    public void should_throw_exception_when_no_players_present_at_gamestart(){

    }
    */

    /*
    Her vil vi teste at et spill starter opp dersom en eller flere spillere har registrert seg.
     */
    @Test
    public void should_start_game_when_one_or_more_players_present_at_gamestart() {

    }

    @Test()
    public void should_turn_on_lights_when_game_starts() {
        LightningMock lightningMock = new LightningMock();
        AdjustableClock adjustableClock = new AdjustableClock();
        adjustableClock.setClock(LocalDateTime.of(2015,11,16,8,0));// Monday morning

        Game game = createGameWithPlayers();
        game.setClock(adjustableClock);
        game.setLightning(lightningMock);
        game.startGame();

        assertTrue(lightningMock.turnedOnlights);
    }

    @Test
    public void should_turn_on_disco_lights_when_game_starts() {
        LightningMock lightningMock = new LightningMock();

        LocalDateTime fridayFiveOClock = LocalDateTime.of(2015, 11, 13, 17, 00);
        AdjustableClock adjustableClock = new AdjustableClock();
        adjustableClock.setClock(fridayFiveOClock);
        Game game = createGameWithPlayers();
        game.setClock(adjustableClock);
        game.setLightning(lightningMock);
        game.startGame();

        assertTrue(lightningMock.turnedOnDiscoLights);
    }


    @Test
    public void should_turn_on_disco_lights_when_starting_game_on_friday_23_59() {
        LightningMock lightningMock = new LightningMock();

        LocalDateTime fridayFiveOClock = LocalDateTime.of(2015, 11, 13, 23, 59);
        AdjustableClock adjustableClock = new AdjustableClock();
        adjustableClock.setClock(fridayFiveOClock);
        Game game = createGameWithPlayers();
        game.setClock(adjustableClock);
        game.setLightning(lightningMock);
        game.startGame();
        assertTrue(lightningMock.turnedOnDiscoLights);
    }

    @Test
    public void should_turn_on_regular_lights_when_starting_game_on_saturday_00_00() {
        LightningMock lightningMock = new LightningMock();

        LocalDateTime fridayFiveOClock = LocalDateTime.of(2015, 11, 14, 00, 00);
        AdjustableClock adjustableClock = new AdjustableClock();
        adjustableClock.setClock(fridayFiveOClock);
        Game game = createGameWithPlayers();
        game.setClock(adjustableClock);
        game.setLightning(lightningMock);
        game.startGame();

        assertTrue(lightningMock.turnedOnlights);
    }

    @Test
    public void should_turn_on_regular_lights_when_starting_game_on_friday_16_59() {
        LightningMock lightningMock = new LightningMock();

        LocalDateTime fridayFiveOClock = LocalDateTime.of(2015, 11, 13, 16, 59);
        AdjustableClock adjustableClock = new AdjustableClock();
        adjustableClock.setClock(fridayFiveOClock);
        Game game = createGameWithPlayers();
        game.setClock(adjustableClock);
        game.setLightning(lightningMock);
        game.startGame();

        assertTrue(lightningMock.turnedOnlights);
    }

    private Game createGameWithPlayers() {
        Game game = new Game(new MockDisplay(), new LightningMock(), new MockHighScoreRepository(), new SystemClock());
        game.addPlayer(new Player(DEFAULT_PLAYER_NAME));
        return game;
    }

    @Test(expected = IllegalStateException.class)
    public void should_throw_exception_on_empty_game(){
        Game game = new Game(null, null, null);
        game.startGame();
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_on_null_player(){
        Game game = new Game(null, null, null);
        game.addPlayer(null);
    }

    @Test
    public void should_not_update_if_not_new_highscore() throws Exception {
        MockHighScoreRepository mockHighScoreRepository = new MockHighScoreRepository();
        mockHighScoreRepository.lowestHighScore = 300;

        Game game = createGameWithPlayers();
        game.setHighScoreRepository(mockHighScoreRepository);
        game.roll(10);
        game.endGame();

        assertThat(mockHighScoreRepository.updatedHighScores, is(false));
    }

    @Test
    public void should_update_if_new_highscore() throws Exception {
        MockHighScoreRepository mockHighScoreRepository = new MockHighScoreRepository();
        mockHighScoreRepository.lowestHighScore = 1;

        Game game = createGameWithPlayers();
        game.setHighScoreRepository(mockHighScoreRepository);
        game.roll(10);
        game.endGame();

        assertThat(mockHighScoreRepository.updatedHighScores, is(true));
    }

    @Test
    public void should_announce_next_player_on_start() {
        MockDisplay mockDisplay = new MockDisplay();

        Game game = createGameWithPlayers();
        game.setDisplay(mockDisplay);
        game.setLightning(new LightningMock());
        game.startGame();

        assertThat(mockDisplay.showedCallPlayerToAction, is(true));
        assertThat(mockDisplay.lastPlayerCalledToAction.getName(), is(DEFAULT_PLAYER_NAME));
    }
}
