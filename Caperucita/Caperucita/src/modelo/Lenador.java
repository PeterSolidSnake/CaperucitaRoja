package modelo;

public class Lenador extends Personaje
{
    public Lenador(String id, String nombre, String tipo)
    {
        super(id, nombre, tipo);
    }

    public String rescatar()
    {
        return getNombre() + " está rescatando a alguien.";
    }

    public boolean abrirPanza(Lobo lobo)
    {
        if (lobo != null && lobo.isVivo())
        {
            return true;
        }
        return false;
    }

    public boolean rellenarPanza(Lobo lobo)
    {
        if (lobo != null)
        {
            lobo.setVivo(false);
            return true;
        }
        return false;
    }
}
