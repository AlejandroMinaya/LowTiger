package game.environment;

import game.Hitbox;
import game.actor.Actor;
import game.manager.Main;

import java.awt.*;
import java.awt.image.*;

/**
 * Bullet is a class to represent the projectiles that result from Firearm objects. It allows to customize size, speed,
 * damage and appearance.
 *
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @see Firearm
 * @since 1.0
 */
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

    /**
     * Allows to specify the dimensions, speed, and damage of the Bullet.
     *
     * @param x starting x-coordinate in pixels
     * @param y y-coordinate in pixels
     * @param width width in pixels
     * @param height height in pixels
     * @param dx speed
     * @param damage impact damage
     */
    public Bullet(int x, int y, int width, int height, int dx, int damage)
    {
        super(x, y, width, height, damage);
        this.dx = dx;
        image = loadImageFile("/static/elements/bullet.png");
    }

    /**
     * Move the bullet across the screen at a constant speed
     */
    @Override
    public void move()
    {
        x += dx;
        hitbox.setX(x);
    }

    /**
     * Render the bullet useless after it has collided with an Actor
     * @see Actor
     */
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
        g.drawImage(image, x, y, width, height, Main.getInstance().getCurrentLevel());
    }
}
