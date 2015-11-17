package game;

import display.Display;
import lightning.Lightning;
import storage.HighScoreRepository;

public class Game {
    private Display display;
    private Lightning lightning;
    private HighScoreRepository highScoreRepository;

    public Game(Display display, Lightning lightning, HighScoreRepository highScoreRepository) {
        this.display = display;
        this.lightning = lightning;
        this.highScoreRepository = highScoreRepository;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public void setLightning(Lightning lightning) {
        this.lightning = lightning;
    }

    public void setHighScoreRepository(HighScoreRepository highScoreRepository) {
        this.highScoreRepository = highScoreRepository;
    }


}
