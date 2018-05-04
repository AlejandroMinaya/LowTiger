package game.manager;

import java.awt.*;

import game.GameObject;
import game.environment.*;
import game.actor.*;
/**
 * Level2 is a special Level representing the last game level
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class Level2 extends Level
{
    public Level2()
    {
        super();
    }
    public void init()
    {
        //SETTINGS
        name = "Level 2";
        setBackground(Color.BLACK);
        nextLevel = Victory.getInstance();
        background = GameObject.loadImageFile("/static/level/puente.jpg");

        //CHARACTERS
        // - PLAYER
        // - ENEMIES
        addEnemy(new Secuaz(300, 500));
        addEnemy(new Yakuza(700, 500));

        //ENVIRONMENT
        elements.add(new Ground(0, Main.WINDOW_HEIGHT - 80, Main.WINDOW_WIDTH, 100));
        elements.add(new Firearm(400, Main.WINDOW_HEIGHT - 150, 50, 50, 10));
        elements.add(new Ground(500, 600, 200, 100));

    }

    @Override
    public void addNotify()
    {
        super.addNotify();
        levelThread.setName("Level 2");
        System.out.println("Enemy count: " + enemyCount);
    }

}
