package game.manager;

import game.GameObject;

import java.awt.*;

/**
 * Victory is a singleton special level to alert the user that he/she won.
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class Victory extends Level
{
    private static Victory instance = new Victory();
    private Victory()
    {
        super();
    }

    public void init()
    {
        setBackground(Color.BLACK);
        nextLevel = StartMenu.getInstance();
        background = GameObject.loadImageFile("/static/level/win.jpg");
        enemyCount = 1;
        name = "Victory";
    }

    public static Victory getInstance()
    {
        return instance;
    }
}
