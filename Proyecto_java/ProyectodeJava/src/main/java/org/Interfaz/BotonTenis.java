package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para la disciplina de Tenis.
 * Extiende {@link BotonBase} y define su apariencia y comportamiento al ser presionado.
 */
public class BotonTenis extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonTenis(JFrame frame) {
        super(frame);
    }

    /**
     * Configura la apariencia visual del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen asociada al botón.
     * - Cursor tipo mano.
     * Este método se invoca automáticamente al crear la instancia.
     */
    @Override
    public void configurar() {
        setBounds(650, 400, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/tenis.jpg");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Guarda el panel actual (PanelDeportes) en el historial para navegación.
     * - Cambia el contenido del JFrame al panel de selección de tipo de torneo.
     * - Establece la palabra clave "TENIS" en {@link Navegador#palabra}.
     * - Imprime un mensaje de seguimiento en consola.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelDeportes(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "TENIS";
        System.out.println("¡Botón Tenis presionado!");
    }
}