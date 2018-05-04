package game;

import java.awt.geom.Line2D;
import game.actor.*;
import game.environment.*;
import game.manager.Main;

public class Raycast extends Line2D.Double
{
    private int length;
    private int height;
    public Raycast()
    {
        super();
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
    }

    public Raycast(int x, int height, int length)
    {
        super();
        this.length = length;
        this.height = height;
        x1 = x;
        x2 = x + length * 10;
        y1 = height;
        y2 = height;
    }

    public int getRaycastX1()
    {
        return (int) x1;
    }

    public int getRaycastX2()
    {
        return (int) x2;
    }

    public int getRaycastY1()
    {
        return (int) y1;
    }

    public int getRaycastY2()
    {
        return (int) y2;
    }

    public void setX(int x, boolean facingRight)
    {
        if(facingRight)
        {
            x1 = x;
            x2 = x + length * 10;
        }
        else{
            x1 = x;
            x2 = x - length * 10;
        }
    }

    public void setY(int y)
    {
        y1 = y;
        y2 = y;
    }

    public boolean isTargetInRange(Actor target)
    {
        if(target == null || target.getHitbox().equals(this))
        {
            return false;
        }
        Hitbox targetHitbox = target.getHitbox();
        if(this.intersects(targetHitbox))
        {
            return true;
        }
        return false;
    }

    public boolean isObstacleInRange()
    {
        for(GameObject element : Main.getInstance().getCurrentLevel().getElements())
        {
            if(element instanceof Obstacle && this.intersects(element.getHitbox()))
            {
                    return true;
            }
        }
        return false;
    }

    public boolean isEnemyInRange()
    {
        for(GameObject element : Main.getInstance().getCurrentLevel().getElements())
        {
            if(element instanceof Enemy && this.intersects(element.getHitbox()))
            {
                return true;
            }
        }
        return false;
    }
}
