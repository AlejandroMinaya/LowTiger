package ObjetoJuego.Actores;
import ObjetoJuego.Item.Item;
public class Enemigo extends Personaje
{
    protected boolean esBoss;

    public Enemigo(int vida, Item equipado, int dano, double velocidad, boolean esBoss)
    {
        super(vida, equipado, dano, velocidad);
        this.esBoss = esBoss;
    }

    public Enemigo(int vida, Item equipado, int dano, double velocidad)
    {
        super(vida, equipado, dano, velocidad);
        esBoss = false;
    }

    public Enemigo(boolean esBoss)
    {
        super();
        this.esBoss = true;
        this.vida *= 2;
    }
    public Enemigo()
    {
        super();
        esBoss = false;
    }
}
