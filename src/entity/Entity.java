package entity;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    GamePanel gp;
    public int x, y;
    public int speed;

    public BufferedImage attack, idle1, idle2, idle3, idle4, idle5, run_r1, run_r2, run_r3, run_r4, run_r5, run_r6, run_r7, run_r8, run_l1, run_l2, run_l3, run_l4, run_l5, run_l6, run_l7, run_l8, guard1, guard2, guard3, guard4, guard5, guard6;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public BufferedImage image1, image2, image3;
    public String name;
    public boolean collision = false;
    public UtilityTool uTool = new UtilityTool();

    public int maxLife;
    public int life;

    public int worldX,worldY;
    public int actionLockCounter = 0;


    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }
    public void setAction()
    {
        System.out.println("sup");
    }
    public void update()
    {
        setAction();
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



    public Entity() {
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        switch(direction) {
            case "right":
                if (spriteNum == 1) {
                    image = run_r1;
                }
                if (spriteNum == 2) {
                    image = run_r2;
                }
                if (spriteNum == 3) {
                    image = run_r3;
                }
                if (spriteNum == 4) {
                    image = run_r4;
                }
                if (spriteNum == 5) {
                    image = run_r5;
                }
                if (spriteNum == 6) {
                    image = run_r6;
                }
                if (spriteNum == 7) {
                    image = run_r7;
                }
                if (spriteNum == 8)
                {
                    image = run_r8;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = run_l1;
                }
                if (spriteNum == 2) {
                    image = run_l2;
                }
                if (spriteNum == 3) {
                    image = run_l3;
                }
                if (spriteNum == 4) {
                    image = run_l4;
                }
                if (spriteNum == 5) {
                    image = run_l5;
                }
                if (spriteNum == 6) {
                    image = run_l6;
                }
                if (spriteNum == 7) {
                    image = run_l7;
                }
                if (spriteNum == 8) {
                    image = run_l8;
                }
                break;
        }
        g2.drawImage(image, 500, 280, 200, 470, null);
    }


    public int check (Entity entity, boolean player)
    {
        int index = 999;

        return index;
    }
}
