package game.environment;

/**
 * Special Item that modifies the default properties of an Actor in terms of health, defense or offense.
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class Melee extends Item
{
    public Melee()
    {
        super();
    }

    public Melee(int x, int y, int width, int height, int damage)
    {
        super(x, y, width, height, damage);
    }
}
