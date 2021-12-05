package behaviour.puckmoveactuator;

import game.game.Game;
import game.PlayerDisc;

public abstract class PuckMoveActuator {
    protected String name;
    protected Game game;
    protected PlayerDisc playerDisc;

    public PuckMoveActuator(String name, Game game, PlayerDisc playerDisc){
        this.name = name;
        this.game = game;
        this.playerDisc = playerDisc;
    }

    public PuckMove getPuckMove(){
        return PuckMove.NO_MOVE;
    }

}
