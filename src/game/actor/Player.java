package game.actor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import game.manager.Main;
import game.Hitbox;

public class Player extends Actor
{
    private static Player instance = new Player();
    private Player()
    {
        super(100 , Main.WINDOW_HEIGHT - 210, 80, 100, 100, 10, 10, "player");
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
            dx = STEP;
            releasedKeys[0] = false;
        }
        if(key == KeyEvent.VK_LEFT)
        {
            dx = -1 * STEP;
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
//    @Override
//    public void draw(Graphics g) {
////        ImageIcon ii = new ImageIcon("../../static/player/idle.gif");
////        g.drawImage(ii.getImage(), x, y, Main.getInstance());
//        g.setColor(new Color(255,255,0));
//        g.fillOval(x, y, width, height);
//    }
}
