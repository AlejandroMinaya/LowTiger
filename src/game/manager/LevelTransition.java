package game.manager;

import game.GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * LevelTransition is a special class designed to allow the user to start the next level when she/he is ready.
 *
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class LevelTransition extends Level
{
    private boolean proceed = false;

    /**
     * This constructor received the level to load after the transition.
     * @param nextLevel next level to load
     */
    LevelTransition(Level nextLevel)
    {
        super();
        this.nextLevel = nextLevel;
        background = GameObject.loadImageFile("/static/level/continue.jpg");
    }

    public void init()
    {
        name = "Level Transition";
        setBackground(Color.BLUE);
        elements.remove(PLAYER);
    }

    /**
     * This override allows the Thread to be stopped once the user presses ENTER
     */
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

    /**
     * This override tries to join the Thread but ultimately interrupts it.
     */
    @Override
    public void terminate()
    {
        try
        {
            levelThread.join();
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }finally {
            levelThread.interrupt();
        }
    }


    /**
     * Receives pressed key until it receives ENTER to stop the thread.
     * @param e pressed key event
     */
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
