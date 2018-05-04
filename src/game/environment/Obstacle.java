package game.environment;

import game.GameObject;
import game.manager.Main;

import java.awt.*;

public class Obstacle extends  GameObject
{
    private Image image = loadImageFile("/static/elements/street.jpg");
    public Obstacle()
    {
        super();
    }

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
