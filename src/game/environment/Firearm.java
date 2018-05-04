package game.environment;

import game.actor.Actor;
import game.manager.*;

import java.awt.*;

/**
 * Firearm represents a weapon capable of producing damage from afar. It can be wielded by any Actor.
 *
 * @author Juan Alcantara
 * @author Jose Hernadez
 * @version %I%
 * @see Actor
 * @since 1.0
 */
public class Firearm extends Item
{
    public int bulletSpeed = 10;
    private Image image = loadImageFile("/static/elements/gun.png");
    public Firearm()
    {
        super();
    }

    /**
     * Allows to specify the unequipped dimensions, unequipped position and base damage
     *
     * @param x x-coordinate in pixels
     * @param y y-coordinate in pixels
     * @param width width in pixels
     * @param height height in pixels
     * @param damage base damage
     */
    public Firearm(int x, int y, int width, int height, int damage)
    {
        super(x, y, width, height, damage);
    }

    /**
     * Generates a bullet in the direction the wielder is currently facing
     * @param x starting x-coordinate for the new Bullet.
     * @param y height for the new Bullet.
     * @param facingRight direction the Actor is facing.
     */
    public void shoot(int x, int y, boolean facingRight)
    {
        int speed = bulletSpeed;
        if (!facingRight)
        {
            speed *= -1;
        }
        Main.getInstance().getCurrentLevel().addComponent(new Bullet(x, y, 10, 5, speed, getDamage()));
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        g.drawImage(image, x, y, width, height, Main.getInstance().getCurrentLevel());
    }

}
