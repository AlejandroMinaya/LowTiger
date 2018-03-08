package ObjetoJuego;

import java.awt.Rectangle;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
public abstract class ObjetoJuego
{
    protected boolean alive;
    protected double[] posicion;
    protected String sprites; //Path to Sprites folder
    protected Rectangle hitbox;
    protected Double raycast;

    public double[] getPosicion() {
        return posicion;
    }

    public void setPosicion(double[] posicion) {
        this.posicion = posicion;
    }

    public String getSprites() {
        return sprites;
    }

    public void setSprites(String sprites) {
        this.sprites = sprites;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public Double getRaycast() {
        return raycast;
    }

    public void setRaycast(Double raycast) {
        this.raycast = raycast;
    }

    public ObjetoJuego()
    {
        alive = true;
        posicion = new double[]{0, 0};
        sprites = "";
        hitbox = new Rectangle(0, 0, 0, 0);
        raycast = new Double(0, 0, 0, 0);
    }

    public ObjetoJuego getColision(ArrayList<ObjetoJuego> objetos)
    {
        for(ObjetoJuego obj : objetos)
        {
            if(this.hitbox.intersects(obj.getHitbox()))
            {
                return obj;
            }
        }
        return null;
    }

    public ObjetoJuego getVission(ArrayList<ObjetoJuego> objetos)
    {
        for(ObjetoJuego obj : objetos)
        {
            if(this.raycast.intersects(obj.getHitbox()))
            {
                return obj;
            }
        }
        return null;
    }

    public void destroy()
    {
        alive = false;
        posicion = new double[]{-1, -1};
        hitbox = new Rectangle(-1, -1, 0, 0);

    }


}
