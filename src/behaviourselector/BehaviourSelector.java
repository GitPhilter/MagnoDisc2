package behaviourselector;

import behaviour.Behaviour;
import behaviour.implementations.EmptyBehaviour;
import game.Game;
import game.PlayerDisc;


public abstract class BehaviourSelector {
    protected String name;
    Game game;
    PlayerDisc playerDisc;
    protected Behaviour[] behaviours;

    public BehaviourSelector(String name, Game game, PlayerDisc playerDisc){
        this.name = name;
        this.game = game;
        this.playerDisc = playerDisc;
        this.behaviours = getEmptyBehaviour();
    }

    public void setBehaviours(Behaviour[] behaviours){
        this.behaviours = behaviours;
    }

    private static Behaviour[] getEmptyBehaviour(){
        Behaviour[] emptyBehaviour = new Behaviour[1];
        emptyBehaviour[0] = new EmptyBehaviour(null, null);
        return emptyBehaviour;
    }

    public String getName(){
        return name;
    }

    /**
     * override this method in the subclasses
     * @return the behaviour that seems best and most fitting
     */
    public Behaviour getBehaviour(){
        return behaviours[0];
    }

}
