package ObjetoJuego.Item;

public class ArmaFuego extends Item
{
    private int ammoCapacidad;
    private int ammoDisponible;
    private int ammo;

    public ArmaFuego(int dano, int usabilidad, boolean equipada, int ammoCapacidad, int ammoDisponible, int ammo)
    {
        super(dano, usabilidad, equipada);
        this.ammoCapacidad = ammoCapacidad;
        this.ammoDisponible = ammoDisponible;
        this.ammo = ammo;
    }

    public ArmaFuego()
    {
        ammoCapacidad = 0;
        ammoDisponible = 0;
        ammo = 0;
    }
}
