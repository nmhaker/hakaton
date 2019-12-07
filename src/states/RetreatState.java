package states;

public class RetreatState extends State {

    private static RetreatState retreat_state = null;
    private RetreatState() {}

    public static RetreatState getInstance()
    {
        if (retreat_state == null)
            retreat_state = new RetreatState();

        return retreat_state;
    }

    @Override
    public String chooseAction() {
        return null;
    }
}
