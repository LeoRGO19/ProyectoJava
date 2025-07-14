package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón para mostrar el panel de tabla de posiciones o resultados.
 * Extiende {@link BotonBase} y define su configuración visual y comportamiento al presionarlo.
 */
public class BotonTabla extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonTabla(JFrame frame) {
        super(frame);
    }

    /**
     * Configura la apariencia visual del botón:
     * - Texto descriptivo.
     * - Colores de fondo y texto.
     * - Fuente, borde, cursor.
     * - Posición y tamaño.
     * Este método se ejecuta automáticamente al crear el botón.
     */
    @Override
    public void configurar() {
        setText("mostrar Tabla");
        setBackground(new Color(40, 40, 40));
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 18));
        setFocusPainted(false);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBounds(700, 410, 170, 150);
    }

    /**
     * Acción que se ejecuta al presionar el botón:
     * - Guarda el panel actual (PanelObservador) en el historial de navegación.
     * - Cambia el contenido del JFrame al panel de tabla.
     * - Imprime un mensaje de seguimiento en consola.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelObservador(frame));
        cambiarPanel(new PanelTabla(frame));
        System.out.println("¡Botón Tabla presionado!");
    }
}