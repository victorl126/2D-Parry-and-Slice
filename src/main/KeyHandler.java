package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener
{
    public boolean leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();


        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
        {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
        {
            rightPressed = true;
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
