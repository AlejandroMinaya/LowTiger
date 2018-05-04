package game.manager;

import java.awt.event.KeyEvent;

public class StartMenu extends Level
{
    public StartMenu()
    {
        super();
    }

    public void init()
    {
        name = "Start Menu";
        nextLevel = new Level1();
        elements.remove(PLAYER);
        enemyCount = 1;
    }

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
}
