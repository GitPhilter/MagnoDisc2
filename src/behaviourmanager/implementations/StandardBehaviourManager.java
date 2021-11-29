package behaviourmanager.implementations;

import behaviour.Behaviour;
import behaviourmanager.BehaviourManager;
import behaviourmanager.TacticState;
import behaviourselector.implementations.EmptyBehaviourSelector;
import behaviourselector.implementations.attack.StandardAttackSelector;
import behaviourselector.implementations.defense.StandardDefenseSelector;
import behaviourselector.implementations.freeball.StandardFreeballSelector;
import behaviourselector.implementations.wait.StandardWaitSelector;
import game.Game;
import game.PlayerDisc;

public class StandardBehaviourManager extends BehaviourManager {

    public StandardBehaviourManager(Game game, PlayerDisc playerDisc){
        super("StandardBehaviourManager", game, playerDisc);
        this.tacticState = TacticState.WAIT;
        // set attack selector
        setAttackSelector(new StandardAttackSelector(game, playerDisc));
        // set defense selector
        setDefenseSelector(new StandardDefenseSelector(game, playerDisc));
        // set freeball selector
        setFreeballSelector(new StandardFreeballSelector(game, playerDisc));
        // set wait selector
        setWaitSelector(new StandardWaitSelector(game, playerDisc));
    }








}
