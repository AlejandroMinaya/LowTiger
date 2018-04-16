package game;

import game.manager.Main;
import game.environment.*;
import game.actor.*;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Hitbox extends Rectangle
{
    private static int lastID = 0;
    private int ID;
    private final ArrayList<Obstacle> collisions = new ArrayList<>();
    private final ArrayList<Item> items = new ArrayList<>();
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

    public ArrayList<Obstacle> getCollisions()
    {
        collisions.clear();
        ArrayList<GameObject> elements = Main.getInstance().getCurrentLevel().getElements();
        for(GameObject element : elements)
        {
            if(element instanceof Obstacle){
                Hitbox elementHitbox = element.getHitbox();
                if(!elementHitbox.equals(this) && elementHitbox.intersects(this))
                {
                    collisions.add((Obstacle) element);
                }
            }

        }
        return collisions;
    }

    public boolean isCollidingGround()
    {
        ArrayList<GameObject> elements = Main.getInstance().getCurrentLevel().getElements();
        for(GameObject element : elements)
        {
            if(element instanceof Ground)
            {
                Hitbox elementHitbox = element.getHitbox();
                if(!elementHitbox.equals(this) && elementHitbox.intersects(this))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Item> getItems()
    {
        items.clear();
        ArrayList<GameObject> elements = Main.getInstance().getCurrentLevel().getElements();
        for(GameObject element : elements)
        {
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
            ArrayList<Obstacle> obstacles = getCollisions();
            for(Obstacle element : obstacles)
            {
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
        for(GameObject element : elements)
        {
            if(element instanceof Bullet) //ADD MELEE WEAPON
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Hitbox hitbox = (Hitbox) o;
        return ID == hitbox.ID;
    }
}
