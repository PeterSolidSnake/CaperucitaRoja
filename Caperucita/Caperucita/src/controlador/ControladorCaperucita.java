package controlador;

import modelo.*;
import java.util.ArrayList;

public class ControladorCaperucita
{
    private Caperucita caperucita;
    private Abuela abuela;
    private Lobo lobo;
    private Lenador lenador;
    private Bosque bosque;
    private ArrayList<String> bitacora;

    private boolean loboHabloConCaperucita;
    private boolean loboLlegoACasaAbuela;
    private boolean abuelaTragada;
    private boolean loboDisfrazado;
    private boolean caperucitaLlegoACasa;
    private boolean panzaAbierta;
    private boolean cestaLlena;

    public ControladorCaperucita()
    {
        bitacora = new ArrayList<>();
        iniciarHistoria();
    }

    private void iniciarHistoria()
    {
        Arbol arbol = new Arbol("A01", "Roble");
        bosque = new Bosque(100.0, 200.0, arbol);

        caperucita = new Caperucita("P01", "Caperucita Roja", "Protagonista", false);
        abuela     = new Abuela("P02", "Abuela", "Secundario", 75, true, false);
        lobo       = new Lobo("P03", "Lobo Feroz", "Villano", true);
        lenador    = new Lenador("P04", "Lenador", "Secundario");

        loboHabloConCaperucita = false;
        loboLlegoACasaAbuela   = false;
        abuelaTragada          = false;
        loboDisfrazado         = false;
        caperucitaLlegoACasa   = false;
        panzaAbierta           = false;
        cestaLlena             = false;

        registrar("La historia comienza. Caperucita debe visitar a su abuela.");
    }

    // --- Lobo ---

    public String accionLoboHablar()
    {
        if (loboHabloConCaperucita)
            return "El lobo ya hablo con Caperucita en el bosque.";
        if (!cestaLlena)
            return "Caperucita todavia no ha llenado su cesta para salir.";
        String msg = lobo.hablar() + " El lobo se acerca a Caperucita en el camino y le pregunta adonde va.";
        loboHabloConCaperucita = true;
        registrar(msg);
        return msg;
    }

    public String accionLoboDisfrazarse()
    {
        if (!abuelaTragada)
            return "El lobo no puede disfrazarse, todavia no ha tragado a la abuela.";
        if (loboDisfrazado)
            return "El lobo ya esta disfrazado de abuela.";
        String msg = lobo.disfrazarse() + " El lobo se mete en la cama y espera a Caperucita.";
        loboDisfrazado = true;
        registrar(msg);
        return msg;
    }

    public String accionLoboCorrer()
    {
        if (!loboHabloConCaperucita)
            return "El lobo todavia no ha hablado con Caperucita.";
        if (loboLlegoACasaAbuela)
            return "El lobo ya llego a casa de la abuela.";
        String msg = lobo.correr() + " El lobo corre por el camino mas corto y llega primero a casa de la abuela.";
        loboLlegoACasaAbuela = true;
        registrar(msg);
        return msg;
    }

    public String accionLoboTragar()
    {
        if (!loboLlegoACasaAbuela)
            return "El lobo todavia no ha llegado a casa de la abuela.";
        if (abuelaTragada)
            return "La abuela ya fue tragada por el lobo.";
        if (!lobo.isVivo())
            return "El lobo esta muerto, no puede hacer nada.";
        String msgGrito = abuela.gritar();
        String msgLobo  = lobo.tragar() + " El lobo traga a la abuela de un bocado.";
        abuela.setSalud(false);
        abuelaTragada = true;
        registrar(msgGrito);
        registrar(msgLobo);
        return msgGrito + "\n" + msgLobo;
    }

    // --- Caperucita ---

    public String accionLlenarCesta()
    {
        if (cestaLlena)
            return "La cesta ya esta llena.";
        Cesta cesta = caperucita.getCesta();
        cesta.setBebida(true);
        cesta.setComida(true);
        cestaLlena = true;
        String msg = "Caperucita llena su cesta con comida y bebida para su abuela. Ahora puede salir.";
        registrar(msg);
        return msg;
    }

