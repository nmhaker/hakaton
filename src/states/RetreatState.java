package states;

import bot.Bot;

public class RetreatState extends State {

    private static RetreatState retreat_state = null;
    private RetreatState(Bot bot) { this.bot = bot;}

    public static RetreatState getInstance(Bot bot)
    {
        if (retreat_state == null)
            retreat_state = new RetreatState(bot);

        return retreat_state;
    }

    @Override
    public String chooseAction() {
        return null;
    }
}
