package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para la disciplina de Tiro con Arco.
 * Extiende {@link BotonBase} y define la apariencia y el comportamiento al ser presionado.
 */
public class BotonTiroConArco extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonTiroConArco(JFrame frame) {
        super(frame);
    }

    /**
     * Configura la apariencia visual del botón:
     * - Posición y tamaño.
     * - Color de fondo (gris claro) y texto.
     * - Imagen representativa del tiro con arco.
     * - Cursor tipo mano.
     * Este método se invoca automáticamente al crear la instancia.
     */
    @Override
    public void configurar() {
        setBounds(1100, 400, 200, 200);
        setBackground(Color.LIGHT_GRAY);
        setForeground(Color.BLACK);
        setImagen("/tiroconarco.png");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Guarda el panel actual (PanelDeportes) en el historial para navegación.
     * - Cambia el contenido del JFrame al panel de selección de tipo de torneo.
     * - Establece la palabra clave "TIROCONARCO" en {@link Navegador#palabra}.
     * - Imprime un mensaje de seguimiento en consola.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelDeportes(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "TIROCONARCO";
        System.out.println("¡Botón TiroConArco presionado!");
    }
}