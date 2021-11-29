package workmain;

import teamnamefactory.CityNameFactory;
import teamnamefactory.TeamNameFactory;

public class FunnyWorkMain {

    public static void main(String[] args){
        for(int i = 0; i < 20; ++i){
            String name = CityNameFactory.getFunnyCityName();
        }

    }
}
