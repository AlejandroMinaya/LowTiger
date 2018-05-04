package game.environment;

import game.GameObject;
import game.Hitbox;
import game.actor.Actor;

import java.awt.Graphics;

/**
 * Item is designed for the game elements Actor can perform certain actions with, e.g.: weapons, powerups, vehicles.
 *
 * @author Juan Alcantara
 * @author Jose Hernandez
 * @version %I%
 * @see Actor
 * @since 1.0
 */
public class Item extends GameObject
{
    private String state;
    private int unequippedHeight;
    private int getUnequippedWidth;
    private int damage;
    private final int STEP = 100;
    private int initialY;
    public Item()
    {
        super();
        state = "unequipped";
        unequippedHeight = 0;
        getUnequippedWidth = 0;
        initialY = 0;
    }

    /**
     * Allows the creation of an Item giving it a dimension, position and damage
     * @param x x-coordinate in pixels when unequipped
     * @param y y-coordinate in pixels when unequipped
     * @param width width in pixels
     * @param height height in pixels
     * @param damage damage the item causes
     */
    public Item(int x, int y, int width, int height, int damage)
    {
        super(x, y, width, height);
        unequippedHeight = height;
        getUnequippedWidth = width;
        state = "unequipped";
        this.damage = damage;
        initialY = y;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * Nullifies all the properties of the object so that another Actor can't accidentally equip it
     */
    public void equip()
    {
        state = "equipped";
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        hitbox = new Hitbox(x, y, width, height);
    }

    /**
     * Allows the Actor to dispose of its weapon in case it wants to equip another one.
     * @param x x-coordinate once drop in pixels
     * @param y y-coordinate once drop in pixels
     */
    public void drop(int x, int y)
    {
        state = "unequipped";
        this.x = x;
        this.y = y;
        this.height = unequippedHeight;
        this.width = getUnequippedWidth;
        hitbox = new Hitbox(x, y, width, height);
    }

    /**
     * Override to avoid all invvoluntary movement
     */
    public void move()
    {
    }

    public int getDamage()
    {
        return damage;
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        if(state.equals("unequipped"))
        {

        }
        else if(state.equals("equipped"))
        {

        }
    }
}
