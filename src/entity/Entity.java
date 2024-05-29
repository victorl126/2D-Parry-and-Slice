package entity;

import java.awt.image.BufferedImage;

public class Entity
{
    public int x, y;
    public int speed;

    public BufferedImage attack, idle, run_r1, run_r2, run_r3, run_r4, run_r5, run_r6, run_r7, run_r8, run_l1, run_l2, run_l3, run_l4, run_l5, run_l6, run_l7, run_l8, guard1, guard2, guard3, guard4, guard5, guard6;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
