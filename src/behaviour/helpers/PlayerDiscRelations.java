package behaviour.helpers;

import game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Position;

import java.util.ArrayList;

public final class PlayerDiscRelations {

    public static PlayerDisc getClosestOpponenPlayerDisc(PlayerDisc playerDisc, PlayerDisc[] opposingTeam){
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



    public static PlayerDisc getPlayerDiscClosestToPuckFromTeam(Game game, PlayerDisc playerDisc){
        PlayerDisc[] team = game.getHomeTeam().getPlayerDiscs();
        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.AWAY) team = game.getAwayTeam().getPlayerDiscs();
        double shortestDistance = 9999;
        PlayerDisc result = null;
        for(PlayerDisc pd : team){
            if(game.getPuck().getPosition().getDistance(pd.getPosition()) < shortestDistance){
                //System.out.println("My ASS");
                shortestDistance = game.getPuck().getPosition().getDistance(pd.getPosition());
                result = pd;
            }
        }
        //System.out.println("I am: " + playerDisc.getName());
        //System.out.println("The disc closest to the puck is: " + result.getName());
        return result;
    }

    public static PlayerDisc getClosestPlayerDisc(Game game, PlayerDisc playerDisc){
        double shortestDistance = 9999;
        PlayerDisc result = null;
        for(PlayerDisc pd : game.getPlayerDiscs()){
            if(pd.getPosition().getDistance(playerDisc.getPosition()) < shortestDistance){
                //System.out.println("My ASS");
                shortestDistance = pd.getPosition().getDistance(playerDisc.getPosition());
                result = pd;
            }
        }
        return result;
    }
}
