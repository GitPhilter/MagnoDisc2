package game.teamfactory;

import game.PlayerDisc;
import game.Team;
import game.TeamEnum;
import game.game.Game;
import game.physics.Position;
import training.neuralnetwork.NeuralNetwork;
import training.neuralnetworkgame.NeuralNetworkPlayerDisc;
import training.traininggame.TrainingPlayerDisc;

import java.awt.*;

public final class NeuralNetworkGameTeamFactory {

    public static Team createRandomTeam(int numberOfPlayers, Game game,
                                        TeamEnum teamEnum, NeuralNetwork neuralNetwork){
        if(numberOfPlayers > 5 || numberOfPlayers < 1) {
            return null;
        }
        double width = game.getWidth();
        double height = game.getHeight();
        Color teamColor = ColorGenerator.getRandomColor();
        String teamName = "Trainingsmannschaft";
        Team team = null;
        if(numberOfPlayers == 1) team = get1PlayerTeam(teamName, teamColor, teamEnum, game, neuralNetwork);
        if(numberOfPlayers == 2) team = get2PlayerTeam(teamName, teamColor, teamEnum, game, neuralNetwork);
        if(numberOfPlayers == 3) team = get3PlayerTeam(teamName, teamColor, teamEnum, game, neuralNetwork);
        if(numberOfPlayers == 4) team = get4PlayerTeam(teamName, teamColor, teamEnum, game, neuralNetwork);
        return team;
    }

    private static Team get1PlayerTeam(String name, Color teamColor, TeamEnum teamEnum, Game game, NeuralNetwork neuralNetwork){
        double xPos = 200;
        if(teamEnum == TeamEnum.AWAY) xPos = 600;
        Team team = new Team(name, teamEnum, teamColor);
        // playerDisc 1
        NeuralNetworkPlayerDisc playerDisc1 = new NeuralNetworkPlayerDisc("TrainingPlayerDisc_1 (" + teamEnum + ")", game, neuralNetwork);
        Position playerDisc1Position = new Position(xPos, 250);
        playerDisc1.setPosition(playerDisc1Position);
        playerDisc1.setDefaultPosition(playerDisc1Position);
        playerDisc1.setTeam(team);
        //
        PlayerDisc[] playerDiscs = new PlayerDisc[1];
        playerDiscs[0] = playerDisc1;
        team.setPlayerDiscs(playerDiscs);
        return team;
    }

    private static Team get2PlayerTeam(String name, Color teamColor, TeamEnum teamEnum, Game game, NeuralNetwork neuralNetwork){
        double xPos = 200;
        if(teamEnum == TeamEnum.AWAY) xPos = 600;
        Team team = new Team(name, teamEnum, teamColor);
        // playerDisc 1
        NeuralNetworkPlayerDisc playerDisc1 = new NeuralNetworkPlayerDisc("TrainingPlayerDisc_1 (" + teamEnum + ")", game, neuralNetwork);
        Position playerDisc1Position = new Position(xPos, 125);
        playerDisc1.setPosition(playerDisc1Position);
        playerDisc1.setDefaultPosition(playerDisc1Position);
        playerDisc1.setTeam(team);
        // playerDisc 2
        NeuralNetworkPlayerDisc playerDisc2 = new NeuralNetworkPlayerDisc("TrainingPlayerDisc_2 (" + teamEnum + ")", game, neuralNetwork);
        Position playerDisc2Position = new Position(xPos, 375);
        playerDisc2.setPosition(playerDisc2Position);
        playerDisc2.setDefaultPosition(playerDisc2Position);
        playerDisc2.setTeam(team);
        //
        PlayerDisc[] playerDiscs = new PlayerDisc[2];
        playerDiscs[0] = playerDisc1;
        playerDiscs[1] = playerDisc2;
        team.setPlayerDiscs(playerDiscs);
        return team;
    }

