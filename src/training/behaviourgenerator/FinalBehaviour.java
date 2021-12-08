package training.behaviourgenerator;

import behaviour.impulseactuator.ImpulseActuator;
import behaviour.puckmoveactuator.PuckMove;
import behaviour.puckmoveactuator.PuckMoveActuator;
import behaviour.shotactuator.ShotActuator;
import game.PlayerDisc;
import game.game.Game;
import game.physics.Direction;
import game.physics.Impulse;
import game.physics.Shot;

import java.util.ArrayList;
import java.util.List;

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

    public FinalBehaviour(double[] doubles, PlayerDisc playerDisc){
        finalImpulseActuator = new FinalImpulseActuator(doubles[0], doubles[1] ,doubles[2]);
        finalPuckMoveActuator = new FinalPuckMoveActuator(playerDisc, doubles[3], doubles[4]);
        boolean empty = false;
        if(doubles[5] == 1) empty = true;
        finalShotActuator = new FinalShotActuator(doubles[6], doubles[7], doubles[8], empty);
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

    /**
     * returns the behaviour as a List of Doubles
     * used for generating trainingDataSets for example.
     * Order of Doubles:
     * // ImpulseActuator
     * xDir, yDir, acceleration
     * // PuckMoveActuator
     * xTargetDir, yTargetDir
     * // ShotActuator
     * empty, xDir, yDir, speed
     * @return the List of Doubles representing the parameters of this FinalBehaviour.
     */
    public List<Double> getAsDoubles(){
        List<Double> result = new ArrayList<Double>();
        result.add(finalImpulseActuator.getImpulse().getDirection().getX());
        result.add(finalImpulseActuator.getImpulse().getDirection().getY());
        result.add(finalImpulseActuator.getImpulse().getAcceleration());
        result.add(finalPuckMoveActuator.getXDir());
        result.add(finalPuckMoveActuator.getYDir());
        double empty = 0;
        if(finalShotActuator.isEmpty()) empty = 1;
        result.add(empty);
        result.add(finalShotActuator.getShot().getDirection().getX());
        result.add(finalShotActuator.getShot().getDirection().getY());
        result.add(finalShotActuator.getShot().getSpeed());
        return result;
    }
}
