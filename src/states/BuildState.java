package states;

public class BuildState extends State {

    private int remainingWood;
    private int remainingStone;
    private int remainingMetal;
    private int nextBuilding;
    enum BuildSubStates {

    }

    private static BuildState build_state = null;
    private BuildState() {}

    public static BuildState getInstance()
    {
        if (build_state == null)
            build_state = new BuildState();

        return build_state;
    }
    @Override
    public String chooseAction() {
        // findClosestShop
        // go to shop
        // take resource
        // need more? yes -> go to start
        // go to building
        // build
        return null;
    }
}
