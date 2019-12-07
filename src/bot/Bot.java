package bot;

import managers.GameManager;
import models.Game;

public class Bot {

    private GameManager gameManager;
    private int playerID;
    private Integer gameID;

    public Bot(String url, int playerID) {
        gameManager = new GameManager(url);
        this.playerID = playerID;
    }

    public Bot(String url, int playerID, int gameID) {
        gameManager = new GameManager(url);
        this.playerID = playerID;
        this.gameID = gameID;
    }

    public void run() {
        Game game = null;
        if(gameID == null){
            game = gameManager.trainRandom(playerID);
            gameID = game.result.id;
        } else {
            game = gameManager.gamePlay(playerID, gameID);
        }

        Brain brain = new Brain();

        while(game.result.winner == null){
            //decide move
            String action = brain.getNewAction(game);
            //do action
            game = gameManager.doAction(playerID, gameID, action);
        }
    }
}
