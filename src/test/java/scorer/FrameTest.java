package scorer;

import scorer.Frame;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

//Credit to ihaworth at Github.
//https://github.com/ihaworth
public class FrameTest
{
    private Frame frame;

    @Before
    public void setup()
    {
        frame = new Frame();
    }

    @Test
    public void should_score_zero_when_no_pins_are_hit()
    {
        frame.roll(0);
        frame.roll(0);

        assertEquals(0, frame.scoreWithBonus());
    }

    @Test
    public void should_score_two_when_one_hit_in_both_rolls()
    {
        frame.roll(1);
        frame.roll(1);

        assertEquals(2, frame.scoreWithBonus());
    }

    @Test
    public void should_not_be_over_when_no_rolls()
    {
        assertFalse(frame.isOver());
    }

    @Test
    public void should_not_be_over_when_one_rolled()
    {
        frame.roll(1);

        assertFalse(frame.isOver());
    }

    @Test
    public void should_be_over_when_two_rolled()
    {
        frame.roll(1);
        frame.roll(1);

        assertTrue(frame.isOver());
    }

    @Test
    public void should_be_over_when_strike()
    {
        frame.roll(10);

        assertTrue(frame.isOver());
    }

    @Test
    public void should_get_one_bonus_from_next_frame_when_spare_is_thrown()
    {
        Frame nextFrame = new Frame();
        frame.setNextFrame(nextFrame);

        frame.roll(3);
        frame.roll(7);

        nextFrame.roll(6);
        nextFrame.roll(3);

        assertEquals(16, frame.scoreWithBonus());
    }

    @Test
    public void should_get_two_bonuses_from_next_frame_when_strike_is_thrown()
    {
        Frame nextFrame = new Frame();
        frame.setNextFrame(nextFrame);

        frame.roll(10);

        nextFrame.roll(6);
        nextFrame.roll(3);

        assertEquals(19, frame.scoreWithBonus());
    }

    @Test
    public void should_get_bonus_from_next_two_frames_when_two_strikes_in_a_row_is_thrown()
    {
        Frame nextFrame = new Frame();
        Frame nextButOneFrame = new Frame();
        frame.setNextFrame(nextFrame);
        nextFrame.setNextFrame(nextButOneFrame);

        frame.roll(10);
        nextFrame.roll(10);
        nextButOneFrame.roll(5);
        nextButOneFrame.roll(4);

        assertEquals(25, frame.scoreWithBonus());
    }

    @Test
    public void should_not_be_spare_when_pins_are_remaining_after_max_rolls_are_rolled()
    {
        frame.roll(1);
        frame.roll(1);

        assertFalse(frame.isSpare());
    }

    @Test
    public void should_be_spare_when_no_pins_remaining_after_two_rolls()
    {
        frame.roll(3);
        frame.roll(7);

        assertTrue(frame.isSpare());
    }

    @Test
    public void should_not_be_spare_when_strike()
    {
        frame.roll(10);

        assertFalse(frame.isSpare());
    }

    @Test
    public void should_not_be_strike_when_pins_remaining_after_two_rolls()
    {
        frame.roll(1);
        frame.roll(1);

        assertFalse(frame.isStrike());
    }

    @Test
    public void should_not_be_strike_when_spare()
    {
        frame.roll(3);
        frame.roll(7);

        assertFalse(frame.isStrike());
    }

    @Test
    public void should_be_strike_when_10_is_thrown_at_first_roll()
    {
        frame.roll(10);

        assertTrue(frame.isStrike());
    }
}