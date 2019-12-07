package states;

public class AttackState extends State {

    private static AttackState attack_state = null;
    private AttackState() {}

    public static AttackState getInstance()
    {
        if (attack_state == null)
            attack_state = new AttackState();

        return attack_state;
    }

    @Override
    public String chooseAction() {
        return null;
    }
}
