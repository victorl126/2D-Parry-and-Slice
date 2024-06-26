package main;

import javax.swing.*;

// CREDITS
// CODE: RyiSnow on YouTube
// Sprites: Mattz Art and LuizMelo on itch.io

public class Main
{
    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Slice and Parry");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);


        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
        gamePanel.setupGame();
    }
}
