package main;

import entity.Entity;

import java.awt.event.*;
import java.security.Key;

public class KeyHandler extends Entity implements KeyListener
{
    GamePanel gp;
    public boolean leftPressed, rightPressed, qPressed, spacePressed;

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
                if (gp.ui.commandNum == -1)
                {
                    gp.ui.commandNum = 2;
                }
                if (gp.ui.commandNum == 1)
                {
                    gp.ui.commandNum = 1;
                }
                if (gp.ui.commandNum == 0)
                {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if (gp.ui.commandNum == 1)
                {
                    gp.ui.commandNum = 1;
                }
                if (gp.ui.commandNum == 2)
                {
                    gp.ui.commandNum = 2;
                }
                if (gp.ui.commandNum == 3)
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
                    gp.gameState = gp.settingsState;
                }
                if (gp.ui.commandNum == 2)
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
            if (code == KeyEvent.VK_Q)
            {
                qPressed = true;
            }
            if (code == KeyEvent.VK_SPACE)
            {
                spacePressed = true;
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
        if (gp.gameState == gp.settingsState)
        {
            if(code == KeyEvent.VK_ESCAPE)
            {
                gp.gameState = gp.titleState;
            }
        }
        if (gp.gameState == gp.endState)
        {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if (gp.ui.commandNum == -1)
                {
                    gp.ui.commandNum = 1;
                }
                if (gp.ui.commandNum == 0)
                {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if (gp.ui.commandNum == 1)
                {
                    gp.ui.commandNum = 1;
                }
                if (gp.ui.commandNum == 2)
                {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER)
            {
                if (gp.ui.commandNum == 0)
                {
                    gp.gameState = gp.titleState;
                    gp.retry();
                }
                else if (gp.ui.commandNum == 1)
                {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
        {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
        {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_Q)
        {
            qPressed = false;
        }
        if (code == KeyEvent.VK_SPACE)
        {
            spacePressed = false;
        }
    }
}
