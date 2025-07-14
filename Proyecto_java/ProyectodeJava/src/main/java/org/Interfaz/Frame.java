package org.Interfaz;

import javax.swing.*;

/**
 * Clase principal que representa la ventana principal del sistema.
 *
 * <p>Esta clase extiende {@link JFrame} y configura la interfaz gráfica inicial
 * con un tamaño predefinido y el panel principal como contenido.</p>
 *
 * <p>Sirve como punto de entrada de la aplicación.</p>
 *
 * @author Canito301
 * @version 1.0
 */
public class Frame extends JFrame {

    /**
     * Constructor que inicializa la ventana principal del sistema.
     *
     * <p>Configura el tamaño, el cierre por defecto, la localización centrada
     * en pantalla y establece el {@link PanelPrincipal} como contenido.</p>
     */
    public Frame() {
        setSize(1650, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        PanelPrincipal panel = new PanelPrincipal(this);
        setContentPane(panel.obtenerPanel());
        setVisible(true);
    }

    /**
     * Método principal que lanza la aplicación gráfica.
     *
     * <p>Usa {@link SwingUtilities#invokeLater} para asegurar que la interfaz
     * gráfica se construya en el hilo de eventos de Swing.</p>
     *
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Frame::new);
    }
}
