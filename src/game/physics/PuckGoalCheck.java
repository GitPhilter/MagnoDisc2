package game.physics;

import game.Game;
import game.Puck;

public final class PuckGoalCheck {
    static double goalUpperY = 150;
    static double goalLowerY = 350;

    public static boolean isInLeftGoal(Game game, Puck puck){
        if(puck.getPosition().getX() <= -puck.getRadius() && puck.getPosition().getY() <= goalLowerY - puck.getRadius()
                && puck.getPosition().getY() >= goalUpperY + puck.getRadius()){
            return true;
        }
        return false;
    }

    public static boolean isInRightGoal(Game game, Puck puck){
        if(puck.getPosition().getX() >= game.getWidth() + puck.getRadius() && puck.getPosition().getY() <= goalLowerY - puck.getRadius()
                && puck.getPosition().getY() >= goalUpperY + puck.getRadius()){
            return true;
        }
        return false;
    }

    public static boolean canMoveLeft(Game game, Puck puck){
        if(puck.getPosition().getX() >= puck.getRadius()) return true;
        if(puck.getPosition().getY() <= 400 - puck.getRadius()
                && puck.getPosition().getY() >= 200 + puck.getRadius()) return true;
        return false;
    }

    public static boolean canMoveRight(Game game, Puck puck){
        if(puck.getPosition().getX() <= game.getWidth() - puck.getRadius()) return true;
        if(puck.getPosition().getY() <= 400 - puck.getRadius()
                && puck.getPosition().getY() >= 200 + puck.getRadius()) return true;
        return false;
    }

}
