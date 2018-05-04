package game.actor;

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

    public Yakuza(int x, int y)
    {
        super(x, y, WIDTH, HEIGHT, INITIAL_HEALTH, DAMAGE, RANGE, CHARACTER);
        step = STEP;
    }

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
