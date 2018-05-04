package game.actor;

/**
 * Secuaz is a specific NPC enemy for this game. Its behavior is the simplest of them all. It just follows the Main
 * Player on the x-axis, unable to jump.
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class Secuaz extends Enemy
{
    private static final int WIDTH = 100;
    private static final int HEIGHT = 100;
    private static final int INITIAL_HEALTH = 100;
    private static final int DAMAGE = 1;
    private static final int RANGE = 6;
    private static final int STEP = 3;
    private static final String CHARACTER = "secuaz";

    public Secuaz()
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
     * Allows the creation of a Secuaz given its initial position
     * @param x initial x-coordinate in pixels
     * @param y initial y-coordinate in pixels
     */
    public Secuaz(int x, int y)
    {
        super(x, y, WIDTH, HEIGHT, INITIAL_HEALTH, DAMAGE, RANGE, CHARACTER);
        step = STEP;
    }

}
