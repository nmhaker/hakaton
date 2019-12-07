package bot;

import models.Game;

public class Brain {

    private Game game;

    Brain(){}

    public String getNewAction(Game g) {
        this.game = g; return null;
    }
}
