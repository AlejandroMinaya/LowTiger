package game.manager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelTransition extends Level
{
    private boolean proceed = false;
    LevelTransition(Level nextLevel)
    {
        super();
        this.nextLevel = nextLevel;
    }

    public void init()
    {
        name = "Level Transition";
        setBackground(Color.BLUE);
        elements.remove(PLAYER);
    }

    @Override
    public void run()
    {
        while(true)
        {
            repaint();
            synchronized (this){
                if(!running)
                {
                    break;
                }
                if(proceed)
                {
                    Main.getInstance().setCurrentLevel(nextLevel);
                    terminate();
                }
            }
        }
    }

    @Override
    public void terminate()
    {
        try
        {
            levelThread.join();
        }catch(InterruptedException e){e.printStackTrace();}
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            synchronized (this)
            {
                proceed = true;
            }
        }

    }

}
