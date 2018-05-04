package game.actor;

import game.Raycast;

/**
 * Yakuza is a specific enemy for this game. It has the most complex behavior because it jumps platforms in order to
 * follow the Main Player. It is quick and performs a lot of damage.
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class Yakuza extends Enemy
{
    private static final int WIDTH = 80;
    private static final int HEIGHT = 100;
    private static final int INITIAL_HEALTH = 100;
    private static final int DAMAGE = 1;
    private static final int RANGE = 10;
    private static final int STEP = 5;
    private static final String CHARACTER = "yakuza";

    public Yakuza()
    {
        super();
        width = WIDTH;
        height = HEIGHT;
        health = INITIAL_HEALTH;
        damage = DAMAGE;
        range = RANGE;
        character = CHARACTER;
        step = STEP;
    }

    /**
     * Given an specific position, this constructor allows to create a Yakuza actor.
     * @param x initial x-coordinate
     * @param y inital y-coordinate
     */
    public Yakuza(int x, int y)
    {
        super(x, y, WIDTH, HEIGHT, INITIAL_HEALTH, DAMAGE, RANGE, CHARACTER);
        step = STEP;
    }


    /**
     * This override allows the Yakuza to jump if an obstacle is colliding with its Raycast
     * @see Raycast
     */
    @Override
    public void behave()
    {
        super.behave();
        if(raycast.isObstacleInRange())
        {
            jumping = true;
        }
    }
}
