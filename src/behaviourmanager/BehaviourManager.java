package behaviourmanager;

import behaviour.Behaviour;
import behaviour.implementations.attack.AttackBehaviourOne;
import behaviour.implementations.defense.DefenseBehaviourOne;
import behaviour.implementations.freeball.FreeBallBehaviourOne;
import behaviour.implementations.wait.WaitBehaviourOne;
import behaviourselector.BehaviourSelector;
import behaviourselector.implementations.EmptyBehaviourSelector;
import game.Game;
import game.PlayerDisc;

public abstract class BehaviourManager implements BehaviourManagerInterface{
    protected String name;
    protected Game game;
    protected PlayerDisc playerDisc;
    protected TacticState tacticState;
    protected Behaviour currentBehaviour;
    BehaviourSelector attackSelector;
    BehaviourSelector defenseSelector;
    BehaviourSelector freeballSelector;
    BehaviourSelector waitSelector;

    public BehaviourManager(String name, Game game, PlayerDisc playerDisc){
        this.name = name;
        this.game = game;
        this.playerDisc = playerDisc;
        this.tacticState = TacticState.WAIT;
        attackSelector = new EmptyBehaviourSelector(game, playerDisc);
        defenseSelector = new EmptyBehaviourSelector(game, playerDisc);
        freeballSelector = new EmptyBehaviourSelector(game, playerDisc);
        waitSelector = new EmptyBehaviourSelector(game, playerDisc);
    }

    /**
     * checks the current game state and picks the optimal behaviour accordingly
     * @return the behaviour picked
     */
    public void update() {
        updateTacticState();
        if (tacticState == TacticState.ATTACK) {
            //System.out.println("TacticState -> ATTACK");
            currentBehaviour = attackSelector.getBehaviour();
        }
        if (tacticState == TacticState.DEFENSE) {
            //System.out.println("TacticState -> DEFENSE");
            currentBehaviour = defenseSelector.getBehaviour();
        }
        if (tacticState == TacticState.FREE_BALL) {
            //System.out.println("TacticState -> FREE_BALL");
            currentBehaviour = freeballSelector.getBehaviour();
        }
        if (tacticState == TacticState.WAIT) {
            //System.out.println("TacticState -> WAIT");
            currentBehaviour = waitSelector.getBehaviour();
        }
    }

    protected void updateTacticState(){
        if(game.getPuck().getControllingPlayerDisc() == null){
            tacticState = TacticState.FREE_BALL;
            return;
        }
        if(game.getPuck().getControllingPlayerDisc().getTeam() == playerDisc.getTeam()){
            tacticState = TacticState.ATTACK;
            return;
        }
        if(game.getPuck().getControllingPlayerDisc().getTeam() != playerDisc.getTeam()){
            tacticState = TacticState.DEFENSE;
            return;
        }
        tacticState = TacticState.WAIT;
    }

    public Behaviour getNextBehaviour(){
        update();
        return currentBehaviour;
    }

    public Behaviour getWaitBehaviour(){
        return waitSelector.getBehaviour();
    }

    ///////////////////
    //               //
    //    Setters    //
    //               //
    ///////////////////

    public void setAttackSelector(BehaviourSelector attackSelector){
        this.attackSelector = attackSelector;
    }

    public void setDefenseSelector(BehaviourSelector defenseSelector){
        this.defenseSelector = defenseSelector;
    }

    public void setFreeballSelector(BehaviourSelector freeballSelector){
        this.freeballSelector = freeballSelector;
    }

    public void setWaitSelector(BehaviourSelector waitSelector){
        this.waitSelector = waitSelector;
    }
}
