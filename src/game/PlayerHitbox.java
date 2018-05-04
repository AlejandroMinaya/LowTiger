package game;

import game.manager.*;
import game.environment.*;
import game.actor.*;
import java.util.ArrayList;

/**
 * PlayerHitbox can only be used by the Player object. It is meant to detect specific Player collisions with enemies and
 * such.
 *
 * @author Juan Alcántara
 * @author José Hernández
 * @version %I%
 * @since 1.0
 */
public class PlayerHitbox extends Hitbox
{
    public PlayerHitbox(int x, int y, int width, int height)
    {
        super(x, y, width, height);
    }

    /**
     * Detects collisions with Obstacle or Enemy objects.
     *
     * @return ArrayList of colliding GameObjects
     */
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
