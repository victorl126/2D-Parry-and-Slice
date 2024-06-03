package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener
{
    GamePanel gp;
    public boolean leftPressed, rightPressed;

    public KeyHandler (GamePanel gp)
    {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState)
        {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0)
                {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1)
                {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER)
            {
                if (gp.ui.commandNum == 0)
                {
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1)
                {
                    System.exit(0);
                }
            }
        }
        if (gp.gameState == gp.playState || gp.gameState == gp.pauseState)
        {
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE)
            {
                if (gp.gameState == gp.playState)
                {
                    gp.gameState = gp.pauseState;
                }
                else if (gp.gameState == gp.pauseState)
                {
                    gp.gameState = gp.playState;
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
    }
}
