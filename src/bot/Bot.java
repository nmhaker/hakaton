package bot;

import managers.GameManager;
import models.Game;
import states.*;

public class Bot {

    private GameManager gameManager;
    private int playerID;
    private Integer gameID;
    private State state;

    public Bot(String url, int playerID) {
        gameManager = new GameManager(url);
        this.playerID = playerID;
        Helpers.playerID = playerID;
    }

    public Bot(String url, int playerID, int gameID) {
        gameManager = new GameManager(url);
        this.playerID = playerID;
        Helpers.playerID = playerID;
        this.gameID = gameID;
    }

    public void changeState(StateEnum stateEnum) {
        switch(stateEnum){
            case BUILD:
                state = BuildState.getInstance();
                break;
            case ATTACK:
                state = AttackState.getInstance();
                break;
            case RETREAT:
                state = RetreatState.getInstance();
                break;
        }
    }

    public void run() {
        Game game = null;
        if(gameID == null){
            game = gameManager.trainRandom(playerID);
            gameID = game.result.id;
        } else {
            game = gameManager.gamePlay(playerID, gameID);
        }
        state = StartState.getInstance();
        while(game.result.winner == null){
            //decide move
            String action = state.chooseAction();
            //do action
            game = gameManager.doAction(playerID, gameID, action);

        }
    }
}
