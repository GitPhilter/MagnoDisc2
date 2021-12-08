package training.perception;

import game.TeamEnum;
import game.physics.Direction;
import game.physics.Position;
import training.gamestate.GameState;
import training.gamestate.PlayerDiscState;

import java.util.ArrayList;
import java.util.List;

public final class PerceptionToDataset {

    public static DataSet[] getDataSets(Perception[] perceptions){
        DataSet[] result = new DataSet[perceptions.length];
        for(int i = 0; i < result.length; ++i){
            result[i] = getDatasetFromPerception(perceptions[i]);
        }
        return result;
    }

    public static DataSet getDatasetFromPerception(Perception perception){
        //
        GameState gameState = perception.getGameState();
        String playerDiscName = perception.getPlayerDiscName();
        double[] inputData = getInputDoublesFromGameState(gameState, playerDiscName);
        inputData = getMinMaxNormalizedVector(inputData);
        double[] finalBehaviourDoubles = getFinalBehaviourDoublesFromStringRepresentation(perception.getFinalBehaviourRepresentation());
        //System.out.println("finalBehaviourDoubles length: " + finalBehaviourDoubles.length);
        return new DataSet(inputData, finalBehaviourDoubles);
    }

    public static double[] getMinMaxNormalizedVector(double[] vector){
        double max = vector[0];
        double min = vector[0];
        for(int i = 0; i < vector.length; ++i){
            if(vector[i] > max) max = vector[i];
            if(vector[i] < min) min = vector[i];
        }
        double divide = max - min;
        // normalize
        for(int i = 0; i < vector.length; ++i){
            vector[i] = (vector[i] - min) / divide;
        }
        return vector;
    }

    public static double[] getInputDoublesFromGameState(GameState gameState, String playerDiscName){
        double[] inputData = new double[48];
        Position playerPosition = null;
        PlayerDiscState actingPlayerDiscState = null;
        for(PlayerDiscState pds : gameState.getPlayerDiscStates()){
            if(pds.getName().equals(playerDiscName)){
                playerPosition = new Position(pds.getPosition().getX(), pds.getPosition().getY());
                actingPlayerDiscState = pds;
            }
        }
        // puck
        // Order: playerToPuckXDir, playerToPuckYDir, distance, xDir, yDir, speed, controllingPlayerDiscIndex
        Direction playerToPuckDirection = playerPosition.getDirection(gameState.getPuckPosition());
        inputData[0] = playerToPuckDirection.getX();
        inputData[1] = playerToPuckDirection.getY();
        inputData[2] = playerPosition.getDistance(gameState.getPuckPosition());
        inputData[3] = gameState.getPuckDirection().getX();
        inputData[4] = gameState.getPuckDirection().getY();
        inputData[5] = gameState.getPuckSpeed();
        inputData[6] = gameState.getPuckControllingPlayerDiscIndex();
        //
        // the acting playerDisc
        // Order: xPos, yPos, xDir, yDir, speed, teamEnum
        inputData[7] = actingPlayerDiscState.getPosition().getX();
        inputData[8] = actingPlayerDiscState.getPosition().getY();
        inputData[9] = actingPlayerDiscState.getDirection().getX();
        inputData[10] = actingPlayerDiscState.getDirection().getY();
        inputData[11] = actingPlayerDiscState.getSpeed();
        int teamEnum = 0; // equals TeamEnum.HOME
        if(actingPlayerDiscState.getTeamEnum() == TeamEnum.AWAY) teamEnum = 1;
        inputData[12] = teamEnum;
        //
        // other discs not controlled
        int indexPointer = 13;
        for(PlayerDiscState pds : gameState.getPlayerDiscStates()){
            if(pds != actingPlayerDiscState){
                Direction actingPlayerDiscToOtherPlayerDiscDirection = playerPosition.getDirection(pds.getPosition());
                inputData[indexPointer] = actingPlayerDiscToOtherPlayerDiscDirection.getX();
                ++indexPointer;
                inputData[indexPointer] = actingPlayerDiscToOtherPlayerDiscDirection.getY();
                ++indexPointer;
                inputData[indexPointer] = playerPosition.getDistance(pds.getPosition());
                ++indexPointer;
                inputData[indexPointer] = pds.getDirection().getX();
                ++indexPointer;
                inputData[indexPointer] = pds.getDirection().getY();
                ++indexPointer;
                inputData[indexPointer] = pds.getSpeed();
                ++indexPointer;
                teamEnum = 0; // equals TeamEnum.HOME
                if(pds.getTeamEnum() == TeamEnum.AWAY) teamEnum = 1;
                inputData[indexPointer] = teamEnum;
            }
        }
        return inputData;
    }

    public static double[] getFinalBehaviourDoublesFromStringRepresentation(String finalBehaviourRepresentation){
        ArrayList<String> doubleStrings = new ArrayList<>();
        String currentDouble = "";
        for(Character c : finalBehaviourRepresentation.toCharArray()){
            if(c == '(' || c == ')' || c == '{' || c == '}' || c == '[' || c == ']' || c == ','){
                if(!currentDouble.equals("")){
                   doubleStrings.add(currentDouble);
                   currentDouble = "";
                }
            } else {
                currentDouble += c;
            }
        }
        double[] result = new double[doubleStrings.size()];
        for(int i = 0; i < result.length; ++i){
            try{
                result[i] = Double.parseDouble(doubleStrings.get(i));
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        double[] finalResult = new double[9];
        if(result.length == 5){
            for(int i = 0; i < 5; ++i){
                finalResult[i] = result[i];
            }
            finalResult[5] = 1;
            for(int i = 6; i < 9; ++i){
                finalResult[i] = 0;
            }
        } else {
            for(int i = 0; i < 5; ++i){
                finalResult[i] = result[i];
            }
            finalResult[5] = 0;
            for(int i = 6; i < 9; ++i){
                finalResult[i] = result[i - 1];
            }
        }
        return finalResult;
    }


}
