package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para la disciplina de Rocket League (videojuego).
 * Extiende {@link BotonBase} y define su apariencia y comportamiento al ser presionado.
 */
public class BotonRocket extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonRocket(JFrame frame) {
        super(frame);
    }

    /**
     * Configura las propiedades visuales del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen asociada al botón.
     * - Cursor tipo mano.
     * Este método se ejecuta automáticamente al crear la instancia.
     */
    @Override
    public void configurar() {
        setBounds(650, 400, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/rocket_icono.png");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Añade el panel actual (PanelVideojuegos) al historial para navegación.
     * - Cambia el contenido del JFrame al panel de selección de tipo de torneo.
     * - Establece la palabra clave "ROCKETLEAGUE" en {@link Navegador#palabra}.
     * - Muestra un mensaje de seguimiento en consola.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelVideojuegos(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "ROCKETLEAGUE";
        System.out.println("¡Botón Rocket League presionado!");
    }
}