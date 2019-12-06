package bot;

import managers.GameManager;
import models.Game;

public class Main {

    private static void startTrain(String playerId) {
        Integer pid = Integer.parseInt(playerId);
        GameManager manager = new GameManager("http://localhost:9080");
        Game state = manager.trainRandom(pid);
        Integer gameId = state.result.id;
        while (true) {
            if (pid == 0) {
                manager.doAction(pid,gameId, "s");
                manager.doAction(pid,gameId, "s");
                manager.doAction(pid,gameId, "s");
                manager.doAction(pid,gameId, "s");
                manager.doAction(pid,gameId, "d");
                manager.doAction(pid,gameId, "d");
                manager.doAction(pid,gameId, "d");
                manager.doAction(pid,gameId, "d");
                manager.doAction(pid,gameId, "w");
                manager.doAction(pid,gameId, "w");
                manager.doAction(pid,gameId, "w");
                manager.doAction(pid,gameId, "w");
                manager.doAction(pid,gameId, "a");
                manager.doAction(pid,gameId, "a");
                manager.doAction(pid,gameId, "a");
                manager.doAction(pid,gameId, "a");
            }

            if (pid == 1) {
                manager.doAction(pid,gameId, "w");
                manager.doAction(pid,gameId, "w");
                manager.doAction(pid,gameId, "w");
                manager.doAction(pid,gameId, "w");
                manager.doAction(pid,gameId, "a");
                manager.doAction(pid,gameId, "a");
                manager.doAction(pid,gameId, "a");
                manager.doAction(pid,gameId, "a");
                manager.doAction(pid,gameId, "s");
                manager.doAction(pid,gameId, "s");
                manager.doAction(pid,gameId, "s");
                manager.doAction(pid,gameId, "s");
                manager.doAction(pid,gameId, "d");
                manager.doAction(pid,gameId, "d");
                manager.doAction(pid,gameId, "d");
                manager.doAction(pid,gameId, "d");

            }
        }
    }


    private static void joinGame(String playerId, String gameId) {
        Integer pid = Integer.parseInt(playerId);
        Integer gid = Integer.parseInt(gameId);
        GameManager manager = new GameManager("http://localhost:9080");
        Game state = manager.gamePlay(pid, gid);
        while (true) {
            if (pid == 0) {
                manager.doAction(pid,gid, "s");
                manager.doAction(pid,gid, "s");
                manager.doAction(pid,gid, "s");
                manager.doAction(pid,gid, "s");
                manager.doAction(pid,gid, "d");
                manager.doAction(pid,gid, "d");
                manager.doAction(pid,gid, "d");
                manager.doAction(pid,gid, "d");
                manager.doAction(pid,gid, "w");
                manager.doAction(pid,gid, "w");
                manager.doAction(pid,gid, "w");
                manager.doAction(pid,gid, "w");
                manager.doAction(pid,gid, "a");
                manager.doAction(pid,gid, "a");
                manager.doAction(pid,gid, "a");
                manager.doAction(pid,gid, "a");
            }

            if (pid == 1) {
                manager.doAction(pid,gid, "w");
                manager.doAction(pid,gid, "w");
                manager.doAction(pid,gid, "w");
                manager.doAction(pid,gid, "w");
                manager.doAction(pid,gid, "a");
                manager.doAction(pid,gid, "a");
                manager.doAction(pid,gid, "a");
                manager.doAction(pid,gid, "a");
                manager.doAction(pid,gid, "s");
                manager.doAction(pid,gid, "s");
                manager.doAction(pid,gid, "s");
                manager.doAction(pid,gid, "s");
                manager.doAction(pid,gid, "d");
                manager.doAction(pid,gid, "d");
                manager.doAction(pid,gid, "d");
                manager.doAction(pid,gid, "d");

            }
        }
    }

    public static void main (String args[]) {
        joinGame(args[0], args[1]);
    }
}