    private static Team get3PlayerTeam(String name, Color teamColor, TeamEnum teamEnum, Game game, NeuralNetwork neuralNetwork){
        double xPos = 100;
        if(teamEnum == TeamEnum.AWAY) xPos = 700;
        Team team = new Team(name, teamEnum, teamColor);
        // playerDisc 1
        NeuralNetworkPlayerDisc playerDisc1 = new NeuralNetworkPlayerDisc("TrainingPlayerDisc_1 (" + teamEnum + ")", game, neuralNetwork);
        Position playerDisc1Position = new Position(xPos, 125);
        playerDisc1.setPosition(playerDisc1Position);
        playerDisc1.setDefaultPosition(playerDisc1Position);
        playerDisc1.setTeam(team);
        // playerDisc 2
        NeuralNetworkPlayerDisc playerDisc2 = new NeuralNetworkPlayerDisc("TrainingPlayerDisc_2 (" + teamEnum + ")", game, neuralNetwork);
        Position playerDisc2Position = new Position(xPos, 375);
        playerDisc2.setPosition(playerDisc2Position);
        playerDisc2.setDefaultPosition(playerDisc2Position);
        playerDisc2.setTeam(team);
        //
        xPos = 250;
        if(teamEnum == TeamEnum.AWAY) xPos = 550;
        // playerDisc 3
        NeuralNetworkPlayerDisc playerDisc3 = new NeuralNetworkPlayerDisc("TrainingPlayerDisc_3 (" + teamEnum + ")", game, neuralNetwork);
        Position playerDisc3Position = new Position(xPos, 250);
        playerDisc3.setPosition(playerDisc3Position);
        playerDisc3.setDefaultPosition(playerDisc3Position);
        playerDisc3.setTeam(team);
        //
        PlayerDisc[] playerDiscs = new PlayerDisc[3];
        playerDiscs[0] = playerDisc1;
        playerDiscs[1] = playerDisc2;
        playerDiscs[2] = playerDisc3;
        team.setPlayerDiscs(playerDiscs);
        return team;
    }

    private static Team get4PlayerTeam(String name, Color teamColor, TeamEnum teamEnum, Game game, NeuralNetwork neuralNetwork){
        double xPos = 150;
        if(teamEnum == TeamEnum.AWAY) xPos = 650;
        Team team = new Team(name, teamEnum, teamColor);
        // playerDisc 1
        NeuralNetworkPlayerDisc playerDisc1 = new NeuralNetworkPlayerDisc("TrainingPlayerDisc_1 (" + teamEnum + ")", game, neuralNetwork);
        Position playerDisc1Position = new Position(xPos, 100);
        playerDisc1.setPosition(playerDisc1Position);
        playerDisc1.setDefaultPosition(playerDisc1Position);
        playerDisc1.setTeam(team);
        // playerDisc 2
        NeuralNetworkPlayerDisc playerDisc2 = new NeuralNetworkPlayerDisc("TrainingPlayerDisc_2 (" + teamEnum + ")", game, neuralNetwork);
        Position playerDisc2Position = new Position(xPos, 400);
        playerDisc2.setPosition(playerDisc2Position);
        playerDisc2.setDefaultPosition(playerDisc2Position);
        playerDisc2.setTeam(team);
        //
        xPos = 250;
        if(teamEnum == TeamEnum.AWAY) xPos = 550;
        // playerDisc 3
        NeuralNetworkPlayerDisc playerDisc3 = new NeuralNetworkPlayerDisc("TrainingPlayerDisc_3 (" + teamEnum + ")", game, neuralNetwork);
        Position playerDisc3Position = new Position(xPos, 150);
        playerDisc3.setPosition(playerDisc3Position);
        playerDisc3.setDefaultPosition(playerDisc3Position);
        playerDisc3.setTeam(team);
        // playerDisc 4
        NeuralNetworkPlayerDisc playerDisc4 = new NeuralNetworkPlayerDisc("TrainingPlayerDisc_4 (" + teamEnum + ")", game, neuralNetwork);
        Position playerDisc4Position = new Position(xPos, 350);
        playerDisc4.setPosition(playerDisc4Position);
        playerDisc4.setDefaultPosition(playerDisc4Position);
        playerDisc4.setTeam(team);
        //
        PlayerDisc[] playerDiscs = new PlayerDisc[4];
        playerDiscs[0] = playerDisc1;
        playerDiscs[1] = playerDisc2;
        playerDiscs[2] = playerDisc3;
        playerDiscs[3] = playerDisc4;
        team.setPlayerDiscs(playerDiscs);
        return team;
    }
}
