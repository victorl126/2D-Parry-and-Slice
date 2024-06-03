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
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage()
    {
        try
        {
            run_r1 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_r1.png"));
            run_r2 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_r2.png"));
            run_r3 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_r3.png"));
            run_r4 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_r4.png"));
            run_r5 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_r5.png"));
            run_r6 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_r6.png"));
            run_r7 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_r7.png"));
            run_r8 = ImageIO.read(getClass().getResourceAsStream("/enemy/run_r8.png"));

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    @Override
    public void setAction()
    {
        actionLockCounter++;
        direction = "right";
        if (actionLockCounter == 120)
        {
            actionLockCounter = 0;
        }
    }

}
