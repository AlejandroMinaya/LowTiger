package game.manager;


import java.awt.*;

import game.GameObject;
import game.ui.*;

public class GameOver extends Level
{
    private static GameOver instance = new GameOver();
    private GameOver()
    {
        super();
    }

    public void init()
    {
        setBackground(Color.BLACK);

        elements.add(new GameText("GAME OVER", 100, 100));
        enemyCount = 1;
    }

    public static GameOver getInstance()
    {
        return instance;
    }
}
