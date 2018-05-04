package game;

import java.awt.geom.Line2D;
import game.actor.*;
import game.environment.*;
import game.manager.Main;

/**
 * Raycast depicts the vision of our Actor objects. It is a line able to collide with Hitbox and other Raycast. The idea
 * is to allow objects to interact without having to collide.
 *
 * @author Juan Alcántara
 * @author José Hernández
 * @version %I%
 * @see Actor
 * @see Hitbox
 * @since 1.0
 */
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

    /**
     * Raycast Constructor. Allows the creation of a Raycast given the x-coordinate, the height, and lenght. This
     * constructor only contemplates horizontal Raycasts objects.
     * @param x x-coordinate
     * @param height y-coordinate for height of the Raycast
     * @param length distance from initial to final x-coordinate.
     */
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

    /**
     * Given an specific Actor it determines if it is within range of the Raycast.
     * @param target Actor to determine if in range.
     * @return <code>true</code> if the Raycast object collides with the given Actor; <code>false</code> otherwise.
     * @see Actor
     */
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

    /**
     * Determines if an Obstacle object is currently colliding with this Raycast.
     *
     * @return <code>true</code> if the Raycast object collides with an Obstacle object. <code>false</code> otherwise.
     * @see Object
     */
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

    /**
     * Determines if an Enemy object is currently colliding with this Raycast.
     *
     * @return <code>true</code> if the Raycast object collides with an Enemy object. <code>false</code> otherwise.
     * @see Enemy
     */
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
