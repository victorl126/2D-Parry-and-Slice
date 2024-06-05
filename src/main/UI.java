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

    public double playTime;
    DecimalFormat form = new DecimalFormat("#0.00");

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
        if (gp.gameState == gp.endState)
        {
            drawEndScreen();
        }
        if (gp.gameState == gp.settingsState)
        {
            drawSettingsScreen();
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

        if (gp.player.life == 0 || gp.monster[0] == null)
        {
            gp.gameState = gp.endState;
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

        text = "SETTINGS";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1)
        {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2)
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
    public void drawEndScreen()
    {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if (gp.player.life <= 0 || !gp.player.alive )
        {
            String text;
            int x;
            int y = (gp.screenHeight / 3) - 40;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            text = "GAME OVER";
            g2.setColor(Color.RED);
            x = getXForCenteredText(text);
            g2.drawString(text, x, y);
            g2.setColor(Color.WHITE);
            g2.drawString(text, x - 4, y - 4);

            String text2;
            int x2;
            int y2 = (gp.screenHeight / 3) + 60;
            text2 = "YOU LASTED FOR " + form.format(playTime) + " SECONDS!";
            g2.setColor(Color.RED);
            x2 = getXForCenteredText(text2) + 20;
            g2.drawString(text2, x2, y2);
            g2.setColor(Color.WHITE);
            g2.drawString(text2, x2 - 4, y2 - 4);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
            String text3 = "Play Again";
            int x3 = getXForCenteredText(text2) + 170;
            int y3 = (gp.screenHeight / 3) + 180;
            g2.drawString(text3, x3, y3);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
            String text4 = "Quit";
            int x4 = getXForCenteredText(text2) + 220;
            int y4 = (gp.screenHeight / 3) + 240;
            g2.drawString(text4, x4, y4);

            if (commandNum == 0)
            {
                g2.drawString(">", x3 - 40, y3);
            }
            if (commandNum == 1)
            {
                g2.drawString(">", x4 - 40, y4);
            }
        }
        else {
            String text = "YOU WIN";
            int x = getXForCenteredText(text);
            int y = (gp.screenHeight / 3) - 40;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            g2.setColor(Color.GREEN);
            g2.drawString(text, x, y);
            g2.setColor(Color.WHITE);
            g2.drawString(text, x - 4, y - 4);

            String text2 = "IT TOOK YOU " + form.format(playTime) + " SECONDS!";
            int x2 = getXForCenteredText(text2) + 25;
            int y2 = (gp.screenHeight / 3) + 60;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            g2.setColor(Color.GREEN);
            g2.drawString(text2, x2, y2);
            g2.setColor(Color.WHITE);
            g2.drawString(text2, x2 - 4, y2 - 4);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
            String text3 = "Play Again";
            int x3 = getXForCenteredText(text2) + 150;
            int y3 = (gp.screenHeight / 3) + 180;
            g2.drawString(text3, x3, y3);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30F));
            String text4 = "Quit";
            int x4 = getXForCenteredText(text2) + 200;
            int y4 = (gp.screenHeight / 3) + 240;
            g2.drawString(text4, x4, y4);

            if (commandNum == 0)
            {
                g2.drawString(">", x3 - 40, y3);
            }
            if (commandNum == 1)
            {
                g2.drawString(">", x4 - 40, y4);
            }
        }
    }
    public void drawSettingsScreen()
    {
        String text = "Q to ATTACK";
        int x = getXForCenteredText(text);
        int y = (gp.screenHeight / 2) - (gp.tileSize * 4);

        g2.drawString(text, x, y);

        String text2 = "A to MOVE LEFT";
        int x2 = getXForCenteredText(text2);
        int y2 = (gp.screenHeight / 2) - (gp.tileSize * 2);

        g2.drawString(text2, x2, y2);

        String text3 = "D to MOVE LEFT";
        int x3 = getXForCenteredText(text3);
        int y3 = (gp.screenHeight / 2);

        g2.drawString(text3, x3, y3);

        String text4 = "SPACE to BLOCK/PARRY";
        int x4 = getXForCenteredText(text4);
        int y4 = (gp.screenHeight / 2) + (gp.tileSize * 2);

        g2.drawString(text4, x4, y4);

        String text5 = "ESC to RETURN";
        int x5 = getXForCenteredText(text5);
        int y5 = (gp.screenHeight / 2) + (gp.tileSize * 4);

        g2.drawString(text5, x5, y5);
    }
    public int getXForCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
}
