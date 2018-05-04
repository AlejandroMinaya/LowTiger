package game.environment;

import game.Hitbox;
import game.manager.Main;

import java.awt.*;
import java.awt.image.*;

public class Bullet extends Item
{
    private boolean collided;
    private Image image;

    private int dx;
    public Bullet()
    {
        super();
        dx = 0;
        collided = false;
        image = loadImageFile("/static/elements/bullet.png");
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
        collided = true;
    }

    public boolean collided()
    {
        return collided;
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        g.drawImage(image, x, y, 100, 100, Main.getInstance().getCurrentLevel());
    }
}
