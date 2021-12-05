package training.behaviourgenerator;

import behaviour.impulseactuator.ImpulseActuator;
import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import behaviour.shotactuator.ShotActuator;
import game.PlayerDisc;
import game.game.Game;
import game.physics.Impulse;
import game.physics.Shot;

public class FinalBehaviour {
    protected FinalImpulseActuator finalImpulseActuator;
    protected FinalShotActuator finalShotActuator;
    protected FinalPuckMoveActuator finalPuckMoveActuator;

    public FinalBehaviour(FinalImpulseActuator finalImpulseActuator,
                          FinalPuckMoveActuator finalPuckMoveActuator,
                          FinalShotActuator finalShotActuator){
        this.finalImpulseActuator = finalImpulseActuator;
        this.finalPuckMoveActuator = finalPuckMoveActuator;
        this.finalShotActuator = finalShotActuator;

    }

    public String getRepresentation(){
        return getRepresentationString();
    }

    public Impulse getImpulse() {
        return finalImpulseActuator.getImpulse();
    }

    public PuckMove getPuckMove(){
        return finalPuckMoveActuator.getPuckMove();
    }

    public Shot getShot() {
        return finalShotActuator.getShot();
    }

    @Override
    public String toString(){
        String result = "FinalBehaviour:\n";
        result += "FinalImpulseActuator: " + finalImpulseActuator.getRepresentationString() + "\n";
        result += "FinalPuckMoveActuator: " + finalPuckMoveActuator.getRepresentationString() + "\n";
        result += "FinalShotActuator: " + finalShotActuator.getRepresentationString() + "\n";
        return result;
    }

    public String getRepresentationString(){
        return "{" + finalImpulseActuator.getRepresentationString() + "," + finalPuckMoveActuator.getRepresentationString()
                + "," + finalShotActuator.getRepresentationString() + "}";
    }
}
