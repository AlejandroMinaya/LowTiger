package game.manager;

import java.awt.*;

import game.GameObject;
import game.environment.*;
import game.actor.*;
import game.ui.*;

/**
 * Level1 is a special Level representing the inital game level
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class Level1 extends Level
{
    public Level1()
    {
        super();
    }

    public void init()
    {
        //SETTINGS
        name = "Level 1";
        setBackground(Color.BLACK);
        nextLevel = new Level2();
        background = GameObject.loadImageFile("/static/level/techo.jpg");

        //CHARACTERS
        // - ENEMIES
        addEnemy(new Pacino(900, 500));
        elements.add(new Ground(0, Main.WINDOW_HEIGHT - 80, Main.WINDOW_WIDTH, 100));
        elements.add(new Firearm(400, Main.WINDOW_HEIGHT - 150, 50, 50, 10));
        elements.add(new Ground(500, 600, 200, 100));

    }

    @Override
    public void addNotify()
    {
        super.addNotify();
        levelThread.setName("Level 1");
    }
}
