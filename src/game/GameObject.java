package game;

import game.manager.Main;

import java.awt.*;
import javax.swing.*;
import java.awt.Color;
import java.io.File;


/**
 * GameObject is a generic class to depict elements in our game that need xy-coordinates, size and a hitbox
 *
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @since 1.0
 */
public class GameObject extends JPanel
{
    protected int x, y;
    protected int lastX, lastY;
    protected int width, height;
    protected Hitbox hitbox;

    public GameObject()
    {
        x = 0;
        y = 0;
        lastX = 0;
        lastY = 0;
        width = 0;
        height = 0;
        hitbox = new Hitbox(0,0,0,0);
        init();
    }

    /**
     * Creates a GameObject with a default Hitbox given by the parameters
     *
     * @param x x-coordinate in pixels
     * @param y y-coordinate in pixels
     * @param width width in pixels
     * @param height width in pixels
     *
     * @see Hitbox
     */

    public GameObject(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hitbox = new Hitbox(x, y, width, height);
        init();
    }

    /**
     * The method initializes the GameObject by setting its size and the JPanel default double buffer.
     * */

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

    public void setX(int x)
    {
        this.x = x;
    }

    @Override
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
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

    /**
     * Draws the GameObject into the give Graphics Component. If the DEBUG option in true in the Main class, it includes
     * a bounding box for the game element.
     *
     * @param g Craphics Component received from PaintComponent method from a JComponent
     * */

    public void draw(Graphics g)
    {
        if(x == lastX && y == lastY)
            return;

        if(Main.DEBUG)
        {
            g.setColor(new Color(0, 255, 0));
            g.drawRect(hitbox.hitboxGetX(), hitbox.hitboxGetY(), hitbox.hitboxGetWidth(), hitbox.hitboxGetHeight());
        }
        g.setColor(new Color(0, 0, 0));
    }

    /**
     * It retrieves the image from the specified directory and loads it.
     *
     * @param file root path to the image
     * @return Image object
     * */

    public static Image loadImageFile(String file)
    {
        String basePath = new File("").getAbsolutePath() + "/src";
        Image img = (new ImageIcon(basePath + file)).getImage();
        return img;
    }
}
