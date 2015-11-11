package scorer;

import scorer.BowlingScorer;
import org.junit.*;

import static org.junit.Assert.assertEquals;

//Credit to ihaworth at Github.
//https://github.com/ihaworth
public class BowlingScorerTest {

    private BowlingScorer bowlingScorer;

    @Before
    public void setup()
    {
        bowlingScorer = new BowlingScorer();
    }

    @Test
    public void should_score_zero_when_gutter_game()
    {
        rollMany(20, 0);
        assertEquals(0, bowlingScorer.score());
    }

    @Test
    public void should_score_20_when_1_hit_in_every_frame()
    {
        rollMany(20, 1);
        assertEquals(20, bowlingScorer.score());
    }

    @Test
    public void should_get_spare_bonus_when_spare_is_rolled()
    {
        rollSpare(5);
        bowlingScorer.roll(3);
        rollMany(17, 0);
        assertEquals(16, bowlingScorer.score());
    }

    @Test
    public void should_get_strike_bonus_when_strike()
    {
        rollStrike();
        bowlingScorer.roll(3);
        bowlingScorer.roll(4);
        rollMany(16, 0);
        assertEquals(24, bowlingScorer.score());
    }

    @Test
    public void should_get_300_when_perfect_game_occurs()
    {
        rollMany(12, 10);
        assertEquals(300, bowlingScorer.score());
    }

    //Test helpers
    private void rollStrike()
    {
        bowlingScorer.roll(10);
    }

    private void rollSpare(int pinsInFirstRoll)
    {
        bowlingScorer.roll(pinsInFirstRoll);
        bowlingScorer.roll(10 - pinsInFirstRoll);
    }

    private void rollMany(int numberOfRolls, int pins)
    {
        for (int i = 0; i < numberOfRolls; i++)
            bowlingScorer.roll(pins);
    }
}
