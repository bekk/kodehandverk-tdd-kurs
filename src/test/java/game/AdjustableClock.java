package game;

import org.joda.time.LocalDateTime;

public class AdjustableClock implements Clock {
    final private LocalDateTime fixedTime;

    public AdjustableClock(LocalDateTime time) {
        this.fixedTime = time;
    }
    
    public LocalDateTime now() {
            return fixedTime;
    }
}
