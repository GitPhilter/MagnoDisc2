package behaviour.shotactuator;

import game.Game;
import game.PlayerDisc;
import game.physics.Shot;

public abstract class ShotActuator {
    protected final String name;
    protected Game game;
    protected PlayerDisc playerDisc;

    public ShotActuator(String name, Game game, PlayerDisc playerDisc){
        this.name = name;
        this.game = game;
        this.playerDisc = playerDisc;
    }

    public Shot getShot() {
        if(!playerDisc.hasPuck()) return null;
        return null;
    }

    public String getName(){
        return name;
    }
}
