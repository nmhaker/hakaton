package bot;

public class Main {

    private static void startTrain(String playerId, String url) {
        Integer pid = Integer.parseInt(playerId);
        Bot bot = new Bot(url, pid);
        bot.run();
    }


    private static void joinGame(String playerId, String gameId, String url) {
        Integer pid = Integer.parseInt(playerId);
        Integer gid = Integer.parseInt(gameId);
        Bot bot = new Bot(url, pid, gid);
        bot.run();
    }

    public static void main (String args[]) {
        String url = "http://localhost:9080";
        joinGame(args[0], args[1], url);
    }
}
