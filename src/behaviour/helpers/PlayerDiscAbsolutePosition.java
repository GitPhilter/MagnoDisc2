package behaviour.helpers;

import game.game.Game;
import game.PlayerDisc;

public final class PlayerDiscAbsolutePosition {

    public static boolean isTouchingAnyBorder(Game game, PlayerDisc playerDisc){
        int safetyMargin = 2;
        if(playerDisc.getPosition().getX()  - safetyMargin<= playerDisc.getRadius() ||
        playerDisc.getPosition().getX() + safetyMargin >= game.getWidth() - playerDisc.getRadius() ||
        playerDisc.getPosition().getY()  - safetyMargin <= playerDisc.getRadius() ||
        playerDisc.getPosition().getY()  + safetyMargin >= game.getHeight() - playerDisc.getRadius()){
            return true;
        }
        if(playerDisc.hasPuck()){

        }
        String thisName = "PlayerDiscAbsolutePosition.isTouchingAnyBorder";
        //System.out.println("is not touching any borders!");
        //System.out.println("PlayerDisc position: " + playerDisc.getPosition());
        return false;
    }

    public static double getDistanceToLeftGoalCenter(Game game, PlayerDisc playerDisc){
        double xGoal = 0;
        double yGoal = (double)game.getHeight() / 2;
        double xDistance = playerDisc.getPosition().getX() - xGoal;
        double yDistance = playerDisc.getPosition().getY() - yGoal;
        double overallDistance = Math.sqrt(xDistance*xDistance + yDistance*yDistance);
        //System.out.println("OverallDistance to goal: " + overallDistance);
        return overallDistance;
    }

    public static double getDistanceToRightGoalCenter(Game game, PlayerDisc playerDisc){
        double xGoal = 800;
        double yGoal = (double)game.getHeight() / 2;
        double xDistance = playerDisc.getPosition().getX() - xGoal;
        double yDistance = playerDisc.getPosition().getY() - yGoal;
        double overallDistance = Math.sqrt(xDistance*xDistance + yDistance*yDistance);
        //System.out.println("OverallDistance to goal: " + overallDistance);
        return overallDistance;
    }
}
