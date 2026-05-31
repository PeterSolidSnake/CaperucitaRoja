package modelo;

public class Abuela extends Personaje
{
    private int edad;
    private boolean salud;
    private boolean rescatada;

    public Abuela(String id, String nombre, String tipo, int edad, boolean salud, boolean rescatada)
    {
        super(id, nombre, tipo);
        this.edad = edad;
        this.salud = salud;
        this.rescatada = rescatada;
    }

    public String gritar()
    {
        return getNombre() + " grita: ¡Auxilio!";
    }

    public int getEdad()
    {
        return edad;
    }

    public boolean isSalud()
    {
        return salud;
    }

    public void setSalud(boolean salud)
    {
        this.salud = salud;
    }

    public boolean isRescatada()
    {
        return rescatada;
    }

    public void setRescatada(boolean rescatada)
    {
        this.rescatada = rescatada;
    }
}
