package game.ui;

import game.GameObject;
import game.actor.*;
import java.awt.*;

public class HealthBar extends GameObject
{
    private int width;
    private static final int MAX_WIDTH = 200;
    private static final int HEIGHT = 50;
    private final Player PLAYER = Player.getInstance();
    public HealthBar()
    {
        super(10, 10, MAX_WIDTH, HEIGHT);
        width = MAX_WIDTH;
    }

    private void updateWidth()
    {
        width = Math.round(200 * (PLAYER.getHealth()/(float)100));
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.GREEN);
        updateWidth();
        g.fillRect(x, y, width, HEIGHT);
    }

}