    public String accionCaperucitaVisitar()
    {
        if (!loboDisfrazado)
            return "El lobo todavia no esta disfrazado en la cama.";
        if (caperucitaLlegoACasa)
            return "Caperucita ya esta en casa de su abuela.";
        String msg = "Caperucita llega a casa de su abuela y nota que algo es raro.";
        caperucitaLlegoACasa = true;
        registrar(msg);
        return msg;
    }

    // --- Abuela ---

    public String accionAbuelaGritar()
    {
        if (!loboLlegoACasaAbuela)
            return "La abuela todavia esta bien, el lobo no ha llegado.";
        if (abuelaTragada)
            return "La abuela esta dentro del lobo, no puede gritar afuera.";
        String msg = abuela.gritar();
        registrar(msg);
        return msg;
    }

    // --- Lenador ---

    public String accionLenadorRescatar()
    {
        if (!caperucitaLlegoACasa)
            return "Todavia no hay nadie en peligro en casa de la abuela.";
        if (!lobo.isVivo())
            return "El lenador ya termino con el lobo.";
        String msg = lenador.rescatar() + " El lenador escucha los gritos y entra a la casa con su hacha.";
        registrar(msg);
        return msg;
    }

    public String accionAbrirPanza()
    {
        if (!caperucitaLlegoACasa)
            return "El lenador todavia no ha llegado a la casa.";
        if (panzaAbierta)
            return "La panza ya fue abierta.";
        if (!lobo.isVivo())
            return "El lobo ya esta muerto.";
        boolean resultado = lenador.abrirPanza(lobo);
        String msg;
        if (resultado)
        {
            msg = "El lenador abre la panza del lobo de un hachazo. La abuela y Caperucita salen sanas y salvas.";
            abuela.setSalud(true);
            abuela.setRescatada(true);
            caperucita.setRescatada(true);
            panzaAbierta = true;
        }
        else
        {
            msg = "No se puede abrir la panza.";
        }
        registrar(msg);
        return msg;
    }

    public String accionRellenarPanza()
    {
        if (!panzaAbierta)
            return "Primero hay que abrir la panza del lobo.";
        if (!lobo.isVivo())
            return "El lobo ya esta muerto.";
        boolean resultado = lenador.rellenarPanza(lobo);
        String msg;
        if (resultado)
            msg = "El lenador rellena la panza del lobo con piedras pesadas y la cose. El lobo intenta escapar pero cae muerto. Fin del cuento.";
        else
            msg = "No se puede rellenar la panza.";
        registrar(msg);
        return msg;
    }

    // --- Getters de estado ---

    public String getEstadoLobo()
    {
        if (!lobo.isVivo())          return "Muerto";
        if (loboDisfrazado)          return "Disfrazado de abuela";
        if (loboLlegoACasaAbuela)    return "En casa de la abuela";
        if (loboHabloConCaperucita)  return "Corriendo al bosque";
        return "Acechando en el bosque";
    }

    public String getEstadoAbuela()
    {
        if (abuela.isRescatada())    return "Rescatada";
        if (abuelaTragada)           return "Tragada por el lobo";
        if (loboLlegoACasaAbuela)    return "En peligro";
        return "En su casa";
    }

    public String getEstadoCaperucita()
    {
        if (caperucita.isRescatada())    return "Rescatada";
        if (caperucitaLlegoACasa)        return "En casa de la abuela";
        if (loboHabloConCaperucita)      return "Caminando por el bosque";
        if (cestaLlena)                  return "Saliendo de casa";
        return "En casa, preparando la cesta";
    }

    public String getEstadoCesta()
    {
        return caperucita.getCesta().estaLlena() ? "Llena" : "Vacia";
    }

    public ArrayList<Personaje> getPersonajes()
    {
        ArrayList<Personaje> lista = new ArrayList<>();
        lista.add(caperucita);
        lista.add(abuela);
        lista.add(lobo);
        lista.add(lenador);
        return lista;
    }

    public ArrayList<String> getBitacora()
    {
        return bitacora;
    }

    public Bosque getBosque()
    {
        return bosque;
    }

    private void registrar(String mensaje)
    {
        bitacora.add(mensaje);
    }

    public void reiniciarHistoria()
    {
        bitacora.clear();
        iniciarHistoria();
    }
}
