package game.teamfactory;

import java.awt.*;

public final class ColorGenerator {

    public static Color getRandomColor(){
        int red = getRandomRGBInt();
        int green = getRandomRGBInt();
        int blue = getRandomRGBInt();
        return new Color(red, green, blue);
    }

    private static int getRandomRGBInt(){
        return (int)(Math.random() * 255);
    }
}
