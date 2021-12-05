package behaviourselector;

import behaviour.Behaviour;
import behaviour.implementations.EmptyBehaviour;
import game.game.Game;
import game.PlayerDisc;


public abstract class BehaviourSelector {
    protected String name;
    protected Game game;
    protected PlayerDisc playerDisc;
    protected Behaviour[] behaviours;
    protected Behaviour previousBehaviour;

    public BehaviourSelector(String name, Game game, PlayerDisc playerDisc){
        this.name = name;
        this.game = game;
        this.playerDisc = playerDisc;
        this.behaviours = getEmptyBehaviour();
        this.previousBehaviour = null;
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
