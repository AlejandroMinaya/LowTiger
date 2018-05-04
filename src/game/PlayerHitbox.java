package game;

import game.manager.*;
import game.environment.*;
import game.actor.*;
import java.util.ArrayList;

public class PlayerHitbox extends Hitbox
{
    public PlayerHitbox(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }

    @Override
    public ArrayList<GameObject> getCollisions()
    {
        collisions.clear();
        ArrayList<GameObject> elements = Main.getInstance().getCurrentLevel().getElements();
        for(GameObject element : elements)
        {
            Hitbox elementHitbox = element.getHitbox();
            if(!elementHitbox.equals(this) && elementHitbox.intersects(this))
            {
                if(element instanceof Obstacle || element instanceof Enemy)
                {
                    collisions.add(element);
                }
            }
        }
        return collisions;
    }
}
