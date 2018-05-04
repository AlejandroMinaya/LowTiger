package game.environment;

import game.GameObject;
import game.Hitbox;

import java.awt.Graphics;

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

    public void equip()
    {
        state = "equipped";
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        hitbox = new Hitbox(x, y, width, height);
    }

    public void drop(int x, int y)
    {
        state = "unequipped";
        this.x = x;
        this.y = y;
        this.height = unequippedHeight;
        this.width = getUnequippedWidth;
        hitbox = new Hitbox(x, y, width, height);
    }

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
