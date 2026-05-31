package modelo;

public class Bosque
{
    private double alto;
    private double ancho;
    private Arbol arbol;

    public Bosque(double alto, double ancho, Arbol arbol)
    {
        this.alto = alto;
        this.ancho = ancho;
        this.arbol = arbol;
    }

    public double getAlto()
    {
        return alto;
    }

    public double getAncho()
    {
        return ancho;
    }

    public Arbol getArbol()
    {
        return arbol;
    }
}
