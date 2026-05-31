package modelo;

import vista.JFCaperucita;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Juego
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            try
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            catch (Exception e)
            {
                // Usar look and feel por defecto si falla
            }
            JFCaperucita ventana = new JFCaperucita();
            ventana.setVisible(true);
        });
    }
}
