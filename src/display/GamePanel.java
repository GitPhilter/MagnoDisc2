package display;

import game.Arena;
import game.Game;
import game.PlayerDisc;
import game.Puck;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {
    Game game;
    int width;
    int height;
    BufferedImage backgroundImage;
    // background colors
    Color fieldColor = Color.lightGray;
    Color borderColor = Color.DARK_GRAY;
    Color goalColor = Color.WHITE;
    Color lineColor = Color.BLACK;



    public GamePanel(Game game){
        this.width = 900;
        this.height = 600;
        this.setDoubleBuffered(true);
        this.game = game;
        setSize(width, height);
        setLayout(null);
        setVisible(true);
        setBackgroundImage();
    }

    private void setBackgroundImage(){
        backgroundImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = backgroundImage.getGraphics();
        // borders
        g.setColor(borderColor);
        g.fillRect(0, 0, width, height);
        // field
        g.setColor(fieldColor);
        g.fillRect(50, 50, width - 100, height - 100);
        // goals
        g.setColor(goalColor);
        g.fillRect(0, 200, 50, 200);
        g.fillRect(width - 50, 200, 50, 200);
        // lines
        g.drawLine(450, 51, 450, height - 51);
        g.drawOval(350, 200, 200, 200);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0, 0, width, height, this);
        // playerDiscs
        for (PlayerDisc playerDisc : game.getPlayerDiscs()) {
            g.setColor(playerDisc.getTeam().getJerseyColor());
            int drawX = (int) playerDisc.getPosition().getX() + 50 - playerDisc.getRadius();
            int drawY = (int) playerDisc.getPosition().getY() + 50 - playerDisc.getRadius();
            int radius = playerDisc.getRadius();
            int innerRadius = playerDisc.getInnerRadius();
            if(playerDisc.hasPuck()){
                g.setColor(playerDisc.getHasPuckColor());
                g.fillOval(drawX, drawY, radius * 2, radius * 2);
                g.setColor(playerDisc.getTeam().getJerseyColor());
                g.fillOval(drawX + (radius) - innerRadius, drawY + (radius - innerRadius), innerRadius * 2, innerRadius * 2);
            } else {
                g.fillOval(drawX, drawY, radius * 2, radius * 2);
            }
            g.setColor(Color.BLACK);
            g.drawOval(drawX, drawY, radius * 2, radius * 2);
            g.drawOval(drawX + (radius) - innerRadius, drawY + (radius - innerRadius), innerRadius * 2, innerRadius * 2);
        }
        // puck
        Puck puck = game.getPuck();
        g.setColor(puck.getBasicColor());
        //System.out.println("puck.position: " + puck.getPosition().toString());
        int drawX = (int) (puck.getPosition().getX() + 50 - puck.getRadius());
        int drawY = (int) (puck.getPosition().getY() + 50 - puck.getRadius());
        int radius = puck.getRadius();
        g.fillOval(drawX, drawY, radius * 2, radius * 2);
        g.setColor(Color.BLACK);
        g.drawOval(drawX, drawY, radius * 2, radius * 2);
    }
}
