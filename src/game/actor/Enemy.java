package game.actor;

public class Enemy extends Actor
{
    public Enemy()
    {
        super();
    }

    public Enemy(int x, int y, int width, int height, int health, int damage, int range, String character)
    {
        super(x, y, width, height, health, damage, range, character);
    }

    @Override
    public void equip(){}
}
