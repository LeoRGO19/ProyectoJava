package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón para acceder a la sección de videojuegos dentro de la interfaz.
 * Extiende {@link BotonBase} y define su apariencia y comportamiento al ser presionado.
 */
public class BotonVideojuegos extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonVideojuegos(JFrame frame) {
        super(frame);
    }

    /**
     * Configura la apariencia visual del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen representativa de videojuegos.
     * - Cursor tipo mano para interacción.
     * Este método se ejecuta automáticamente al crear la instancia.
     */
    @Override
    public void configurar() {
        setBounds(595, 320, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/Videojuegos.jpg");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Añade el panel actual (PanelDisciplina) al historial para navegación hacia atrás.
     * - Cambia el contenido del JFrame al panel de videojuegos.
     * - Imprime un mensaje de seguimiento en consola.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelDisciplina(frame));
        cambiarPanel(new PanelVideojuegos(frame));
        System.out.println("¡Botón Videojuegos presionado!");
    }
}
