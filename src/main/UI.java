package main;

import entity.Entity;
import object.Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI
{
    GamePanel gp;
    Graphics2D g2;
    Font mono_40;
    BufferedImage heart_full, heart_half, heart_blank;

    double playTime;
    DecimalFormat form = new DecimalFormat("#0.00");

    public boolean gameFinished = false;

    public int commandNum = 0;

    public UI (GamePanel gp)
    {
        this.gp = gp;

        mono_40 = new Font("Monospaced", Font.PLAIN, 40);

        Entity heart = new Heart(gp);
        heart_full = heart.image1;
        heart_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        g2.setFont(mono_40);
        g2.setColor(Color.white);
        if (gp.gameState == gp.titleState)
        {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playState)
        {
            drawPlayerLife();
            playTime += (double)1/60;
            g2.drawString("Time: " + form.format(playTime), 500, 50);
        }
        if (gp.gameState == gp.pauseState)
        {
            drawPlayerLife();
            g2.drawString("Time: " + form.format(playTime), 500, 50);
            drawPauseScreen();
        }

    }
    public void drawPlayerLife()
    {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.player.maxLife / 2)
        {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;

        }

        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        while (i < gp.player.life)
        {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life)
            {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    public void drawTitleScreen()
    {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70F));
        String text = "Parry and Slice";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 2;

        g2.setColor(Color.BLUE);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.drawImage(gp.player.idle1, 285, -100, 200, 400, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35F));

        text = "START GAME";
        x = getXForCenteredText(text);
        y += gp.tileSize * 6;
        g2.drawString(text, x, y);
        if (commandNum == 0)
        {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1)
        {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
    public void drawPauseScreen()
    {
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }
    public int getXForCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
