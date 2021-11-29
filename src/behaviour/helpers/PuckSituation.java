package behaviour.helpers;

import game.Game;
import game.PlayerDisc;
import game.TeamEnum;
import game.physics.Position;

public final class PuckSituation {

    public static boolean puckTouchesOpposingPlayerDisc(Game game, PlayerDisc playerDisc){
        if((playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME && game.getAwayTeam() == null) ||
        playerDisc.getTeam().getTeamEnum() == TeamEnum.AWAY && game.getHomeTeam() == null) return false;
        PlayerDisc[] opposingTeam = game.getHomeTeam().getPlayerDiscs();

        if(playerDisc.getTeam().getTeamEnum() == TeamEnum.HOME) opposingTeam = game.getAwayTeam().getPlayerDiscs();

        Position puckPosition = game.getPuck().getPosition();
        for(PlayerDisc opposingDisc : opposingTeam){
            if(puckPosition.getDistance(opposingDisc.getPosition()) < (game.getPuck().getRadius() + playerDisc.getRadius())){
                return true;
            }
        }
        return false;
    }

}
