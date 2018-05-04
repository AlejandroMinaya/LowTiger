package game.environment;

import game.GameObject;
import game.manager.Main;

import java.awt.*;

/**
 * Obstacle objects are meant as platforms and boundaries for Actors and Items. It allows for free range movement across
 * more than one axis and avoids falls.
 *
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class Obstacle extends  GameObject
{
    private Image image = loadImageFile("/static/elements/street.jpg");
    public Obstacle()
    {
        super();
    }

    /**
     * Allows to create an object by providing its dimensions and initial position
     * @param x x-coordinate in pixels
     * @param y y-coordinate in pixels
     * @param width width in pixels
     * @param height height in pixels
     */
    public Obstacle(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        g.drawImage(image, x, y, width, height, Main.getInstance().getCurrentLevel());
    }
}
