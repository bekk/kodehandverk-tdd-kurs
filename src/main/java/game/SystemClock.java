package game;

import java.time.LocalDateTime;

public class SystemClock implements Clock {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
