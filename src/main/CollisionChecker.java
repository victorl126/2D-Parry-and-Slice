package main;

import entity.Entity;

public class CollisionChecker
{
    GamePanel gp;
    public CollisionChecker(GamePanel gp)
    {
        this.gp = gp;
    }

    public int checkEntity (Entity entity, Entity[] target)
    {
        int index = 999;

        for (int i = 0; i < target.length; i++)
        {
            if (target[i] != null)
            {
                entity.solidArea.x = entity.worldX;
                entity.solidArea.y = entity.worldY;

                target[i].solidArea.x = target[i].worldX;
                target[i].solidArea.y = target[i].worldY;

                switch(entity.direction)
                {
                    case "left":
                        if (entity.worldX > gp.player.worldX)
                        {
                            entity.solidArea.x -= entity.speed;
                        }
                    case "right":
                        entity.solidArea.x += entity.speed;

                }

                if(entity.solidArea.intersects(target[i].solidArea))
                {
                    if (target[i] != entity)
                    {
                        entity.collisionOn = true;
                        index = i;
                    }

                }
            }
        }
        return index;
    }
    public int checkEntity (Entity entity, Entity[] target, int i)
    {
        int index = 999;

        if (target[i] != null) {
            entity.solidArea.x = entity.worldX;
            entity.solidArea.y = entity.worldY;

            target[i].solidArea.x = target[i].worldX;
            target[i].solidArea.y = target[i].worldY;

            switch (entity.direction) {
                case "left":
                    if (entity.worldX > gp.player.worldX) {
                        entity.solidArea.x -= entity.speed;
                    }
                case "right":
                    entity.solidArea.x += entity.speed;

            }

            if (entity.solidArea.intersects(target[i].solidArea)) {
                if (target[i] != entity) {
                    entity.collisionOn = true;
                    index = i;
                }

            }
        }

        return index;
    }
}
