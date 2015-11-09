import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//Credit to ihaworth at Github.
//https://github.com/ihaworth
public class LastFrameTest
{
    private LastFrame lastFrame;

    @Before
    public void setup()
    {
        lastFrame = new LastFrame();
    }

    private void rollStrike()
    {
        lastFrame.roll(10);
    }

    private void rollSpare(int pins)
    {
        lastFrame.roll(pins);
        lastFrame.roll(10 - pins);
    }


    @Test
    public void should_not_be_over_with_one_strike()
    {
        rollStrike();

        assertFalse(lastFrame.isOver());
    }

    @Test
    public void should_not_be_over_with_one_strike_followed_by_one_roll()
    {
        rollStrike();
        lastFrame.roll(5);

        assertFalse(lastFrame.isOver());
    }

    @Test
    public void should_be_over_with_one_strike_followed_by_two_rolls()
    {
        rollStrike();
        lastFrame.roll(5);
        lastFrame.roll(3);

        assertTrue(lastFrame.isOver());
    }

    @Test
    public void should_not_be_over_when_spare_is_thrown()
    {
        rollSpare(3);

        assertFalse(lastFrame.isOver());
    }

    @Test
    public void should_be_over_when_spare_followed_by_one_roll()
    {
        rollSpare(3);
        lastFrame.roll(6);

        assertTrue(lastFrame.isOver());
    }

    @Test
    public void should_not_be_over_when_one_roll()
    {
        lastFrame.roll(3);

        assertFalse(lastFrame.isOver());
    }

    @Test
    public void should_be_over_when_two_rolled_and_there_are_pins_left()
    {
        lastFrame.roll(3);
        lastFrame.roll(5);

        assertTrue(lastFrame.isOver());
    }

    @Test
    public void should_score_similar_to_ordinary_frame_when_pins_are_left_after_two_rolls()
    {
        lastFrame.roll(2);
        lastFrame.roll(6);

        assertEquals(8, lastFrame.scoreWithBonus());
    }

    @Test
    public void should_score_equal_to_total_pins_hit_in_three_rolls_when_spare_is_thrown()
    {
        rollSpare(4);
        lastFrame.roll(2);

        assertEquals(12, lastFrame.scoreWithBonus());
    }

    @Test
    public void should_score_equal_to_total_hits_in_three_rolls_when_strike_is_thrown()
    {
        rollStrike();
        lastFrame.roll(6);
        lastFrame.roll(2);

        assertEquals(18, lastFrame.scoreWithBonus());
    }

    @Test
    public void should_give_strike_bonus_equal_to_hits_in_first_two_rolls_for_previous_frame_when_strike_was_thrown_in_frame_9()
    {
        lastFrame.roll(6);
        lastFrame.roll(2);

        assertEquals(8, lastFrame.strikeBonusForPreviousFrame());
    }

    @Test
    public void should_give_strike_bonus_for_previous_frame_equal_to_first_two_rolls_when_LastFrame_starts_with_a_strike()
    {
        rollStrike();
        lastFrame.roll(2);

        assertEquals(12, lastFrame.strikeBonusForPreviousFrame());
    }
}