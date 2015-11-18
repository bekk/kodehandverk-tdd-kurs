package game;

import org.joda.time.LocalDateTime;

public class AdjustableClock implements Clock {
    private LocalDateTime fixedTime;

    public LocalDateTime now() {
        if (fixedTime == null)
            return new SystemClock().now();
        else
            return fixedTime;
    }

    public void setClock(LocalDateTime time) {
        this.fixedTime = time;
    }

    public void reset() {
        this.fixedTime = null;
    }
}
