package game.actor;

import game.*;

/**
 * Enemy are NPC Actors meant to interact negatively with the Player Actor. This generic class is the template for a
 * variety of enemies.
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
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


    /**
     * This method makes sure that if the target is within range of the enemy, it receives damage.
     */
    public void attack()
    {
        if(raycast.isTargetInRange(target))
        {
            target.hurt(damage);
        }
    }

    /**
     * Activate the autonomous behavior for the characters. The most basic behavior is walking from side to side
     * following a target position.
     */
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

    /**
     * This Override avoids enemies from picking up the items.
     */
    @Override
    public void equip(){}
}

