package main;

import enemy.Ninja;

public class AssetSetter
{
    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }

    public void setMonster() {
        gp.monster[0] = new Ninja(gp);
        gp.monster[0].worldX = 500;
        gp.monster[0].worldY = 280;
    }
}
