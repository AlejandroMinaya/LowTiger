package game;

import game.manager.Main;

import java.awt.*;
import javax.swing.*;
import java.awt.Color;

public class GameObject extends JPanel
{
    protected int x, y;
    protected int width, height;
    protected Hitbox hitbox;

    public GameObject()
    {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        hitbox = new Hitbox(0,0,0,0);
    }

    public GameObject(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitbox = new Hitbox(x, y, width, height);
        init();
    }

    public void init()
    {
        setSize(width, height);
        setDoubleBuffered(true);
    }

    public Hitbox getHitbox()
    {
        return hitbox;
    }

    @Override
    public int getX()
    {
        return x;
    }

    @Override
    public int getY()
    {
        return y;
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    public void draw(Graphics g)
    {
        if(Main.DEBUG)
        {
            g.setColor(new Color(0, 255, 0));
            g.drawRect(hitbox.hitboxGetX(), hitbox.hitboxGetY(), hitbox.hitboxGetWidth(), hitbox.hitboxGetHeight());
        }
        g.setColor(new Color(0, 0, 0));
    }
}