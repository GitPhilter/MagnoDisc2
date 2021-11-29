package game;

import java.awt.*;

public class Team {
    String name;
    TeamEnum teamEnum;
    Color jerseyColor;

    public Team(String name, TeamEnum teamEnum, Color jerseyColor){
        this.name = name;
        this.teamEnum = teamEnum;
        this.jerseyColor = jerseyColor;
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
