package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

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
                run = ImageIO.read(getClass().getResourceAsStream("/player/RUN.png"));

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
                x -= speed;
            }
            else if (keyH.rightPressed)
            {
                direction = "right";
                x += speed;
            }
        }

        public void draw(Graphics2D g2)
        {
//          g2.setColor(Color.white);
//
//          g2.fillRect(x, y, gp.tileSize, gp.tileSize * 3);

            BufferedImage image = null;
        }
}
