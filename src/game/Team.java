package game;

import java.awt.*;

public class Team {
    String name;
    TeamEnum teamEnum;
    Color jerseyColor;
    PlayerDisc[] playerDiscs;

    public Team(String name, TeamEnum teamEnum, Color jerseyColor){
        this.name = name;
        this.teamEnum = teamEnum;
        this.jerseyColor = jerseyColor;
        this.playerDiscs = null;
    }

    public void setPlayerDiscs(PlayerDisc[] playerDiscs){
        this.playerDiscs = playerDiscs;
    }

    public PlayerDisc[] getPlayerDiscs(){
        return playerDiscs;
    }

    public String getName(){
        return name;
    }

    public TeamEnum getTeamEnum(){
        return teamEnum;
    }

    public Color getJerseyColor(){
        return jerseyColor;
    }

}
