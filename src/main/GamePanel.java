package main;


import entity.Player;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable
{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16 x 16 tile for char, setting, etc.
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // scale it by three so it doesn't look small (48 x 48)
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    // set player's default pos
    int playerX = 100;
    int playerY = 300;
    int playerSpeed = 2;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null)
        {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000)
            {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update()
    {
        player.update();
    }
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);


        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();
    }
}
