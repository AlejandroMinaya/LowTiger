package game.environment;

import game.Hitbox;

public class Bullet extends Item
{

    private int dx;
    public Bullet()
    {
        super();
        dx = 0;
    }

    public Bullet(int x, int y, int width, int height, int dx, int damage)
    {
        super(x, y, width, height, damage);
        this.dx = dx;
    }

    @Override
    public void move()
    {
        x += dx;
        hitbox.setX(x);
    }

    public void collide()
    {
        dx = 0;
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        hitbox = new Hitbox(0,0,0,0);
    }
}
