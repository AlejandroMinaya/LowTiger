package game.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

import game.GameObject;
import game.actor.Actor;
import game.actor.Player;
import game.environment.*;

public class Level extends JPanel implements Runnable
{
    private final Canvas canvas = new Canvas();
    private final Player PLAYER = Player.getInstance();
    private ArrayList<GameObject> elements = new ArrayList<>();
    Level()
    {
        init();
    }

    private void init()
    {
        setSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        add(canvas);

        elements.add(PLAYER);
        elements.add(new Ground(0, Main.WINDOW_HEIGHT - 80, Main.WINDOW_WIDTH, 100));
        elements.add(new Firearm(400, Main.WINDOW_HEIGHT - 150, 50, 50));
    }

    public void addComponent(GameObject gobj)
    {
        elements.add(gobj);
    }

    public ArrayList<GameObject> getElements()
    {
        return elements;
    }

    private void step()
    {
        for(GameObject element : elements)
        {
            if(element instanceof Actor)
            {
                Actor actor = (Actor) element;
                actor.move();
                actor.jump();
            }
            else if(element instanceof Item)
            {
                Item item = (Item) element;
                item.move();
            }

        }
//        PLAYER.move();
//        PLAYER.jump();
    }

    @Override
    public void paint(Graphics g)
    {
        for(GameObject element : elements)
        {
            element.draw(g);
            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void addNotify()
    {
        super.addNotify();

        Thread levelThread = new Thread(this);
        levelThread.start();
    }

    @Override
    public void run()
    {
        long startTime, timeDiff, sleep;

        startTime = System.currentTimeMillis();

        while(true)
        {
            step();
            repaint();
            timeDiff = System.currentTimeMillis() - startTime;
            sleep = 20 - timeDiff;

            if(sleep < 0)
            {
                sleep = 2;
            }

            try
            {
                Thread.sleep(sleep);
            } catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
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
