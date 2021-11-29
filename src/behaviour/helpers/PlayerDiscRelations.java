package behaviour.helpers;

import game.PlayerDisc;
import game.physics.Position;

import java.util.ArrayList;

public final class PlayerDiscRelations {

    public static PlayerDisc getNearestOpponentPlayerDisc(PlayerDisc playerDisc, PlayerDisc[] opposingTeam){
        Position playerPosition = playerDisc.getPosition();
        int resultIndex = 0;
        double shortestDistance = 9999;
        for(int i = 0; i < opposingTeam.length; ++i){
            if(playerPosition.getDistance(opposingTeam[i].getPosition()) < shortestDistance){
                shortestDistance = playerPosition.getDistance(opposingTeam[i].getPosition());
                resultIndex = i;
            }
        }
        return opposingTeam[resultIndex];
    }
}
