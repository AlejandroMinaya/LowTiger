package game;

import game.manager.Main;
import game.environment.*;
import game.actor.*;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

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

    public boolean isCollidingActor()
    {
        return isCollidingActor(false);
    }

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
