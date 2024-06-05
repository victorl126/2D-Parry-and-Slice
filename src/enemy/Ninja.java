package enemy;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ninja extends Entity
{
    public Ninja(GamePanel gp)
    {
        super(gp);

        name = "Ninja";
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 40;
        solidArea.height = 400;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 90;
        attackArea.height = 48;

        getImage();
        getAttack();
    }

    public void getImage()
    {
        try
        {
            run_l1 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_l1.png"));
            run_l2 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_l2.png"));
            run_l3 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_l3.png"));
            run_l4 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_l4.png"));
            run_l5 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_l5.png"));
            run_l6 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_l6.png"));
            run_l7 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_l7.png"));
            run_l8 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_l8.png"));

            death_l1 = ImageIO.read(getClass().getResourceAsStream("/enemy/death_l1.png"));
            death_l2 = ImageIO.read(getClass().getResourceAsStream("/enemy/death_l2.png"));
            death_l3 = ImageIO.read(getClass().getResourceAsStream("/enemy/death_l3.png"));
            death_l4 = ImageIO.read(getClass().getResourceAsStream("/enemy/death_l4.png"));
            death_l5 = ImageIO.read(getClass().getResourceAsStream("/enemy/death_l5.png"));
            death_l6 = ImageIO.read(getClass().getResourceAsStream("/enemy/death_l6.png"));

            take_l1 = ImageIO.read(getClass().getResourceAsStream("/enemy/take_l1.png"));
            take_l2 = ImageIO.read(getClass().getResourceAsStream("/enemy/take_l2.png"));
            take_l3 = ImageIO.read(getClass().getResourceAsStream("/enemy/take_l3.png"));
            take_l4 = ImageIO.read(getClass().getResourceAsStream("/enemy/take_l4.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void getAttack()
    {
        try
        {
            attack_l1 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l1.png"));
            attack_l2 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l2.png"));
            attack_l3 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l3.png"));
            attack_l4 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l4.png"));
            attack_l5 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l5.png"));
            attack_l6 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l6.png"));
            attack_l7 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l7.png"));
            attack_l8 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l8.png"));
            attack_l9 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l9.png"));
            attack_l10 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l10.png"));
            attack_l11 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l11.png"));
            attack_l12 = ImageIO.read(getClass().getResourceAsStream("/enemy/attack_l12.png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setAction()
    {
        actionLockCounter++;
        direction = "left";
        if (actionLockCounter == 120)
        {
            actionLockCounter = 0;
        }
        if (!attacking)
        {
            checkAttackOrNot(90);
        }
    }

    public void attacking()
    {
        spriteCounter4++;

        if (spriteCounter4 <= 5) {
            spriteNum4 = 1;
        }
        if (spriteCounter4 > 5 && spriteCounter4 <= 10) {
            spriteNum4 = 2;
        }
        if (spriteCounter4 > 10 && spriteCounter4 <= 15) {
            spriteNum4 = 3;
        }
        if (spriteCounter4 > 15 && spriteCounter4 <= 20) {
            spriteNum4 = 4;
        }
        if (spriteCounter4 > 20 && spriteCounter4 <= 30) {
            spriteNum4 = 5;

            int currentWorldX = worldX;
            int solidAreaWidth = solidArea.width;


            worldX -= attackArea.width;

            solidArea.width = attackArea.width;

            int playerIndex = gp.cChecker.checkEntity(this, gp.monster, 1);
            damagePlayer(playerIndex);

            worldX = currentWorldX;
            solidArea.width = solidAreaWidth;

        }
        if (spriteCounter4 > 30 && spriteCounter4 <= 35) {
            spriteNum4 = 6;
        }
        if (spriteCounter4 > 35 && spriteCounter4 <= 40) {
            spriteNum4 = 7;
        }
        if (spriteCounter4 > 40 && spriteCounter4 <= 45) {
            spriteNum4 = 8;
        }
        if (spriteCounter4 > 45 && spriteCounter4 <= 50) {
            spriteNum4 = 9;
        }
        if (spriteCounter4 > 55 && spriteCounter4 <= 60) {
            spriteNum4 = 10;
        }
        if (spriteCounter4 > 60) {
            spriteNum4 = 1;
            spriteCounter4 = 0;
            attacking = false;
        }
    }
    public void damagePlayer (int i)
    {
        int damage = 2;
        if (i != 999)
        {
            if (!gp.monster[i].invincible)
            {
                if (gp.player.guarding)
                {
                    if (gp.player.guardCounter < 10)
                    {
                        damage = 0;
                    }
                    else
                    {
                        damage = damage / 2;
                    }
                }
                gp.monster[i].life -= damage;
                gp.monster[i].invincible = true;
                if (gp.monster[i].life <= 0)
                {
                    gp.monster[i].dying = true;
                }
            }
        }
    }

}
