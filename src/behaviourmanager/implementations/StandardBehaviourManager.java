package behaviourmanager.implementations;

import behaviour.Behaviour;
import behaviourmanager.BehaviourManager;
import behaviourmanager.TacticState;
import game.Game;
import game.PlayerDisc;

public class StandardBehaviourManager extends BehaviourManager {

    public StandardBehaviourManager(Game game, PlayerDisc playerDisc){
        super("StandardBehaviourManager", game, playerDisc);
        this.tacticState = TacticState.WAIT;
        // set attack selector


        // set defense selector

        // set freeball selector

        // set wait selector

    }

    public void update(){
        updateTacticState();

    }

    private void updateTacticState() {

    }




}
