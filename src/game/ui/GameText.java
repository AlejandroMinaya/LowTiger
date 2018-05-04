package game.ui;

import game.GameObject;
import game.manager.Main;
import java.awt.*;

public class GameText extends GameObject
{
    private String message;
    public GameText(String message, int x, int y)
    {
        super(x, y, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        this.message = message;
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
    }
}
