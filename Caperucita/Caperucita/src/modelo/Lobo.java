package modelo;

public class Lobo extends Personaje
{
    private boolean vivo;

    public Lobo(String id, String nombre, String tipo, boolean vivo)
    {
        super(id, nombre, tipo);
        this.vivo = vivo;
    }

    public String hablar()
    {
        return getNombre() + " dice: ¡Te voy a comer!";
    }

    public String disfrazarse()
    {
        return getNombre() + " se está disfrazando de abuela...";
    }

    public String correr()
    {
        return getNombre() + " está corriendo por el bosque.";
    }

    public String tragar()
    {
        return getNombre() + " ha tragado a alguien.";
    }

    public boolean isVivo()
    {
        return vivo;
    }

    public void setVivo(boolean vivo)
    {
        this.vivo = vivo;
    }
}
