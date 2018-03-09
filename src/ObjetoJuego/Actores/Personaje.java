package ObjetoJuego.Actores;
import ObjetoJuego.ObjetoJuego;
import ObjetoJuego.Item.Item;
public abstract class Personaje extends ObjetoJuego
{
    protected int vida;
    protected Item equipado;
    protected int dano;
    protected double velocidad;

    public Personaje(int vida, Item equipado, int dano, double velocidad)
    {
        this.vida = vida;
        this.equipado = equipado;
        this.dano = dano;
        this.velocidad = velocidad;
    }

    public Personaje()
    {

        vida = 100;
        equipado = null;
        dano = 0;
        velocidad = 0.0;

    }

    private void adelante()
    {
        this.hitbox.x++;
        this.hitbox.y++;
        this.raycast.x1++;
        this.raycast.x2++;
    }

    private void atras()
    {
        this.hitbox.x--;
        this.hitbox.y--;
        this.raycast.x1--;
        this.raycast.x2--;

    }


    public void mover(boolean delante)
    {
        if(delante)
        {
            adelante();
        }
        else {
            atras();
        }

    }

}
