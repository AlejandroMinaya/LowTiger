package game.actor;

import game.GameObject;
import game.manager.Main;
import game.Hitbox;
import game.Raycast;
import game.environment.*;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.util.HashMap;

public abstract class Actor extends GameObject
{
    protected String state;
    protected String character;
    protected final int STEP = 10;
    protected final int DEFAULT_GRAVITY = 1;
    protected int gravity = DEFAULT_GRAVITY;
    protected final int MAX_GRAVITY = DEFAULT_GRAVITY * 20;
    protected int targetAltitude = y;
    protected int maxRelativeAltitude = DEFAULT_GRAVITY * 200;
    protected boolean jumping = false;
    protected boolean facingRight = true;
    protected int dx;
    protected int range;
    protected int damage;
    protected int health;
    protected boolean[] releasedKeys = {true, true};
    protected Raycast raycast;
    protected Item equippedItem;
    protected HashMap<String, String> sprites = new HashMap<>();

    Actor()
    {
        super();
        state = "idle";
    }

    Actor(int x, int y, int width, int height, int health, int damage, int range, String character)
    {
        super(x, y, width, height);
        this.health = health;
        this.damage = damage;
        this.range = range;
        this.character = character;
        loadSprites();
        raycast = new Raycast(x + width/2, y + height/3, range);
    }

    public void loadSprites()
    {
//        sprites.put("idle", "static/"+character+"/idle.gif");
        sprites.put("idle", "../../static/"+character+"/idle.png");
        sprites.put("walk", "../../static/"+character+"/walk.gif");
    }

    public Image loadImage()
    {
        ImageIcon image = new ImageIcon(sprites.get(state));
        return image.getImage();
    }

    public void drop()
    {
        int xOffset = x + width * 2;
        if(!facingRight)
        {
            xOffset = x - width * 2;
        }
        if(equippedItem != null)
        {

            equippedItem.drop(xOffset, y);
        }
        equippedItem = null;
    }

    public void equip(Item item)
    {
        equippedItem = item;
        item.equip();
    }

    public void shoot()
    {
        if(equippedItem != null && equippedItem instanceof Firearm)
        {
            int speed = ((Firearm) equippedItem).getBulletSpeed();
            if(!facingRight)
            {
                speed *= -1;
            }
            Main.getInstance().getCurrentLevel().addComponent(new Bullet(raycast.getRaycastX2(), raycast.getRaycastY1(), 10, 5, speed, equippedItem.getDamage()));
        }
    }

    public void move()
    {
        if(releasedKeys[0] && releasedKeys[1])
        {
            dx = 0;
        }
        if(dx > 0)
        {
            facingRight = true;
        }
        else if(dx < 0)
        {
            facingRight = false;
        }
        if(releasedKeys[0] != releasedKeys[1])
        {
            if((x + dx/2) > 0 && (x + width + dx/2) < Main.WINDOW_WIDTH)
            {
                hitbox.setX(x + dx/2);
                if(hitbox.getCollisions().size() < 1)
                {
                    x += dx;
                }
                hitbox.setX(x);
                raycast.setX(x + width/2, facingRight);
            }
        }
    }

    public void jump()
    {
        Hitbox tmpHitbox = hitbox;
        tmpHitbox.setY(y + gravity);

        if(jumping && tmpHitbox.isCollidingGround())
        {
            targetAltitude = y - maxRelativeAltitude;
            jumping = false;
            gravity = MAX_GRAVITY;
        }

        if(targetAltitude < y)
        {
            y -= gravity;
            if(gravity > DEFAULT_GRAVITY)
            {
                gravity -= DEFAULT_GRAVITY;
            }
            else {gravity = DEFAULT_GRAVITY;}
        }
        else if(!tmpHitbox.isCollidingGround())
        {
            y += gravity;
            if(gravity < MAX_GRAVITY)
            {
                gravity += DEFAULT_GRAVITY;
            }
            else{gravity = MAX_GRAVITY;}
            targetAltitude = y;


        }
        else if(tmpHitbox.isCollidingGround())
        {
            Ground ground = tmpHitbox.getGround();
            gravity = ground.getY() - (y + height);
            y += gravity;
        }
        hitbox.setY(y);
        raycast.setY(y + height/3);
    }

    public void hurt()
    {
        System.out.println(character + ": " + health);
        if(health <=0 )
        {
            x = 0;
            y = 0;
            width = 0;
            height = 0;
            hitbox = new Hitbox(0,0,0,0);
            raycast = new Raycast(0,0,0);
        }
        else{
            health -= hitbox.getDamage();
        }
    }

    public void equip()
    {
        if(hitbox.getItems().size() > 0)
        {
            if(equippedItem == null)
            {
                equip(hitbox.getItems().get(0));
            }
        }
    }


    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        if(Main.DEBUG)
        {
            g.setColor(new Color(0, 255, 0));
            g.drawLine(raycast.getRaycastX1(), raycast.getRaycastY1(), raycast.getRaycastX2(), raycast.getRaycastY2());
        }
        g.setColor(new Color(0, 0, 0));
        g.drawImage(loadImage(), x, y, Main.getInstance());
    }
}
