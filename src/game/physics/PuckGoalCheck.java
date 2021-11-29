package game.physics;

import game.Game;
import game.Puck;

public final class PuckGoalCheck {

    public static boolean isInLeftGoal(Game game, Puck puck){
        if(puck.getPosition().getX() <= -puck.getRadius() && puck.getPosition().getY() <= 400
                && puck.getPosition().getY() >= 200){
            return true;
        }
        return false;
    }

    public static boolean isInRightGoal(Game game, Puck puck){
        if(puck.getPosition().getX() >= game.getWidth() + puck.getRadius() && puck.getPosition().getY() <= 400
                && puck.getPosition().getY() >= 200){
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
