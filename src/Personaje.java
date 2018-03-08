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

}
