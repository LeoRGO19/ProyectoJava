package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para la disciplina de League of Legends (LoL).
 * Extiende {@link BotonBase} y define su apariencia y comportamiento al ser presionado.
 */
public class BotonLoL extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonLoL(JFrame frame) {
        super(frame);
    }

    /**
     * Configura la apariencia visual del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen asociada al botón.
     * - Cursor tipo mano al pasar el mouse.
     * Este método se invoca automáticamente al crear la instancia.
     */
    @Override
    public void configurar() {
        setBounds(1100, 150, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/lol_icono.png");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción al presionar el botón:
     * - Guarda el panel actual (PanelVideojuegos) en el historial para permitir navegación hacia atrás.
     * - Cambia el contenido del JFrame al panel de selección de tipo de torneo.
     * - Establece la palabra clave "LOL" en {@link Navegador#palabra}.
     * - Imprime un mensaje de seguimiento en consola.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelVideojuegos(frame)); // Variable global para poder volver si hay más de un panel
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "LOL";
        System.out.println("¡Botón LoL presionado!");
    }
}
