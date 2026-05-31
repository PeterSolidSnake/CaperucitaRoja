package modelo;

public class Caperucita extends Personaje
{
    private boolean rescatada;
    private Cesta cesta;

    public Caperucita(String id, String nombre, String tipo, boolean rescatada)
    {
        super(id, nombre, tipo);
        this.rescatada = rescatada;
        this.cesta = new Cesta(false, false);
    }

    public boolean visitarAbuela()
    {
        return rescatada;
    }

    public boolean isRescatada()
    {
        return rescatada;
    }

    public void setRescatada(boolean rescatada)
    {
        this.rescatada = rescatada;
    }

    public Cesta getCesta()
    {
        return cesta;
    }

    public void setCesta(Cesta cesta)
    {
        this.cesta = cesta;
    }
}
