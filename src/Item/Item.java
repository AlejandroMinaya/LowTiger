package Item;

public abstract class Item
{
    protected int dano;
    protected int usabilidad;
    protected boolean equipada;

    public Item(int dano, int usabilidad, boolean equipada)
    {
        this.dano = dano;
        this.usabilidad = usabilidad;
        this.equipada = equipada;
    }

    public Item()
    {
        dano = 0;
        usabilidad = 0;
        equipada = false;
    }
}
