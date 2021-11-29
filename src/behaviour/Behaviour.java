package behaviour;

import behaviour.impulseactuator.ImpulseActuator;
import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import behaviour.shotactuator.ShotActuator;
import game.Game;
import game.PlayerDisc;
import game.physics.Impulse;
import game.physics.Shot;

public abstract class Behaviour implements BehaviourInterface{
    protected final String name;
    protected Game game;
    protected PlayerDisc playerDisc;
    protected ImpulseActuator impulseActuator;
    protected ShotActuator shotActuator;
    protected PuckMoveActuator puckMoveActuator;

    public Behaviour(String name, Game game, PlayerDisc playerDisc,
                     ImpulseActuator impulseActuator, ShotActuator shotActuator, PuckMoveActuator puckMoveActuator){
        this.name = name;
        this.game = game;
        this.playerDisc = playerDisc;
        this.impulseActuator = impulseActuator;
        this.shotActuator = shotActuator;
        this.puckMoveActuator = puckMoveActuator;
    }

    public String getName(){
        return name;
    }

    public Impulse getImpulse() {
        return impulseActuator.getImpulse();
    }


    public Shot getShot() {
        return shotActuator.getShot();
    }


    public PuckMove getPuckMove(){
        return puckMoveActuator.getPuckMove();
    }

}
