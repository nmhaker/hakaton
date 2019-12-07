package states;

public abstract class State {

    public abstract String chooseAction();

    public boolean threat(){
        // check if enemy player is closer then 5 spots. Change state to attack or retreat.
        return true;
    }
}
