public class Personajes
{
    protected int vida;
    protected Item equipado;
    protected int dano;
    protected double velocidad;

    public Personajes()
    {

        vida= 100;
        equipado= null;
        dano= 0;
        velocidad= 0.0;

    }

public adelante()
{
x++;y++;
hitbox(x,y);
raycast(x,y);
}
public atras()
{
    x--;y--;
    hitbox(x,y);
    raycast(x,y);

}


    public avanza(boolean delante)
    {
if(delante==true){

    adelante
}
else{


}


    }

}