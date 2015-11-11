package game;

import display.Display;
import lightning.Lightning;
import payment.Payment;
import storage.HighScoreRepository;

public class Game {
	private Display display;
    private Lightning lightning;
    private Payment payment;
    private HighScoreRepository highScoreRepository;

    public Game(Display display, Lightning lightning, Payment payment, HighScoreRepository highScoreRepository) {
        this.display = display;
        this.lightning = lightning;
        this.payment = payment;
        this.highScoreRepository = highScoreRepository;
    }


}
