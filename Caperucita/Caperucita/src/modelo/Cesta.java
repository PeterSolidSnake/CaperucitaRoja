package modelo;

public class Cesta
{
    private boolean bebida;
    private boolean comida;

    public Cesta(boolean bebida, boolean comida)
    {
        this.bebida = bebida;
        this.comida = comida;
    }

    public boolean estaLlena()
    {
        return bebida && comida;
    }

    public boolean isBebida()
    {
        return bebida;
    }

    public void setBebida(boolean bebida)
    {
        this.bebida = bebida;
    }

    public boolean isComida()
    {
        return comida;
    }

    public void setComida(boolean comida)
    {
        this.comida = comida;
    }
}
