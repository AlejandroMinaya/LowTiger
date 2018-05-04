package game.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import game.GameObject;
import game.actor.*;
import game.environment.*;
import game.ui.HealthBar;


/**
 * Level is where the game takes place. It controls all the objects that are in the screen at that moment and displays
 * them. Each Level object is ran in its own Thread.
 *
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public abstract class Level extends JPanel implements Runnable
{
    protected final static int DELAY = 20;
    protected final Player PLAYER = Player.getInstance();
    protected Level nextLevel;
    protected int enemyCount = 0;
    protected boolean complete = false;
    protected ArrayList<GameObject> elements = new ArrayList<>();
    protected ArrayList<GameObject> stagingArea = new ArrayList<>();
    protected Thread levelThread;
    protected String name;
    protected Rectangle paintingRegion = new Rectangle(0,0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    protected boolean running;
    protected Image background;
    Level()
    {
        //SETTINGS
        setSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        setDoubleBuffered(true);

        //CHARACTERS
        elements.add(PLAYER);
        PLAYER.setHealth(100);

        //GUI
        elements.add(new HealthBar());

        init();
    }

    /**
     * Initializes the level with the correct images and settings.
     */
    public abstract void init();

    /**
     * Adds a GameObject to the Staging Area. The Staging Area is a pre-buffer of all the elements that are going to be
     * included in the screen.
     *
     * @param gobj the GameObject to add
     * @see GameObject
     */
    public void addComponent(GameObject gobj)
    {
            stagingArea.add(gobj);
    }


    /**
     * Adds an Enemy to the Staging Area. The difference with addComponent is the change in the enemyCount instance
     * variable.
     * @param enem the Enemy to add
     */
    public void addEnemy(Enemy enem)
    {
        elements.add(enem);
        enemyCount++;
    }


    /**
     * Removes the objects that are no longer participating in the level, i.e. dead enemies, lost bullets, etc. This is
     * an improvised garbage collector to avoid clutter in the levels
     */
    public void removeTrash()
    {
        boolean remove = false;
        GameObject objToRemove = null;
        Iterator<GameObject> elementsIterator = elements.iterator();
        GameObject obj;
        while(elementsIterator.hasNext())
        {
            obj = elementsIterator.next();
            if(obj instanceof Actor)
            {
                if(((Actor) obj).getHealth() <= 0)
                {
                    if(obj instanceof Enemy)
                    {
                        enemyCount--;
                    }
                    objToRemove = obj;
                    remove = true;
                }
            }

            if(obj instanceof Bullet)
            {
                if(((Bullet)obj).collided())
                {
                    objToRemove = obj;
                    remove = true;
                }
            }
        }
        if(remove)
        {
            elements.remove(objToRemove);
        }

    }

    /**
     * Checks whether the Player is still alive to end the game otherwise.
     * @see Player
     */
    public void gameOver()
    {
        if(PLAYER.getHealth() == 0)
        {
            terminate();
            PLAYER.setHealth(100);
        }
    }

    /**
     * Checks whether there are no enemies left in the screen to move to stop this current level and move onto the next
     * one.
     */
    public synchronized void levelComplete()
    {
        if(enemyCount == 0)
        {
            complete = true;
            PLAYER.drop();
            PLAYER.setPosition(10, 10);
            terminate();
        }
    }

    /**
     * Adds the Staging Area elements to the actual elements list of the level.
     */
    public void addStagingArea()
    {
        Iterator<GameObject> elementsIterator = stagingArea.iterator();
        GameObject obj;
        while(elementsIterator.hasNext())
        {
            obj = elementsIterator.next();
            elements.add(obj);
        }
        stagingArea.clear();
    }

    public ArrayList<GameObject> getElements()
    {
        return elements;
    }

    /**
     * This represent a game cycle. It is the method that trigger all the actions for all the elements within the level.
     * It calls different methods for various Actors, keeps track of pre-buffers, removes clutter and checks whether to
     * pass to the next level or if to call it game over.
     */
    public void step()
    {
        Iterator<GameObject> elementsIterator = elements.iterator();
        GameObject element;
        while(elementsIterator.hasNext())
        {
            element = elementsIterator.next();
            if(element instanceof Enemy)
            {
                Enemy enemy = (Enemy) element;
                enemy.behave();

            }
            if(element instanceof Actor)
            {
                Actor actor = (Actor) element;
                actor.move();
                actor.jump();
                actor.hurt();
                actor.equip();
            }
            else if(element instanceof Item)
            {
                Item item = (Item) element;
                item.move();
            }
        }
        addStagingArea();
        removeTrash();
        levelComplete();
        gameOver();
//        PLAYER.move();
//        PLAYER.jump();
    }

    /**
     * Paints all the elements belonging to this level.
     * @param g Graphics Component where the level is going to be painted
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(background != null)
        {
            g.drawImage(background, 0,0, getWidth(), getHeight(), Main.getInstance().getCurrentLevel());
        }
        Iterator<GameObject> elementsIterator = elements.iterator();
        GameObject element;
        while(elementsIterator.hasNext())
        {
            element = elementsIterator.next();

            synchronized (this)
            {
                element.draw(g);
            }
        }
        paintingRegion = g.getClipBounds();
    }

    /**
     * Initializes the Thread once the level is added to a JComponent content panel.
     */
    @Override
    public void addNotify()
    {
        super.addNotify();
        levelThread = new Thread(this);
        levelThread.setName(name);
        running = true;
        levelThread.start();
        System.out.println("Started new thread: " + levelThread.getName());
    }


    /**
     * Stops the Level, including its thread, to load the next Level.
     */
    public synchronized void terminate()
    {
        running = false;
        if(complete)
        {
            System.out.println("LEVEL COMPLETE");
            Main.getInstance().setCurrentLevel(new LevelTransition(nextLevel));
        }
        else{
            System.out.println("GAME OVER");
            Main.getInstance().setCurrentLevel(GameOver.getInstance());
        }
//        try
//        {
//            levelThread.join();
//        }
//        catch(InterruptedException e)
//        {
//            e.printStackTrace();
//        }
        levelThread.interrupt();
    }

    /**
     * This is the loop that runs in the Thread constantly refreshing the game state. It allows smooth animations and
     * higher frame rates.
     */
    @Override
    public void run()
    {
        long startTime, timeDiff, sleep;

        startTime = System.currentTimeMillis();

        while(true)
        {
            synchronized (this)
            {
                if(!running)
                {
                    break;
                }
            }
            step();
            repaint(paintingRegion);
            timeDiff = System.currentTimeMillis() - startTime;
            sleep = DELAY - timeDiff;

            if(sleep < 0)
            {
                sleep = 2;
            }

            try
            {
                Thread.sleep(sleep);
            } catch(InterruptedException e)
            {
//                e.printStackTrace();
                System.out.println("THREAD ENDED");
            }

            startTime = System.currentTimeMillis();
        }
    }

    /**
     * Tells the Player object that a key has been pressed.
     *
     * @param e pressed key event
     */
    public void keyPressed(KeyEvent e)
    {
        PLAYER.keyPressed(e);
    }


    /**
     *  Tells the Player object that a key has been released.
     *
     * @param e released key event
     */
    public void keyReleased(KeyEvent e)
    {
        PLAYER.keyReleased(e);
    }
}
