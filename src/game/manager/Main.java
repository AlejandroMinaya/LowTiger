package game.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is the game window singleton class. This is the window that manages the levels and gets the pressed keys
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class Main extends JFrame implements KeyListener
{
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 800;
    public static final boolean DEBUG = false;
    private static Main instance = new Main();
    private Level currentLevel;
    private Main()
    {
        init();
    }

    /**
     * Initializes the window by setting the starting level, sizes, close behavior, etc.
     */
    public void init()
    {
        currentLevel = StartMenu.getInstance();

        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Low Tiger");

        addKeyListener(this);

        getContentPane().add(currentLevel);

    }

    public Level getCurrentLevel()
    {
        return currentLevel;
    }

    public synchronized void setCurrentLevel(Level lev)
    {
        getContentPane().remove(currentLevel);
        currentLevel = lev;
        getContentPane().add(lev);
    }

    /**
     * Passes the pressed keys to the current level.
     * @param e pressed key event
     */
    public void keyPressed(KeyEvent e)
    {
        currentLevel.keyPressed(e);

    }

    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * Passes the released keys to the current level.
     * @param e released key event
     */
    public void keyReleased(KeyEvent e)
    {
        currentLevel.keyReleased(e);

    }

    public static Main getInstance()
    {
        return instance;
    }

    /**
     * Starts the window
     * @param args default main args
     */
    public static void main(String[] args)
    {
       EventQueue.invokeLater(() -> {
           JFrame game = getInstance();
           game.setVisible(true);
       });
    }
}
