package game.actor;

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

    public Secuaz(int x, int y)
    {
        super(x, y, WIDTH, HEIGHT, INITIAL_HEALTH, DAMAGE, RANGE, CHARACTER);
        step = STEP;
    }

}
