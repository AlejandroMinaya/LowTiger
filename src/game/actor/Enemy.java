package game.actor;

import game.*;

public abstract class Enemy extends Actor
{
    protected final Player target = Player.getInstance();
    public Enemy()
    {
        super();
    }

    public Enemy(int x, int y, int width, int height, int health, int damage, int range, String character)
    {
        super(x, y, width, height, health, damage, range, character);
    }


    public void attack()
    {
        if(raycast.isTargetInRange(target))
        {
            target.hurt(damage);
        }
    }

    public void behave()
    {
        attack();
        int targetPosition = target.getX();
        int offset = x - targetPosition;
        if(offset > 1)
        {
            dx = step * -1;
        }
        else if (offset < -1)
        {
            dx = step;
        }
        else{
            dx = 0;
        }
    }

    @Override
    public void equip(){}
}

