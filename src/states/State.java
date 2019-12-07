package states;

import bot.Bot;
import bot.Helpers;

public abstract class State {

    protected Bot bot;
    public abstract String chooseAction();

    public boolean threat(){
        return Helpers.assessEnemyDanger();
    }
}
