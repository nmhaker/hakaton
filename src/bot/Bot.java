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
                state = BuildState.getInstance(this);
                break;
            case ATTACK:
                state = AttackState.getInstance(this);
                break;
            case RETREAT:
                state = RetreatState.getInstance(this);
                break;
        }
    }

    public void run() {
        Game game = null;
        if(gameID == null){
            game = gameManager.trainRandom(playerID);
            while(!game.success) game = gameManager.trainRandom(playerID);
            gameID = game.result.id;
        } else {
            game = gameManager.gamePlay(playerID, gameID);
            while(!game.success) game = gameManager.gamePlay(playerID, gameID);
        }
        Helpers.setPlayerID(playerID);
        PathHelper.setPlayerID(playerID);
        state = StartState.getInstance(this);
        while(game.result.winner == null){
            Helpers.SetGame(game);
            PathHelper.setGame(game);
            //decide move
            String action = state.chooseAction();
            if (state.threat() && !state.equals(StartState.getInstance(this))) {
                if (Helpers.getEnemyAttackingPower() > Helpers.getMyAttackingPower()) {
                    this.changeState(StateEnum.RETREAT);
                }
                else {
                    this.changeState(StateEnum.ATTACK);
                }
            }
            //do action
            game = gameManager.doAction(playerID, gameID, action);
        }
    }
}
