package game.environment;

public class Bullet extends Item
{

    private int dx;
    public Bullet()
    {
        super();
        dx = 0;
    }

    public Bullet(int x, int y, int width, int height, int dx)
    {
        super(x, y, width, height);
        this.dx = dx;
    }

    @Override
    public void move()
    {
        x += dx;
        hitbox.setX(x);
    }
}
