package game;

import game.manager.Main;
import game.environment.*;
import game.actor.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Hitbox allows to define boundaries for our game objects in order to get collisions.
 *
 * @author Juan Alcantara
 * @author José Hernández
 * @version %I%
 * @since 1.0
 * */

public class Hitbox extends Rectangle
{
    private static int lastID = 0;
    private int ID;
    protected final ArrayList<GameObject> collisions = new ArrayList<>();
    protected final ArrayList<Item> items = new ArrayList<>();

    public Hitbox()
    {
        super(0,0,0,0);
        ID = lastID;
        lastID++;
    }

    /**
     * Hitbox constructor. Receives parameters directly from the GameObject where it is initialized
     *
     * @param x x-coordinate in pixels
     * @param y y-coordinate in pixels
     * @param width width in pixels
     * @param height height in pixels
     *
     * @see GameObject
     */
    public Hitbox(int x, int y, int width, int height)
    {
        super(x, y, width, height);
        ID = lastID;
        lastID++;
    }

    public int hitboxGetX()
    {
        return (int) getX();
    }

    public int hitboxGetY()
    {
        return (int) getY();
    }

    public int hitboxGetWidth()
    {
        return (int) getWidth();
    }

    public int hitboxGetHeight()
    {
        return (int) getHeight();
    }

    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }

    /**
     * Retrieves all the objects that are currently in the screen and colliding with this Hitbox. Only consider objects
     * of the Player and Obstacle class.
     *
     * @return ArrayList containing all colliding elements
     * @see Player
     * @see Obstacle
     */
    public ArrayList<GameObject> getCollisions()
    {
        collisions.clear();
        ArrayList<GameObject> elements = Main.getInstance().getCurrentLevel().getElements();
        Iterator<GameObject> elementsIterator = elements.iterator();
        GameObject element;
        while(elementsIterator.hasNext())
        {
            element = elementsIterator.next();
            Hitbox elementHitbox = element.getHitbox();
                if(!elementHitbox.equals(this) && elementHitbox.intersects(this))
                {
                    if(element instanceof Obstacle || element instanceof Player)
                    {
                        collisions.add(element);
                    }
                }
        }
        return collisions;
    }

    /**
     * Verifies that the Hitbox is touching a Ground object
     *
     * @return <code>true</code> if the Hitbox is colliding with a Ground object; <code>false</code> otherwise.
     * @see Ground
     */
    public boolean isCollidingGround()
    {
        ArrayList<GameObject> elements = getCollisions();
        Iterator<GameObject> elementsIterator = elements.iterator();
        GameObject element;
        while(elementsIterator.hasNext())
        {
            element = elementsIterator.next();
            if(element instanceof Ground)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all Item objects in screen that are colliding with this Hitbox
     *
     * @return ArrayList of all the Item objects colliding
     * @see Item
     */
    public ArrayList<Item> getItems()
    {
        items.clear();
        ArrayList<GameObject> elements = Main.getInstance().getCurrentLevel().getElements();
        Iterator<GameObject> elementsIterator = elements.iterator();
        GameObject element;
        while(elementsIterator.hasNext())
        {
            element = elementsIterator.next();
            if(element instanceof Item){
                Hitbox elementHitbox = element.getHitbox();
                if(!elementHitbox.equals(this) && elementHitbox.intersects(this))
                {
                    items.add((Item) element);
                }
            }

        }
        return items;
    }

    /**
     * Retrieve Ground object currently colliding with this Hitbox
     *
     * @return Ground object if found; <code>null</code> otherwise.
     * @see Ground
     */
    public Ground getGround()
    {
        if(isCollidingGround())
        {
            ArrayList<GameObject> obstacles = getCollisions();
            Iterator<GameObject> elementsIterator = obstacles.iterator();
            GameObject element;
            while(elementsIterator.hasNext())
            {
                element = elementsIterator.next();
                if(element instanceof Ground){
                    return (Ground) element;
                }
            }
        }
        return null;
    }

    /**
     * Detects collisions with Bullet and Melee objects to calculate the damage due for the Actor
     *
     * @return amount of damage received
     * @see Bullet
     * @see Melee
     * @see Actor
     */
    public int getDamage()
    {
        ArrayList<GameObject> elements = Main.getInstance().getCurrentLevel().getElements();
        Iterator<GameObject> elementsIterator = elements.iterator();
        GameObject element;
        while(elementsIterator.hasNext())
        {
            element = elementsIterator.next();
            if(element instanceof Bullet || element instanceof Melee)
            {
                Hitbox elementHitbox = element.getHitbox();
                if(!elementHitbox.equals(this) && elementHitbox.intersects(this))
                {
                    if(element instanceof Bullet)
                    {
                        ((Bullet) element).collide();
                    }
                    return ((Item)element).getDamage();
                }
            }

        }
        return 0;
    }

    /**
     * Detects if the current Hitbox is colliding with an Actor. Possible to specify if interested in collisions with
     * the head
     * @param head determine if the collision detection should only consider the head.
     * @return <code>true</code> if colliding with Actor; <code>false</code> otherwise.
     * @see Actor
     */
    public boolean isCollidingActor(boolean head)
    {
        ArrayList<GameObject> collisions = getCollisions();
        Iterator<GameObject> elementsIterator = collisions.iterator();
        GameObject element;
        while(elementsIterator.hasNext())
        {
            element = elementsIterator.next();
            if(element instanceof Actor)
            {
                if(head)
                {
                    if(element.getY() >= y)
                    {
                        return true;
                    }
                    continue;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hitbox hitbox = (Hitbox) o;
        return ID == hitbox.ID;
    }
}
