package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para la disciplina de Ping Pong (Tenis de Mesa).
 * Extiende {@link BotonBase} y define la configuración visual y el comportamiento al presionarlo.
 */
public class BotonPingPong extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonPingPong(JFrame frame) {
        super(frame);
    }

    /**
     * Configura la apariencia del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen asociada.
     * - Cursor tipo mano.
     * Este método se invoca automáticamente al crear el botón.
     */
    @Override
    public void configurar() {
        setBounds(200, 400, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/pingpong_icono.jpg");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que se ejecuta al presionar el botón:
     * - Agrega el panel actual (PanelDeportes) al historial para navegación.
     * - Cambia el contenido del JFrame al panel de selección de tipo de torneo.
     * - Establece la palabra clave "TENISDEMESA" en {@link Navegador#palabra}.
     * - Imprime un mensaje en consola para seguimiento.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelDeportes(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "TENISDEMESA";
        System.out.println("¡Botón PingPong presionado!");
    }
}
