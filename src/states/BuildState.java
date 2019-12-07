package states;

import bot.Helpers;
import bot.Bot;

public class BuildState extends State {

//    private int remainingWood;
//    private int remainingStone;
//    private int remainingMetal;
//    private int nextBuilding;
    enum BuildSubStates {
        GET_3_STONES,
        GET_2_WOODS,
        BUILD_FORTIFY,
        GET_3_METAL,
        GET_1_WOOD,
        BUILD_FORTIFY_SWORD,
        GET_2__WOODS,
        GET_1_STONE,
        BUILD_HOUSE,
        TAKE_SWORD
    }

    BuildSubStates buildSubState = BuildSubStates.GET_3_STONES;

    private static BuildState build_state = null;
    private BuildState(Bot bot) { this.bot = bot; }

    public static BuildState getInstance(Bot bot)
    {
        if (build_state == null)
            build_state = new BuildState(bot);

        return build_state;
    }
    @Override
    public String chooseAction() {
        if(threat()){

        }else{
            switch(buildSubState){
                case GET_3_STONES:{
                    if(!Helpers.nextTo())
                }
                break;
            }
            return null;
        }

    }
}
