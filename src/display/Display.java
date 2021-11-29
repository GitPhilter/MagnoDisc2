package display;

import game.Arena;
import game.Game;

import javax.swing.*;
import java.awt.*;

public class Display extends JFrame {
    GamePanel gamePanel;

    public Display(Game game){
        setLayout(null);
        setSize(900, 630);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        gamePanel = new GamePanel(game);
        add(gamePanel);
        setVisible(true);
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        gamePanel.paintComponents(gamePanel.getGraphics());
    }

}
