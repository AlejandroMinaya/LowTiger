package game.manager;


import java.awt.*;

import game.GameObject;
import game.ui.*;

/**
 * GameOver is a singleton special level to alert the user that he/she lost.
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
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
        nextLevel = StartMenu.getInstance();
        background = GameObject.loadImageFile("/static/level/gameover.jpg");
        enemyCount = 1;
        name = "Game Over";
    }

    public static GameOver getInstance()
    {
        return instance;
    }
}
