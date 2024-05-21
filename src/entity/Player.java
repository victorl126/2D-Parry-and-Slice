package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.image.*;

public class Player extends Entity
{
        GamePanel gp;
        KeyHandler keyH;

        public Player(GamePanel gp, KeyHandler keyH)
        {
            this.gp = gp;
            this.keyH = keyH;

            setDefaultValues();
            getPlayerImage();
        }

        public void setDefaultValues ()
        {
            x = 100;
            y = 100;
            speed = 2;
            direction = "right";
        }

        public void getPlayerImage()
        {
            try
            {
                attack = ImageIO.read(getClass().getResourceAsStream("/player/ATTACK 1.png"));
                idle = ImageIO.read(getClass().getResourceAsStream("/player/IDLE.png"));
                run_r = ImageIO.read(getClass().getResourceAsStream("/player/RUN.png"));
                run_l = ImageIO.read(getClass().getResourceAsStream("/player/RUN_LEFT.png"));

            }catch(IOException e)
            {
                e.printStackTrace();
            }
        }

        public void update()
        {
            if(keyH.leftPressed)
            {
                direction = "left";
                x -= 2;
            }
            else if (keyH.rightPressed)
            {
                direction = "right";
                x += speed;
            }
            else
            {
                direction = null;
            }
        }

        public void draw(Graphics2D g2)
        {
//          g2.setColor(Color.white);
//
//          g2.fillRect(x, y, gp.tileSize, gp.tileSize * 3);

            BufferedImage image = null;

            switch(direction)
            {
                case null:
                    image = idle;
                    break;
                case "right":
                    image = run_r;
                    break;
                case "left":
                    image = run_l;
                    break;
            }
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }
}
