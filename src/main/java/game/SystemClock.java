package game;

import java.time.LocalDateTime;

/**
 * Her har vi laget en enkel wrapper. Dette ser kanskje litt unødvendig ut, men
 * er nødvendig for testbarhet.
 * En avansert ting du kan gjøre her er å legge til mulighet for å sette dato i f.eks.
 * en properties fil. Dette kan du bruke til å teste tidsbaserte ting i produksjonskoden
 * også.
 */
public class SystemClock implements Clock {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
