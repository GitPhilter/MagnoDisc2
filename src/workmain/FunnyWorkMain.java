package workmain;

import game.physics.Direction;
import geometry.AngleCalculator;
import teamnamefactory.CityNameFactory;
import teamnamefactory.TeamNameFactory;

public class FunnyWorkMain {

    public static void main(String[] args){
        for(int i = 0; i  < 360; ++i){
            Direction direction = AngleCalculator.getDirectionFromAngle(i);
            System.out.println(direction);
        }

    }
}
