package game.environment;

/**
 * Special Obstacle class that allows the distinction between regular obstacles and jumpable obstacles.
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class Ground extends Obstacle
{
    public Ground()
    {
        super();
    }

    public Ground(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }
}
