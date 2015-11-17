package display;

import player.Player;

public class MockDisplay implements Display {
    public boolean showedStrikeAnimation;
    public boolean showedWelcomeScreen;
    public boolean showedEndGameScreen;
    public boolean showedCallPlayerToAction;
    public Player lastPlayerCalledToAction;

    public void showStrikeAnimation() {
        this.showedStrikeAnimation = true;
    }

    public void showWelcomeScreen() {
        this.showedWelcomeScreen = true;
    }

    public void showEndGameScreen() {
        this.showedEndGameScreen = true;
    }

    public void callPlayerToAction(Player player) {
        this.showedCallPlayerToAction = true;
        this.lastPlayerCalledToAction = player;
    }
}
