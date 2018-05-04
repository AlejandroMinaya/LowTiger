package game.manager;

import com.sun.tools.javadoc.Start;
import game.GameObject;

import java.awt.event.KeyEvent;

/**
 * StartMenu is a singleton special level to tell the users the controls and allow them to start.
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class StartMenu extends Level
{
    private static StartMenu instance = new StartMenu();
    private StartMenu()
    {
        super();
    }

    public void init()
    {
        name = "Start Menu";
        nextLevel = new Level1();
        elements.remove(PLAYER);
        enemyCount = 1;
        background = GameObject.loadImageFile("/static/level/start.jpg");
    }

    /**
     * This override allows to continue when ENTER is pressed and to stop the game when ESC is pressed.
     *
     * @param e pressed key event
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ENTER)
        {
            synchronized (this)
            {
                this.enemyCount = 0;
                this.levelComplete();
            }
        }
        if(key == KeyEvent.VK_ESCAPE)
        {
            Main.getInstance().dispose();
        }
    }

    public static StartMenu getInstance()
    {
        return instance;
    }
}
