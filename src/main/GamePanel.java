package main;


import enemy.Ninja;
import entity.Entity;
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

    public KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public Player player = new Player(this, keyH);

    public UI ui = new UI(this);

    public Entity[] monster = new Entity[2];


    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int endState = 3;
    public final int settingsState = 4;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame()
    {
        aSetter.setMonster();
        monster[1] = player;
        gameState = titleState;
    }

    public void retry()
    {
        player.setDefault();
        player.respawn();
        aSetter.setMonster();
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
        if (gameState == playState)
        {
            player.update();
            if (monster[0] != null) {
                if (monster[0].alive && !monster[0].dying) {
                    monster[0].update();
                }
                if (!monster[0].alive) {
                    monster[0] = null;
                }
            }
        }
        if (gameState == pauseState)
        {
            //nothing
        }
        if (gameState == endState)
        {
            player.update();
            if (monster[0] != null) {
                if (monster[0].alive && !monster[0].dying) {
                    monster[0].update();
                }
                if (!monster[0].alive) {
                    monster[0] = null;
                }
            }
        }
        if (gameState == settingsState)
        {

        }
    }
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        if (gameState == titleState)
        {
            ui.draw(g2);
        }
        else if (gameState == playState || gameState == pauseState)
        {
            player.draw(g2);

            ui.draw(g2);

            if(monster[0] != null)
            {
                monster[0].draw(g2);
            }
        }
        else if (gameState == endState)
        {
            if (player.life > 0)
            {
                player.draw(g2);

                ui.draw(g2);
            }
            else
            {
                ui.draw(g2);
            }
        }
        else if (gameState == settingsState)
        {
            ui.draw(g2);
        }
        g2.dispose();
    }
}
