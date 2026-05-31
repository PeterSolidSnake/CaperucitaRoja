package vista;

import controlador.ControladorCaperucita;
import modelo.Personaje;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class JFCaperucita extends JFrame
{
    private ControladorCaperucita controlador;

    private JLabel estadoLoboLabel;
    private JLabel estadoAbuelaLabel;
    private JLabel estadoCaperucitaLabel;
    private JLabel estadoCestaLabel;

    private JTextArea areaBitacora;

    private JList<String> listaPersonajes;
    private DefaultListModel<String> modeloLista;

    public JFCaperucita()
    {
        controlador = new ControladorCaperucita();
        configurarVentana();
        construirInterfaz();
        actualizarEstado();
    }

    private void configurarVentana()
    {
        setTitle("Caperucita Roja - Historia Interactiva");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void construirInterfaz()
    {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelPrincipal.setBackground(new Color(245, 235, 220));

        JLabel titulo = new JLabel("Caperucita Roja - Historia Interactiva", SwingConstants.CENTER);
        titulo.setFont(new Font("Serif", Font.BOLD, 22));
        titulo.setForeground(new Color(120, 30, 30));
        titulo.setBorder(new EmptyBorder(0, 0, 5, 0));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new GridLayout(1, 3, 10, 0));
        panelCentro.setOpaque(false);
        panelCentro.add(crearPanelAcciones());
        panelCentro.add(crearPanelEstado());
        panelCentro.add(crearPanelPersonajes());
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        panelPrincipal.add(crearPanelBitacora(), BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
    }

    private JPanel crearPanelAcciones()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 248, 240));
        panel.setBorder(crearBorde("Acciones"));

        // Lobo
        panel.add(crearSubtitulo("Lobo"));
        panel.add(crearBoton("Hablar",     new Color(180, 60, 60),  e -> mostrarResultado(controlador.accionLoboHablar())));
        panel.add(crearBoton("Correr",     new Color(180, 60, 60),  e -> mostrarResultado(controlador.accionLoboCorrer())));
        panel.add(crearBoton("Tragar",     new Color(180, 60, 60),  e -> mostrarResultado(controlador.accionLoboTragar())));
        panel.add(crearBoton("Disfrazarse",new Color(180, 60, 60),  e -> mostrarResultado(controlador.accionLoboDisfrazarse())));

        panel.add(Box.createVerticalStrut(8));

        // Caperucita
        panel.add(crearSubtitulo("Caperucita"));
        panel.add(crearBoton("Llenar cesta",   new Color(200, 100, 30), e -> mostrarResultado(controlador.accionLlenarCesta())));
        panel.add(crearBoton("Visitar abuela", new Color(200, 100, 30), e -> mostrarResultado(controlador.accionCaperucitaVisitar())));

        panel.add(Box.createVerticalStrut(8));

        // Abuela
        panel.add(crearSubtitulo("Abuela"));
        panel.add(crearBoton("Gritar", new Color(100, 100, 180), e -> mostrarResultado(controlador.accionAbuelaGritar())));

        panel.add(Box.createVerticalStrut(8));

        // Lenador
        panel.add(crearSubtitulo("Lenador"));
        panel.add(crearBoton("Rescatar",       new Color(50, 140, 80), e -> mostrarResultado(controlador.accionLenadorRescatar())));
        panel.add(crearBoton("Abrir panza",    new Color(50, 140, 80), e -> mostrarResultado(controlador.accionAbrirPanza())));
        panel.add(crearBoton("Rellenar panza", new Color(50, 140, 80), e -> mostrarResultado(controlador.accionRellenarPanza())));

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel crearPanelEstado()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 248, 240));
        panel.setBorder(crearBorde("Estado Actual"));

        panel.add(Box.createVerticalStrut(10));
        panel.add(crearSubtitulo("Estado de los personajes"));
        panel.add(Box.createVerticalStrut(8));

        estadoLoboLabel       = crearEtiquetaEstado("Lobo: ");
        estadoAbuelaLabel     = crearEtiquetaEstado("Abuela: ");
        estadoCaperucitaLabel = crearEtiquetaEstado("Caperucita: ");
        estadoCestaLabel      = crearEtiquetaEstado("Cesta: ");

        panel.add(estadoLoboLabel);
        panel.add(Box.createVerticalStrut(6));
        panel.add(estadoAbuelaLabel);
        panel.add(Box.createVerticalStrut(6));
        panel.add(estadoCaperucitaLabel);
        panel.add(Box.createVerticalStrut(6));
        panel.add(estadoCestaLabel);

        panel.add(Box.createVerticalStrut(20));
        panel.add(crearSubtitulo("Informacion del Bosque"));
        panel.add(Box.createVerticalStrut(8));

        JLabel bosqueLabel = new JLabel("Alto: 100.0  |  Ancho: 200.0");
        bosqueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bosqueLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(bosqueLabel);

        JLabel arbolLabel = new JLabel("Arbol: Roble");
        arbolLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        arbolLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(arbolLabel);

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel crearPanelPersonajes()
    {
        JPanel panel = new JPanel(new BorderLayout(0, 8));
        panel.setBackground(new Color(255, 248, 240));
        panel.setBorder(crearBorde("Personajes"));

        modeloLista    = new DefaultListModel<>();
        listaPersonajes = new JList<>(modeloLista);
        listaPersonajes.setFont(new Font("SansSerif", Font.PLAIN, 13));
        listaPersonajes.setBackground(new Color(255, 253, 245));
        listaPersonajes.setSelectionBackground(new Color(220, 180, 130));
        listaPersonajes.setFixedCellHeight(28);

        JScrollPane scroll = new JScrollPane(listaPersonajes);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 150)));
        panel.add(scroll, BorderLayout.CENTER);

        actualizarListaPersonajes();
        return panel;
    }

    private JPanel crearPanelBitacora()
    {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setOpaque(false);
        panel.setBorder(crearBorde("Bitacora de eventos"));
        panel.setPreferredSize(new Dimension(0, 180));

        areaBitacora = new JTextArea();
        areaBitacora.setEditable(false);
        areaBitacora.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaBitacora.setBackground(new Color(30, 20, 10));
        areaBitacora.setForeground(new Color(200, 230, 180));
        areaBitacora.setMargin(new Insets(5, 8, 5, 8));
        areaBitacora.setLineWrap(true);
        areaBitacora.setWrapStyleWord(true);

        JScrollPane scrollBitacora = new JScrollPane(areaBitacora);
        panel.add(scrollBitacora, BorderLayout.CENTER);

        JButton botonReiniciar = new JButton("Reiniciar Historia");
        botonReiniciar.setFont(new Font("SansSerif", Font.BOLD, 13));
        botonReiniciar.setBackground(new Color(120, 30, 30));
        botonReiniciar.setForeground(Color.BLACK);
        botonReiniciar.setFocusPainted(false);
        botonReiniciar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botonReiniciar.addActionListener(e ->
        {
            controlador.reiniciarHistoria();
            actualizarEstado();
        });

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBoton.setOpaque(false);
        panelBoton.add(botonReiniciar);
        panel.add(panelBoton, BorderLayout.EAST);

        return panel;
    }

    // --- Auxiliares ---

    private JButton crearBoton(String texto, Color color, java.awt.event.ActionListener accion)
    {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("SansSerif", Font.BOLD, 12));
        boton.setBackground(color);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(180, 30));
        boton.addActionListener(accion);
        return boton;
    }

    private JLabel crearSubtitulo(String texto)
    {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(new Color(80, 40, 10));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private JLabel crearEtiquetaEstado(String texto)
    {
        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(new Font("SansSerif", Font.PLAIN, 13));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private TitledBorder crearBorde(String titulo)
    {
        TitledBorder borde = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(180, 140, 90), 1),
            titulo
        );
        borde.setTitleFont(new Font("Serif", Font.BOLD, 13));
        borde.setTitleColor(new Color(100, 50, 10));
        return borde;
    }

    // --- Actualizacion ---

    private void actualizarEstado()
    {
        estadoLoboLabel.setText("Lobo: "       + controlador.getEstadoLobo());
        estadoAbuelaLabel.setText("Abuela: "   + controlador.getEstadoAbuela());
        estadoCaperucitaLabel.setText("Caperucita: " + controlador.getEstadoCaperucita());
        estadoCestaLabel.setText("Cesta: "     + controlador.getEstadoCesta());
        actualizarListaPersonajes();
        actualizarBitacora();
    }

    private void actualizarListaPersonajes()
    {
        modeloLista.clear();
        ArrayList<Personaje> personajes = controlador.getPersonajes();
        for (Personaje p : personajes)
        {
            modeloLista.addElement("  " + p.getNombre() + "  [" + p.getTipo() + "]");
        }
    }

    private void actualizarBitacora()
    {
        StringBuilder sb = new StringBuilder();
        for (String linea : controlador.getBitacora())
        {
            sb.append("> ").append(linea).append("\n");
        }
        areaBitacora.setText(sb.toString());
        areaBitacora.setCaretPosition(areaBitacora.getDocument().getLength());
    }

    private void mostrarResultado(String mensaje)
    {
        actualizarEstado();
        areaBitacora.setCaretPosition(areaBitacora.getDocument().getLength());
    }
}