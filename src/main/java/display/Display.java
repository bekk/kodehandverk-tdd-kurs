package display;

import player.Player;

public interface Display {
    void showStrikeAnimation();
    void showWelcomeScreen();
    void showEndGameScreen();
    void callPlayerToAction(Player player);
}
