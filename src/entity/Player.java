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
                run_r1 = ImageIO.read(getClass().getResourceAsStream("/player/run_right_1.png"));
                run_r2 = ImageIO.read(getClass().getResourceAsStream("/player/run_right_2.png"));
                run_r3 = ImageIO.read(getClass().getResourceAsStream("/player/run_right_3.png"));
                run_r4 = ImageIO.read(getClass().getResourceAsStream("/player/run_right_4.png"));
                run_r5 = ImageIO.read(getClass().getResourceAsStream("/player/run_right_5.png"));
                run_r6 = ImageIO.read(getClass().getResourceAsStream("/player/run_right_6.png"));
                run_r7 = ImageIO.read(getClass().getResourceAsStream("/player/run_right_7.png"));
                run_r8 = ImageIO.read(getClass().getResourceAsStream("/player/run_right_8.png"));
                run_l1 = ImageIO.read(getClass().getResourceAsStream("/player/run_l1.png"));
                run_l2 = ImageIO.read(getClass().getResourceAsStream("/player/run_l2.png"));
                run_l3 = ImageIO.read(getClass().getResourceAsStream("/player/run_l3.png"));
                run_l4 = ImageIO.read(getClass().getResourceAsStream("/player/run_l4.png"));
                run_l5 = ImageIO.read(getClass().getResourceAsStream("/player/run_l5.png"));
                run_l6 = ImageIO.read(getClass().getResourceAsStream("/player/run_l6.png"));
                run_l7 = ImageIO.read(getClass().getResourceAsStream("/player/run_l7.png"));
                run_l8 = ImageIO.read(getClass().getResourceAsStream("/player/run_l8.png"));
                guard1 = ImageIO.read(getClass().getResourceAsStream("/player/guard1.png"));
                guard2 = ImageIO.read(getClass().getResourceAsStream("/player/guard2.png"));
                guard3 = ImageIO.read(getClass().getResourceAsStream("/player/guard3.png"));
                guard4 = ImageIO.read(getClass().getResourceAsStream("/player/guard4.png"));
                guard5 = ImageIO.read(getClass().getResourceAsStream("/player/guard5.png"));
                guard6 = ImageIO.read(getClass().getResourceAsStream("/player/guard1.png"));

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

            spriteCounter++;
            if(spriteCounter > 10)
            {
                if (spriteNum == 1)
                {
                    spriteNum = 2;
                }
                else if (spriteNum == 2)
                {
                    spriteNum = 3;
                }
                else if (spriteNum == 3)
                {
                    spriteNum = 4;
                }
                else if (spriteNum == 4)
                {
                    spriteNum = 5;
                }
                else if (spriteNum == 5)
                {
                    spriteNum = 6;
                }
                else if (spriteNum == 6)
                {
                    spriteNum = 7;
                }
                else if (spriteNum == 7)
                {
                    spriteNum = 8;
                }
                else if (spriteNum == 8)
                {
                    spriteNum = 1;
                }
                spriteCounter = 0;
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
                case "right":
                    if (spriteNum == 1)
                    {
                        image = run_r1;
                    }
                    if (spriteNum == 2)
                    {
                        image = run_r2;
                    }
                    if (spriteNum == 3)
                    {
                        image = run_r3;
                    }
                    if (spriteNum == 4)
                    {
                        image = run_r4;
                    }
                    if (spriteNum == 5)
                    {
                        image = run_r5;
                    }
                    if (spriteNum == 6)
                    {
                        image = run_r6;
                    }
                    if (spriteNum == 7)
                    {
                        image = run_r7;
                    }
                    if (spriteNum == 8)
                    {
                        image = run_r8;
                    }
                    break;
                case "left":
                    if (spriteNum == 1)
                    {
                        image = run_l1;
                    }
                    if (spriteNum == 2)
                    {
                        image = run_l2;
                    }
                    if (spriteNum == 3)
                    {
                        image = run_l3;
                    }
                    if (spriteNum == 4)
                    {
                        image = run_l4;
                    }
                    if (spriteNum == 5)
                    {
                        image = run_l5;
                    }
                    if (spriteNum == 6)
                    {
                        image = run_l6;
                    }
                    if (spriteNum == 7)
                    {
                        image = run_l7;
                    }
                    if (spriteNum == 8)
                    {
                        image = run_l8;
                    }
                    break;
            }
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }
}
