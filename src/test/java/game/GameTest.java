package game;

import display.MockDisplay;
import lightning.LightningMock;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import player.Player;
import storage.MockHighScoreRepository;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTest {
    private final String DEFAULT_PLAYER_NAME = "Torstein";


    // Tests for lightning
    // ========================================================================

    @Test()
    public void should_turn_on_lights_when_game_starts() {
        LightningMock lightningMock = new LightningMock();
        AdjustableClock adjustableClock = new AdjustableClock();
        adjustableClock.setClock(new LocalDateTime(2015, 11, 16, 8, 0));// Monday morning

        Game game = createGameWithPlayers();
        game.setClock(adjustableClock);
        game.setLightning(lightningMock);
        game.startGame();

        assertThat(lightningMock.turnedOnlights, is(true));
    }

    @Test
    public void should_turn_on_disco_lights_when_game_starts() {
        LightningMock lightningMock = new LightningMock();

        LocalDateTime fridayFiveOClock = new LocalDateTime(2015, 11, 13, 17, 00);
        AdjustableClock adjustableClock = new AdjustableClock();
        adjustableClock.setClock(fridayFiveOClock);

        Game game = createGameWithPlayers();
        game.setClock(adjustableClock);
        game.setLightning(lightningMock);
        game.startGame();

        assertThat(lightningMock.turnedOnDiscoLights, is(true));
    }

    @Test
    public void should_turn_on_disco_lights_when_starting_game_on_friday_23_59() {
        LightningMock lightningMock = new LightningMock();

        LocalDateTime fridayFiveOClock = new LocalDateTime(2015, 11, 13, 23, 59);
        AdjustableClock adjustableClock = new AdjustableClock();
        adjustableClock.setClock(fridayFiveOClock);

        Game game = createGameWithPlayers();
        game.setClock(adjustableClock);
        game.setLightning(lightningMock);
        game.startGame();

        assertThat(lightningMock.turnedOnDiscoLights, is(true));
    }

    @Test
    public void should_turn_on_regular_lights_when_starting_game_on_saturday_00_00() {
        LightningMock lightningMock = new LightningMock();

        LocalDateTime fridayFiveOClock = new LocalDateTime(2015, 11, 14, 00, 00);
        AdjustableClock adjustableClock = new AdjustableClock();
        adjustableClock.setClock(fridayFiveOClock);

        Game game = createGameWithPlayers();
        game.setClock(adjustableClock);
        game.setLightning(lightningMock);
        game.startGame();

        assertThat(lightningMock.turnedOnlights, is(true));
    }

    @Test
    public void should_turn_on_regular_lights_when_starting_game_on_friday_16_59() {
        LightningMock lightningMock = new LightningMock();

        LocalDateTime fridayFiveOClock = new LocalDateTime(2015, 11, 13, 16, 59);
        AdjustableClock adjustableClock = new AdjustableClock();
        adjustableClock.setClock(fridayFiveOClock);
        Game game = createGameWithPlayers();
        game.setClock(adjustableClock);
        game.setLightning(lightningMock);
        game.startGame();

        assertThat(lightningMock.turnedOnlights, is(true));
    }

    @Test
    public void should_turn_off_the_light_when_the_game_ends() throws Exception {
        LightningMock lightningMock = new LightningMock();

        Game game = createGameWithPlayers();
        game.setLightning(lightningMock);
        game.endGame();

        assertThat(lightningMock.turnedOffLights, is(true));
    }


    // Test game start and player registration
    // ========================================================================

    private Game createGameWithPlayers() {
        Game game = new Game(new MockDisplay(), new LightningMock(), new MockHighScoreRepository());
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

    // Test highscoring logic
    // ========================================================================

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

    // Test display
    // ========================================================================

    @Test
    public void should_show_strike_animation_on_strike() {
        MockDisplay mockDisplay = new MockDisplay();

        Game game = createGameWithPlayers();
        game.setDisplay(mockDisplay);
        game.startGame();
        game.roll(10);

        assertThat(mockDisplay.showedStrikeAnimation, is(true));
    }

    @Test
    public void should_not_show_strike_animation_on_spare() {
        MockDisplay mockDisplay = new MockDisplay();

        Game game = createGameWithPlayers();
        game.setDisplay(mockDisplay);
        game.startGame();
        game.roll(9);
        game.roll(1);

        assertThat(mockDisplay.showedStrikeAnimation, is(false));
    }

    @Test
    public void should_announce_next_player_on_start() {
        MockDisplay mockDisplay = new MockDisplay();

        Game game = createGameWithPlayers();
        game.setDisplay(mockDisplay);
        game.startGame();

        assertThat(mockDisplay.showedCallPlayerToAction, is(true));
        assertThat(mockDisplay.lastPlayerCalledToAction.getName(), is(DEFAULT_PLAYER_NAME));
    }

    @Test
    public void should_announce_next_player_after_a_strike() {
        MockDisplay mockDisplay = new MockDisplay();

        Game game = createGameWithPlayers();
        game.addPlayer(new Player("Player 2"));
        game.setDisplay(mockDisplay);
        game.startGame();

        game.roll(10); // Player 1 rolls a strike and it's the next players turn

        assertThat(mockDisplay.lastPlayerCalledToAction.getName(), is("Player 2"));
    }

    @Test
    public void should_announce_first_player_when_second_player_strikes() {
        MockDisplay mockDisplay = new MockDisplay();

        Game game = createGameWithPlayers();
        game.addPlayer(new Player("Player 2"));
        game.setDisplay(mockDisplay);
        game.startGame();

        game.roll(10); // Player 1 rolls a strike and it's the next players turn
        game.roll(10); // Player 2 rolls a strike and it's the first players turn

        assertThat(mockDisplay.lastPlayerCalledToAction.getName(), is(DEFAULT_PLAYER_NAME));
    }
}
