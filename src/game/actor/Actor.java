package game.actor;

import game.GameObject;
import game.manager.Main;
import game.Hitbox;
import game.Raycast;
import game.environment.*;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.util.HashMap;
import java.io.File;

public abstract class Actor extends GameObject
{
    protected String state;
    protected String prevState;
    protected String character;
    protected int step = 10;
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
    protected boolean alive;
    protected Raycast raycast;
    protected Item equippedItem;
    protected HashMap<String, String> sprites = new HashMap<>();

    Actor()
    {
        super();
        state = "idle";
        prevState = "";
        alive = true;
    }

    Actor(int x, int y, int width, int height, int health, int damage, int range, String character)
    {
        super(x, y, width, height);
        this.health = health;
        this.damage = damage;
        this.range = range;
        this.character = character;
        state = "idle";
        alive = true;
        prevState = "";
        raycast = new Raycast(x + width/2, y + height/3, range);
        loadSprites();
    }

    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
        hitbox.setX(x);
        hitbox.setY(y);
        raycast.setX(x + width/2, facingRight);
        raycast.setY(y + height/3);
    }

    private void loadSprites()
    {
        String basePath = new File("").getAbsolutePath() + "/src";
        sprites.put("idle", basePath+"/static/"+character+"/idle.gif");
        sprites.put("walk", basePath+"/static/"+character+"/walk.gif");
        sprites.put("jump", basePath+"/static/"+character+"/walk.gif");
        System.out.println(sprites.get("idle"));
    }

    private Image loadImage()
    {
        return (new ImageIcon(sprites.get(state))).getImage();
    }

    public void setHealth(int health)
    {
        this.health = health;
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
        if(alive)
        {
            if (equippedItem != null && equippedItem instanceof Firearm) {
                Firearm equippedFirearm = (Firearm) equippedItem;
                int origin = range * 5;
                if(!facingRight)
                {
                    origin *= -1;
                }
                equippedFirearm.shoot(raycast.getRaycastX2() - origin, raycast.getRaycastY2(), facingRight);
            }
        }
    }

    public void move()
    {
        if(alive)
        {
            if(dx > 0)
            {
                facingRight = true;
            }
            else if(dx < 0)
            {
                facingRight = false;
            }
            if((x + dx) > 0 && (x + width + dx) < Main.WINDOW_WIDTH)
            {
                hitbox.setX(x + dx);
                if(hitbox.getCollisions().size() < 1)
                {
                    state = "walk";
                    x += dx;
                }
                hitbox.setX(x);
                raycast.setX(x + width/2, facingRight);
            }
        }
    }

    public void jump()
    {
        if(alive)
        {
            Hitbox tmpHitbox = hitbox;
            tmpHitbox.setY(y + gravity);

            if(jumping && (tmpHitbox.isCollidingGround() || tmpHitbox.isCollidingActor(true)))
            {
                targetAltitude = y - maxRelativeAltitude;
                jumping = false;
                gravity = MAX_GRAVITY;
            }

            if(targetAltitude < y)
            {
                state = "jump";
                y -= gravity;
                if(gravity > DEFAULT_GRAVITY)
                {
                    gravity -= DEFAULT_GRAVITY;
                }
                else {gravity = DEFAULT_GRAVITY;}
            }
            else if(!tmpHitbox.isCollidingGround() && !tmpHitbox.isCollidingActor(true))
            {
                y += gravity;
                if(gravity < MAX_GRAVITY)
                {
                    gravity += DEFAULT_GRAVITY;
                }
                else{gravity = MAX_GRAVITY;}

                targetAltitude = y;

                if(y > Main.WINDOW_HEIGHT)
                {
                    health = 0;
                    return;
                }

            }
            else if(tmpHitbox.isCollidingGround())
            {
                Ground ground = tmpHitbox.getGround();
                if(ground.getY() > (y + height))
                {
                    gravity = ground.getY() - (y + height);
                    y += gravity;
                }
            }
            hitbox.setY(y);
            raycast.setY(y + height/3);

        }
    }

    public void hurt()
    {
        if(health <=0 )
        {
            x = 0;
            y = 0;
            width = 0;
            height = 0;
            hitbox = new Hitbox(0,0,0,0);
            raycast = new Raycast(0,0,0);
            alive = false;
        }
        else{
            int damage = hitbox.getDamage();
            if(damage > 0)
            {
                state = "hurt";
                health -= damage;
            }
        }

    }

    public void hurt(int damage)
    {
        health -= damage;
        state = "hurt";
    }

    public void equip()
    {
        if(alive)
        {
            if(hitbox.getItems().size() > 0)
            {
                if(equippedItem == null)
                {
                    equip(hitbox.getItems().get(0));
                }
            }

        }
    }

    public int getHealth()
    {
        return health;
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
        int imgWidth = width;
        int imgHeight = height;
        int imgX = x + 1;
        if(!facingRight)
        {
            imgWidth *= -1;
            imgX -= imgWidth;
        }
        g.drawImage(loadImage(), imgX, y, imgWidth, imgHeight, Main.getInstance().getCurrentLevel());
    }
}

