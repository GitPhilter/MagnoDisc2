package game.physics;

import behaviour.puckmoveactuator.PuckMove;

public class PlayerDiscActuation {
    private Impulse impulse;
    private Shot shot;
    private PuckMove puckMove;

    public PlayerDiscActuation(Impulse impulse, Shot shot, PuckMove puckMove){
        this.impulse = impulse;
        this.shot = shot;
        this.puckMove = puckMove;
    }

    public Impulse getImpulse(){
        return impulse;
    }

    public Shot getShot() {
        return shot;
    }

    public PuckMove getPuckMove() {
        return puckMove;
    }
}
