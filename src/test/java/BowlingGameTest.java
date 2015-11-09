import org.junit.*;

import static org.junit.Assert.assertEquals;

//Credit to ihaworth at Github.
//https://github.com/ihaworth
public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @Before
    public void setup()
    {
        bowlingGame = new BowlingGame();
    }

    @Test
    public void should_score_zero_when_gutter_game()
    {
        rollMany(20, 0);
        assertEquals(0, bowlingGame.score());
    }

    @Test
    public void should_score_20_when_1_hit_in_every_frame()
    {
        rollMany(20, 1);
        assertEquals(20, bowlingGame.score());
    }

    @Test
    public void should_get_spare_bonus_when_spare_is_rolled()
    {
        rollSpare(5);
        bowlingGame.roll(3);
        rollMany(17, 0);
        assertEquals(16, bowlingGame.score());
    }

    @Test
    public void should_get_strike_bonus_when_strike()
    {
        rollStrike();
        bowlingGame.roll(3);
        bowlingGame.roll(4);
        rollMany(16, 0);
        assertEquals(24, bowlingGame.score());
    }

    @Test
    public void should_get_300_when_perfect_game_occurs()
    {
        rollMany(12, 10);
        assertEquals(300, bowlingGame.score());
    }

    //Test helpers
    private void rollStrike()
    {
        bowlingGame.roll(10);
    }

    private void rollSpare(int pinsInFirstRoll)
    {
        bowlingGame.roll(pinsInFirstRoll);
        bowlingGame.roll(10 - pinsInFirstRoll);
    }

    private void rollMany(int numberOfRolls, int pins)
    {
        for (int i = 0; i < numberOfRolls; i++)
            bowlingGame.roll(pins);
    }
}
