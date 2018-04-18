package game.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.GameObject;
import game.actor.*;
import game.environment.*;


public class Level extends JPanel implements Runnable
{
    private final static int DELAY = 20;
    private final Player PLAYER = Player.getInstance();
    private ArrayList<GameObject> elements = new ArrayList<>();
    private Rectangle paintingRegion = new Rectangle(0,0, Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
    Level()
    {
        init();
    }

    private void init()
    {
        setSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT);
        setDoubleBuffered(true);
        setBackground(Color.BLACK);

        elements.add(PLAYER);
        elements.add(new Enemy(300, 500, 50, 100, 100, 50, 10, "yakuza"));
        elements.add(new Ground(0, Main.WINDOW_HEIGHT - 80, Main.WINDOW_WIDTH, 100));
        elements.add(new Firearm(400, Main.WINDOW_HEIGHT - 150, 50, 50, 10));
        elements.add(new Ground(500, 500, 200, 100));
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
                actor.hurt();
                actor.equip();
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
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for(GameObject element : elements)
        {
            element.draw(g);
//            Toolkit.getDefaultToolkit().sync();
        }
        paintingRegion = g.getClipBounds();
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
