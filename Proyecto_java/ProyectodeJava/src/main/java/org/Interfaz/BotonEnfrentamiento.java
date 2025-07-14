package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón que permite al usuario visualizar el panel de enfrentamientos de un torneo.
 * Extiende {@link BotonBase} y define su configuración visual y comportamiento al ser presionado.
 */
public class BotonEnfrentamiento extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame en el que se mostrará el botón.
     */
    public BotonEnfrentamiento(JFrame frame) {
        super(frame);
    }

    /**
     * Configura las propiedades visuales del botón:
     * - Texto descriptivo.
     * - Colores de fondo y texto.
     * - Fuente, borde y cursor.
     * - Posición y tamaño dentro del contenedor.
     * Este método es invocado automáticamente al crear el botón.
     */
    @Override
    public void configurar() {
        setText("ver Enfrentamiento");
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 15));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBounds(1000, 410, 170, 150);
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Guarda el panel actual en el historial de navegación.
     * - Cambia el contenido del {@code JFrame} al panel de enfrentamientos.
     * - Imprime un mensaje de seguimiento en la consola.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelObservador(frame));
        cambiarPanel(new PanelEnfrentamiento(frame));
        System.out.println("¡Botón Enfrentamiento presionado!");
    }
}