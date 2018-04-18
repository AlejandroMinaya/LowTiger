package game.manager;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements KeyListener
{
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 800;
    public static final boolean DEBUG = true;
    private static Main instance = new Main();
    private Level currentLevel;
    private Main()
    {
        init();
    }

    public void init()
    {
        //TEMPORAL LEVEL
        currentLevel = new Level();

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

    public void keyPressed(KeyEvent e)
    {
        currentLevel.keyPressed(e);

    }

    public void keyTyped(KeyEvent e)
    {

    }

    public void keyReleased(KeyEvent e)
    {
        currentLevel.keyReleased(e);

    }

    public static Main getInstance()
    {
        return instance;
    }

    public static void main(String[] args)
    {
       EventQueue.invokeLater(() -> {
           JFrame game = getInstance();
           game.setVisible(true);
       });
    }
}
