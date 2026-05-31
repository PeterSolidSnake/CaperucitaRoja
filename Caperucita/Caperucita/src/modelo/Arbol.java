package modelo;

public class Arbol
{
    public String id;
    public String tipo;

    public Arbol(String id, String tipo)
    {
        this.id = id;
        this.tipo = tipo;
    }

    public String getId()
    {
        return id;
    }

    public String getTipo()
    {
        return tipo;
    }
}
