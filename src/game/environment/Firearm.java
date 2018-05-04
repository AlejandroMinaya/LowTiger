package game.environment;

import game.manager.*;
public class Firearm extends Item
{
    public int bulletSpeed = 10;
    public Firearm()
    {
        super();
    }

    public Firearm(int x, int y, int width, int height, int damage)
    {
        super(x, y, width, height, damage);
    }

    public void shoot(int x, int y, boolean facingRight)
    {
        int speed = bulletSpeed;
        if (!facingRight)
        {
            speed *= -1;
        }
        Main.getInstance().getCurrentLevel().addComponent(new Bullet(x, y, 10, 5, speed, getDamage()));
    }

}
