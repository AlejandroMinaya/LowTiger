package game.environment;

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

    public int getBulletSpeed()
    {
        return bulletSpeed;
    }
}
