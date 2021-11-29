package teamnamefactory;

public final class CityNameFactory {

    public static String getFunnyCityName(){
        String middlePart = getFunnyCityNameMiddlePart();
        String lastPart = getFunnyCityNameLastPart();
        double result = Math.random();
        String term = getTeamActivity();
        if(result <= 0.5) term = getAnimal();
        System.out.println("Result: " + middlePart + lastPart + " " + term);
        return "";
    }

    private static String getFunnyCityNameLastPart(){
        String[] names = {
                "town", "ville"
        };
        double possibility = 1 / (double)names.length;
        double randomResult = Math.random();
        for(int i = 0; i < names.length; ++i){
            if(randomResult <= possibility + possibility * i) return names [i];
        }
        return names[names.length - 1];
    }

    private static String getFunnyCityNameMiddlePart(){
        String[] names = {
                "Old", "New", "Bright", "River"
        };
        double possibility = 1 / (double)names.length;
        double randomResult = Math.random();
        for(int i = 0; i < names.length; ++i){
            if(randomResult <= possibility + possibility * i) return names [i];
        }
        return names[names.length - 1];
    }

    private static String getTeamActivity(){
        String[] names = {
                "Shooters", "Breezers", "Kickers", "Hunters", "Scorers", "Enforcers", "Destroyers",
                "Harvesters"
        };
        double possibility = 1 / (double)names.length;
        double randomResult = Math.random();
        for(int i = 0; i < names.length; ++i){
            if(randomResult <= possibility + possibility * i) return names [i];
        }
        return names[names.length - 1];
    }

    private static String getAnimal(){
        String[] names = {
                "Owls", "Snakes", "Polar Bears", "Dragons", "Dragon Flies", "Grizzlies", "Cobras", "Anacondas"
        };
        double possibility = 1 / (double)names.length;
        double randomResult = Math.random();
        for(int i = 0; i < names.length; ++i){
            if(randomResult <= possibility + possibility * i) return names [i];
        }
        return names[names.length - 1];
    }
}
