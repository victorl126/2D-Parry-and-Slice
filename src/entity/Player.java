package entity;

import enemy.Ninja;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.image.*;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 40;
        solidArea.height = 400;

        attackArea.width = 60;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getAttackImage();
        getGuardImage();
    }

    public void setDefaultValues() {
        worldX = 0;
        worldY = 350;
        defaultSpeed = 2;
        speed = defaultSpeed;
        direction = "right";

        maxLife = 10;
        life = maxLife;
    }

    public void setDefault()
    {
        worldX = 0;
        worldY = 350;
        direction = "right";
        gp.ui.playTime = 0;
    }
    public void respawn()
    {
        life = maxLife;
        invincible = false;
    }

    public void getPlayerImage() {
        try {
            idle1 = ImageIO.read(getClass().getResourceAsStream("/player/idle1.png"));
            idle2 = ImageIO.read(getClass().getResourceAsStream("/player/idle2.png"));
            idle3 = ImageIO.read(getClass().getResourceAsStream("/player/idle3.png"));
            idle4 = ImageIO.read(getClass().getResourceAsStream("/player/idle4.png"));
            idle5 = ImageIO.read(getClass().getResourceAsStream("/player/idle5.png"));

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAttackImage() {
        try {
            attack_r1 = ImageIO.read(getClass().getResourceAsStream("/player/attack_r1.png"));
            attack_r2 = ImageIO.read(getClass().getResourceAsStream("/player/attack_r2.png"));
            attack_r3 = ImageIO.read(getClass().getResourceAsStream("/player/attack_r3.png"));
            attack_r4 = ImageIO.read(getClass().getResourceAsStream("/player/attack_r4.png"));
            attack_r5 = ImageIO.read(getClass().getResourceAsStream("/player/attack_r5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getGuardImage() {
        try {
            guard1 = ImageIO.read(getClass().getResourceAsStream("/player/guard1.png"));
            guard2 = ImageIO.read(getClass().getResourceAsStream("/player/guard2.png"));
            guard3 = ImageIO.read(getClass().getResourceAsStream("/player/guard3.png"));
            guard4 = ImageIO.read(getClass().getResourceAsStream("/player/guard4.png"));
            guard5 = ImageIO.read(getClass().getResourceAsStream("/player/guard5.png"));
            guard6 = ImageIO.read(getClass().getResourceAsStream("/player/guard1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (!invincible) {
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monster[i].invincible) {
                gp.monster[i].life--;
                gp.monster[i].invincible = true;

                if (gp.monster[i].life <= 0) {
                    gp.monster[i].dying = true;
                }
            }
        }
    }


    public void update() {
        fight();
        guard();
        if (attacking) {
            attacking();
        } else if (keyH.spacePressed && !keyH.leftPressed && !keyH.rightPressed && !keyH.qPressed) {
            guarding();
            guardCounter++;

        } else if (keyH.leftPressed) {
            guarding = false;
            guardCounter = 0;
            direction = "left";
            if (worldX > -55) {
                worldX -= speed;
                solidAreaDefaultX -= speed;
            }
        } else if (keyH.rightPressed) {
            guarding = false;
            guardCounter = 0;
            direction = "right";
            if (worldX < 625 && !collisionOn) {
                worldX += speed;
                solidAreaDefaultX += speed;
            }
        } else {
            direction = "none";
            guarding = false;
            guardCounter = 0;
        }
        collisionOn = false;
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        contactMonster(monsterIndex);

        if (keyH.qPressed && !attackCanceled) {
            attacking = true;
            spriteCounter2 = 0;
        }
        attackCanceled = false;
        gp.keyH.qPressed = false;


        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 5;
            } else if (spriteNum == 5) {
                spriteNum = 6;
            } else if (spriteNum == 6) {
                spriteNum = 7;
            } else if (spriteNum == 7) {
                spriteNum = 8;
            } else if (spriteNum == 8) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 50) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void guarding()
    {
        spriteCounter5++;

        if (spriteCounter5 <= 15)
        {
            spriteNum2 = 1;
        }
        if (spriteCounter5 > 15 && spriteCounter5 <= 20)
        {
            spriteNum5 = 2;
        }
        if (spriteCounter5 > 20 && spriteCounter5 <= 25)
        {
            spriteNum5 = 3;
        }
        if (spriteCounter5 > 25 && spriteCounter5 <= 30)
        {
            spriteNum5 = 4;
        }
        if (spriteCounter5 > 35 && spriteCounter5 <= 40)
        {
            spriteNum5 = 5;
        }
        if (spriteCounter5 > 45 && spriteCounter5 <= 50)
        {
            spriteNum5 = 6;
        }
        if (spriteCounter5 > 50)
        {
            spriteNum5 = 1;
            spriteCounter5 = 0;
        }
    }

    public void attacking() {
        spriteCounter2++;

        if (spriteCounter2 <= 15) {
            spriteNum2 = 1;
        }
        if (spriteCounter2 > 15 && spriteCounter2 <= 20) {
            spriteNum2 = 2;
        }
        if (spriteCounter2 > 20 && spriteCounter2 <= 25) {
            spriteNum2 = 3;

            int currentWorldX = worldX;
            int solidAreaWidth = solidArea.width;


            worldX += -10 + attackArea.width;

            solidArea.width = attackArea.width;

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            solidArea.width = solidAreaWidth;

        }
        if (spriteCounter2 > 25 && spriteCounter2 <= 30) {
            spriteNum2 = 4;
        }
        if (spriteCounter2 > 35 && spriteCounter2 <= 40) {
            spriteNum2 = 5;
        }
        if (spriteCounter2 > 40) {
            spriteNum2 = 1;
            spriteCounter2 = 0;
            attacking = false;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
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
                if (spriteNum == 8) {
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
            case "none":
                if (!attacking) {
                    if (spriteNum == 1) {
                        image = idle1;
                    }
                    if (spriteNum == 2) {
                        image = idle2;
                    }
                    if (spriteNum == 3) {
                        image = idle3;
                    }
                    if (spriteNum == 4) {
                        image = idle4;
                    }
                    if (spriteNum == 5) {
                        image = idle5;
                    }
                    if (spriteNum == 6) {
                        image = idle1;
                    }
                    if (spriteNum == 7) {
                        image = idle2;
                    }
                    if (spriteNum == 8) {
                        image = idle5;
                    }
                }
                if (attacking) {
                    if (spriteNum2 == 1) {
                        image = attack_r1;
                    }
                    if (spriteNum2 == 2) {
                        image = attack_r2;
                    }
                    if (spriteNum2 == 3) {
                        image = attack_r3;
                    }
                    if (spriteNum2 == 4) {
                        image = attack_r4;
                    }
                    if (spriteNum2 == 5) {
                        image = attack_r5;
                    }
                }
                if (guarding) {
                    if (spriteNum5 == 1) {
                        image = guard1;
                    }
                    if (spriteNum5 == 2) {
                        image = guard2;
                    }
                    if (spriteNum5 == 3) {
                        image = guard3;
                    }
                    if (spriteNum5 == 4) {
                        image = guard4;
                    }
                    if (spriteNum5 == 5) {
                        image = guard5;
                    }
                    if (spriteNum5 == 5) {
                        image = guard6;
                    }
                    if (spriteNum5 == 6) {
                        image = guard1;
                    }
                }
        }


        if (invincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2.drawImage(image, worldX, 190, 200, 400, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void fight() {
        if (gp.keyH.qPressed && !keyH.leftPressed && !keyH.rightPressed)
        {
            attacking = true;
        }
        gp.keyH.qPressed = false;
    }
    public void guard() {
        if (gp.keyH.spacePressed && !keyH.leftPressed && !keyH.rightPressed)
        {
            guarding = true;
        }
    }
}
