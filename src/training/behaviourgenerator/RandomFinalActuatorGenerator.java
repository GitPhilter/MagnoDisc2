package training.behaviourgenerator;


import behaviour.shotactuator.implementations.EmptyShotActuator;
import game.PlayerDisc;
import game.physics.Direction;

public final class RandomFinalActuatorGenerator {
    private static final double directionStep = 0.2;
    private static final double shotProbability = 0.2;
    private static final double maxShootingSpeed = 10;

    public static FinalImpulseActuator getRandomFinalImpulseActuator(){
        return new FinalImpulseActuator(getRandomDirectionValue(), getRandomDirectionValue(),
                getRandomAcceleration());
    }

    public static FinalPuckMoveActuator getRandomFinalPuckMoveActuator(PlayerDisc playerDisc){
        return new FinalPuckMoveActuator(playerDisc, getRandomDirectionValue(), getRandomDirectionValue());
    }

    public static FinalShotActuator getRandomFinalShotActuator(){
        if(Math.random() < shotProbability){
            return new FinalShotActuator(getRandomDirectionValue(), getRandomDirectionValue(),
                    getRandomShootingSpeed(), false);
        } else {
            return new FinalShotActuator(0,0,0, true);
        }
    }

    public static double getRandomDirectionValue(){
        int numberOfResults = (int)(2 / directionStep);
        double random = Math.random() * numberOfResults;
        double tempResult = (random * directionStep - 1) * 10;
        tempResult = Math.round(tempResult);
        double finalResult = tempResult / 10;
        //System.out.println("finalResult: " + finalResult);
        return finalResult;
    }

    public static double getRandomAcceleration(){
        double random = Math.random() * 10;
        double temp = Math.round(random);
        return temp / 10;
    }

    public static double getRandomShootingSpeed(){
        double random = Math.random() * maxShootingSpeed;
        return Math.round(random);
    }
}
