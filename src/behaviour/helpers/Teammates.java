package behaviour.helpers;

import game.game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Position;

public final class Teammates {

    public static PlayerDisc getTeammateClosestToOpposingGoal(Game game, PlayerDisc playerDisc){
        //System.out.println("getTeammateClosestToOpposingGoal()!");
        PlayerDisc[] team = game.getAwayTeam().getPlayerDiscs();
        double goalY = (double)game.getHeight() / 2;
        double goalX = 0;
        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME){
            goalX = game.getWidth();
            team = game.getHomeTeam().getPlayerDiscs();
        }
        //System.out.println("Team size: " + team.length);
        double shortestDistance = 9999;
        Position goalPosition = new Position(goalX, goalY);
        PlayerDisc result = null;
        for(PlayerDisc pd : team){
            double distance = pd.getPosition().getDistance(goalPosition);
            if(distance < shortestDistance && pd != playerDisc){
                shortestDistance = pd.getPosition().getDistance(goalPosition);
                result = pd;
            }
        }
        //System.out.println("result: " + result.getName());
        return result;
    }

    public static PlayerDisc getClosestTeammate(Game game, PlayerDisc playerDisc){
        //System.out.println("getTeammateClosestToOpposingGoal()!");
        PlayerDisc[] team = game.getAwayTeam().getPlayerDiscs();
        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME){
            team = game.getHomeTeam().getPlayerDiscs();
        }
        //System.out.println("Team size: " + team.length);
        double shortestDistance = 9999;
        PlayerDisc result = null;
        for(PlayerDisc pd : team){
            if(pd != playerDisc){
                double distance = pd.getPosition().getDistance(playerDisc.getPosition());
                if(distance < shortestDistance && pd != playerDisc){
                    shortestDistance = pd.getPosition().getDistance(playerDisc.getPosition());
                    result = pd;
                }
            }

        }
        //System.out.println("result: " + result.getName());
        return result;
    }
}
