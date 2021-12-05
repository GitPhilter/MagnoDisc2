package behaviour.impulseactuator;

import game.game.Game;
import game.PlayerDisc;
import game.physics.Impulse;

public abstract class ImpulseActuator {
    protected String name;
    protected Game game;
    protected PlayerDisc playerDisc;

    public ImpulseActuator(String name, Game game, PlayerDisc playerDisc){
        this.name = name;
        this.game = game;
        this.playerDisc = playerDisc;
    }

    public Impulse getImpulse(){
        return null;
    }

}
