package entity;

import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity
{
    public GamePanel gp;
    public int x, y;
    public int defaultSpeed;
    public int speed;

    public BufferedImage attack_r1, attack_r2, attack_r3, attack_r4, attack_r5,
            idle1, idle2, idle3, idle4, idle5,
            run_r1, run_r2, run_r3, run_r4, run_r5, run_r6, run_r7, run_r8,
            run_l1, run_l2, run_l3, run_l4, run_l5, run_l6, run_l7, run_l8,
            guard1, guard2, guard3, guard4, guard5, guard6;
    public BufferedImage attack_l1, attack_l2, attack_l3, attack_l4, attack_l5, attack_l6, attack_l7, attack_l8, attack_l9,
            attack_l10, attack_l11, attack_l12,
            take_l1, take_l2, take_l3, take_l4,
            death_l1, death_l2, death_l3, death_l4, death_l5, death_l6;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int spriteCounter2 = 0;
    public int spriteNum2 = 1;
    public int spriteCounter3 = 0;
    public int spriteNum3 = 1;
    public int spriteCounter4 = 0;
    public int spriteNum4 = 1;
    public int spriteCounter5 = 0;
    public int spriteNum5 = 1;

    public Rectangle solidArea = new Rectangle(0,0, 200, 400);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    public int knockBackCounter = 0;
    public int guardCounter = 0;
    public int offBalanceCounter = 0;
    public boolean offBalance = false;
    public boolean knockBack = false;
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean guarding = false;
    public boolean attackCanceled = false;
    int dyingCounter = 0;

    public BufferedImage image1, image2, image3;
    public String name;

    public UtilityTool uTool = new UtilityTool();

    public int maxLife;
    public int life;

    public int worldX,worldY;
    public int actionLockCounter = 0;



    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }
    public void knockBack(Entity entity)
    {
        entity.direction = this.direction;
        entity.speed += 5;
        entity.knockBack = true;
    }
    public void setAction()
    {
        System.out.println("sup");
    }
    public void update()
    {
        if (knockBack)
        {
            switch (direction)
            {
                case "right":
                    worldX += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
            }
            knockBackCounter++;
            if (knockBackCounter == 10)
            {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            }
        }
        else if (attacking)
        {
            attacking();
        }
        else
        {
            setAction();
            collisionOn = false;
            gp.cChecker.checkEntity(this, gp.monster);

            if (!collisionOn)
            {
                switch(direction)
                {
                    case "left":
                        if (worldX > gp.player.worldX + 80)
                        {
                            worldX -= speed;
                        }
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 10)
            {
                if (spriteNum == 1) {spriteNum = 2;}
                else if (spriteNum == 2) {spriteNum = 3;}
                else if (spriteNum == 3) {spriteNum = 4;}
                else if (spriteNum == 4) {spriteNum = 5;}
                else if (spriteNum == 5) {spriteNum = 6;}
                else if (spriteNum == 6) {spriteNum = 7;}
                else if (spriteNum == 7) {spriteNum = 8;}
                else if (spriteNum == 8) {spriteNum = 1;}

                spriteCounter = 0;
            }
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }

        }
        if (offBalance)
        {
            offBalanceCounter++;
            if (offBalanceCounter > 60)
            {
                offBalance = false;
                offBalanceCounter = 0;
            }
        }
    }

    public void checkAttackOrNot (int straight)
    {
        boolean targetInRange = false;
        int xDis = Math.abs(worldX - gp.player.worldX);

        if (direction.equals("left"))
        {
            if (gp.player.worldX < worldX && xDis < straight)
            {
                targetInRange = true;
            }
        }
        if (targetInRange)
        {
            attacking = true;
            spriteNum4 = 1;
            spriteCounter4 = 0;
        }
    }
    public void attacking()
    {}

    public Entity() {
    }

    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        spriteCounter3++;
        if (spriteCounter3 <= 15) {
            spriteNum3 = 1;
        }
        if (spriteCounter3 > 15 && spriteCounter3 <= 20) {
            spriteNum3 = 2;
        }
        if (spriteCounter3 > 20 && spriteCounter3 <= 25) {
            spriteNum3 = 3;
        }
        if (spriteCounter3 > 20 && spriteCounter3 <= 25) {
            spriteNum3 = 4;
        }
        switch(direction) {
            case "right":
                if (spriteNum3 == 1) {image = take_l1;}
                if (spriteNum3 == 2) {image = take_l2;}
                if (spriteNum3 == 3) {image = take_l3;}
                if (spriteNum3 == 4) {image = take_l4;}
                break;
            case "left":
                if (!attacking)
                {
                    if (spriteNum == 1) {image = run_l1;}
                    if (spriteNum == 2) {image = run_l2;}
                    if (spriteNum == 3) {image = run_l3;}
                    if (spriteNum == 4) {image = run_l4;}
                    if (spriteNum == 5) {image = run_l5;}
                    if (spriteNum == 6) {image = run_l6;}
                    if (spriteNum == 7) {image = run_l7;}
                    if (spriteNum == 8) {image = run_l8;}
                }

                if (attacking)
                {
                    if (spriteNum4 == 1) {image = attack_l1;}
                    if (spriteNum4 == 2) {image = attack_l2;}
                    if (spriteNum4 == 3) {image = attack_l3;}
                    if (spriteNum4 == 4) {image = attack_l4;}
                    if (spriteNum4 == 5) {image = attack_l5;}
                    if (spriteNum4 == 6) {image = attack_l6;}
                    if (spriteNum4 == 7) {image = attack_l7;}
                    if (spriteNum4 == 8) {image = attack_l8;}
                    if (spriteNum4 == 9) {image = attack_l9;}
                    if (spriteNum4 == 10) {image = attack_l10;}
                }
                break;

        }
        double oneScale = (double) 50 / maxLife;
        double hpBarValue = oneScale * life;

        g2.setColor(new Color(255, 255, 255));
        g2.fillRect(worldX + 73, 440 - 16, 52, 12);

        g2.setColor(new Color(255, 0, 30));
        g2.fillRect(worldX + 74, 440 - 15, (int) hpBarValue, 10);

        if (invincible)
        {
            {
                invincibleCounter++;
                if (invincibleCounter < 10) {image = take_l1;}
                if (invincibleCounter > 10 && invincibleCounter <= 20) {image = take_l2;}
                if (invincibleCounter > 20 && invincibleCounter <= 30) {image = take_l3;}
                if (invincibleCounter > 30 && invincibleCounter <= 40) {image = take_l4;}
                if (invincibleCounter > 40)
                {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }

        }

        if (dying)
        {
            dyingAnimation(g2);
        }

        g2.drawImage(image, worldX, 260, 210, 500, null);

    }
    public void dyingAnimation(Graphics2D g2)
    {
        BufferedImage image = null;
        dyingCounter++;

        int i = 10;

        if (dyingCounter <= i)
        {
            image = death_l1;
            g2.drawImage(image, worldX, 320, 210, 400, null);
        }
        if (dyingCounter > i && dyingCounter <= i * 2)
        {
            image = death_l2;
            g2.drawImage(image, worldX, 320, 210, 400, null);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3)
        {
            image = death_l3;
            g2.drawImage(image, worldX, 320, 210, 400, null);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4)
        {
            image = death_l4;
            g2.drawImage(image, worldX, 320, 210, 400, null);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5)
        {
            image = death_l5;
            g2.drawImage(image, worldX, 320, 210, 400, null);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6)
        {
            image = death_l6;
            g2.drawImage(image, worldX, 320, 210, 400, null);
        }

        if (dyingCounter > i * 6)
        {
            dying = false;
            alive = false;
        }
    }
}
