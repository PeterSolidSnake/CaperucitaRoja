package modelo;

public abstract class Personaje
{
    private String id;
    private String nombre;
    private String tipo;

    public Personaje(String id, String nombre, String tipo)
    {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getId()
    {
        return id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getTipo()
    {
        return tipo;
    }
}
