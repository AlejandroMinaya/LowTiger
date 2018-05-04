package game.actor;

import game.environment.Firearm;

/**
 * This is a specific character for this game able to follow and shoot its target. Nevertheless it is not able to jump.
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class Pacino extends Enemy
{
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int INITIAL_HEALTH = 100;
    private static final int DAMAGE = 2;
    private static final int RANGE = 20;
    private static final int STEP = 1;
    private static final String CHARACTER = "pacino";
    private boolean shotFired = false;

    public Pacino()
    {
        super();
        width = WIDTH;
        height = HEIGHT;
        health = INITIAL_HEALTH;
        damage = DAMAGE;
        range = RANGE;
        character = CHARACTER;
        step = STEP;
        init();
    }

    /**
     * Allows the creation of a Pacino actor with a specific starting position
     * @param x initial x-coordinate in pixels
     * @param y initial y-coordinate in pixels
     */
    public Pacino(int x, int y)
    {
        super(x, y, WIDTH, HEIGHT, INITIAL_HEALTH, DAMAGE, RANGE, CHARACTER);
        step = STEP;
        init();
    }

    /**
     * This override allows the Pacino to be equipped with a gun
     */
    @Override
    public void init()
    {
        super.init();
        Firearm gun = new Firearm(0, 0, 0, 0, damage);
        gun.equip();
        equippedItem = gun;
    }

    /**
     * This override allows the Pacino to shoot if its target is within range
     */
    @Override
    public void attack()
    {
        if(raycast.isTargetInRange(target))
        {
            if(!shotFired)
            {
                shoot();
                shotFired = true;
            }
            shotFired = false;
        }
    }
}
