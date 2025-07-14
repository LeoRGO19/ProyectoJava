package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para la disciplina del videojuego Valorant.
 * Extiende {@link BotonBase} y define su apariencia y comportamiento al ser presionado.
 */
public class BotonValorant extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonValorant(JFrame frame) {
        super(frame);
    }

    /**
     * Configura la apariencia visual del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen representativa del juego Valorant.
     * - Cursor tipo mano para interacción.
     * Este método se ejecuta automáticamente al crear la instancia.
     */
    @Override
    public void configurar() {
        setBounds(650, 150, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/valorant_icono.jpg");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Añade el panel actual (PanelVideojuegos) al historial para permitir navegación hacia atrás.
     * - Cambia el contenido del JFrame al panel de selección de tipo de torneo.
     * - Establece la palabra clave "VALORANT" en {@link Navegador#palabra}.
     * - Imprime un mensaje de seguimiento en consola.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelVideojuegos(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "VALORANT";
        System.out.println("¡Botón Valorant presionado!");
    }
}