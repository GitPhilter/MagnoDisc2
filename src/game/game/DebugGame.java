package game.game;

import game.PlayerDisc;
import game.Team;
import game.TeamEnum;
import game.physics.Position;

import java.awt.*;

public class DebugGame extends Game{

    public DebugGame(){
        super();
        //
        PlayerDisc playerDisc_1 = new PlayerDisc("PlayerDisc_1", this);
        playerDisc_1.setPosition(new Position(100, 150));
        playerDisc_1.setDefaultPosition(playerDisc_1.getPosition());
        Team homeTeam = new Team("Home_Team", TeamEnum.HOME, new Color(100,0,200));
        PlayerDisc[] homeTeamPlayerDiscs = new PlayerDisc[1];
        homeTeamPlayerDiscs[0] = playerDisc_1;
        playerDisc_1.setTeam(homeTeam);
        homeTeam.setPlayerDiscs(homeTeamPlayerDiscs);
        setHomeTeam(homeTeam);
        //
        PlayerDisc playerDisc_2 = new PlayerDisc("PlayerDisc_2", this);
        playerDisc_2.setPosition(new Position(400, 150));
        playerDisc_2.setDefaultPosition(playerDisc_2.getPosition());
        Team awayTeam = new Team("Away_Team", TeamEnum.AWAY, new Color(200,80,200));
        PlayerDisc[] awayTeamPlayerDiscs = new PlayerDisc[1];
        awayTeamPlayerDiscs[0] = playerDisc_2;
        playerDisc_2.setTeam(awayTeam);
        awayTeam.setPlayerDiscs(awayTeamPlayerDiscs);
        setAwayTeam(awayTeam);
        //

    }


}
