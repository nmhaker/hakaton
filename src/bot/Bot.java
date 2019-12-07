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
        while(game.result.winner == null){
            //decide move
            //do action
            gameManager.doAction(playerID, gameID, "d");
            //loop
        }
    }
}
