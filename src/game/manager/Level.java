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

    public abstract void init();

    public void addComponent(GameObject gobj)
    {
            stagingArea.add(gobj);
    }

    public void addEnemy(Enemy enem)
    {
        elements.add(enem);
        enemyCount++;
    }

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

    public void gameOver()
    {
        if(PLAYER.getHealth() == 0)
        {
            terminate();
            PLAYER.setHealth(100);
        }
    }

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

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(background != null)
        {
            g.drawImage(background, 0,0, getWidth(), getHeight(), Main.getInstance().getCurrentLevel());
        }
        Iterator<GameObject> elementsIterator = elements.iterator();
        while(elementsIterator.hasNext())
        {
            synchronized (this)
            {
                elementsIterator.next().draw(g);
            }
        }
        paintingRegion = g.getClipBounds();
    }

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
                e.printStackTrace();
            }

            startTime = System.currentTimeMillis();
        }
    }

    public void keyPressed(KeyEvent e)
    {
        PLAYER.keyPressed(e);
    }

    public void keyReleased(KeyEvent e)
    {
        PLAYER.keyReleased(e);
    }
}
