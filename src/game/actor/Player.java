package game.actor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import game.PlayerHitbox;
import game.manager.Main;
import game.Hitbox;

public class Player extends Actor
{
    private static Player instance = new Player();
    private Player()
    {
        super(100 , Main.WINDOW_HEIGHT - 210, 80, 100, 100, 10, 10, "player");
        hitbox = new PlayerHitbox(x, y, width, height);
    }

    public static Player getInstance()
    {
        return instance;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT)
        {
            dx = step;
            releasedKeys[0] = false;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            dx = -1 * step;
            releasedKeys[1] = false;
        }
        if(key == KeyEvent.VK_SPACE)
        {
            jumping = true;
        }
        if(key == KeyEvent.VK_Q)
        {
            drop();
        }
        if(key == KeyEvent.VK_X)
        {
            shoot();
        }
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT)
        {
            releasedKeys[0] = true;
        }
        else if(key == KeyEvent.VK_LEFT)
        {
            releasedKeys[1] = true;
        }
    }

    @Override
    public void move()
    {
        if(releasedKeys[0] && releasedKeys[1])
        {
            state = "idle";
            dx = 0;
        }
        if(releasedKeys[0] != releasedKeys[1])
        {
            super.move();
        }
    }
}
