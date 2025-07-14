package org.Interfaz;

import org.Logica.FormatoTorneo;

import javax.swing.*;
import java.awt.*;

/**
 * Botón para seleccionar el formato de torneo "Eliminación Simple".
 * Extiende {@link BotonBase} y configura tanto la apariencia como el comportamiento al ser presionado.
 */
public class BotonEliminacionSimple extends BotonBase {

    /** Formato de torneo asociado al botón (no usado directamente aquí, pero será útil para validación futura). */
    FormatoTorneo t;

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará el botón.
     */
    public BotonEliminacionSimple(JFrame frame) {
        super(frame);
    }

    /**
     * Configura las propiedades visuales del botón:
     * - Tamaño y posición.
     * - Colores de fondo y texto.
     * - Fuente, borde, texto y cursor.
     * Este método se invoca automáticamente al crear la instancia.
     */
    @Override
    public void configurar() {
        setBounds(1115, 320, 200, 200);
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setText("ELIMINACION SIMPLE");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Guarda el panel actual en el historial de navegación.
     * - Cambia la vista al panel de confirmación.
     * - Establece el formato de torneo como ELIMINACION_SIMPLE en {@link Navegador}.
     * - Muestra mensajes de seguimiento en la consola.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelTipoTorneo(frame));
        cambiarPanel(new PanelConfirmacion(frame));
        Navegador.t = FormatoTorneo.ELIMINACION_SIMPLE;
        System.out.println(Navegador.t);
        System.out.println("¡Botón Eliminacion simple presionado!");
    }
}