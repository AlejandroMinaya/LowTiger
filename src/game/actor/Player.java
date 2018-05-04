package game.actor;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import game.PlayerHitbox;
import game.manager.Main;
import game.Hitbox;

/**
 * Player is a singleton class to represent the character the user will be using for the game. Its actions are only activated through the user's interaction and can only be instantiated once.
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
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

    /**
     * Depending on the key pressed the Main Player performs various actions:
     * RIGHT ARROW KEY = moves to the right
     * LEFT ARROW KEY = moves to the left
     * SPACEBAR = jumps if in contact with the ground
     * X = shoots if Firearm equipped
     *
     * This actions change the values of certain flags to later be processed by move()
     * @param e pressed key event
     */
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

    /**
     * Register when the user has stopped certain keys to change certain flags to later be processed by move()
     * @param e released key event
     */
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

    /**
     * This overried allows for the customisation of movement. It avoids the user moving when pressing two keys at the
     * time.
     */
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
