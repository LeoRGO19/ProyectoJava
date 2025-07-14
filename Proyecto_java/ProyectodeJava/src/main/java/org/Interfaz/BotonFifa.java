package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para seleccionar la disciplina de FIFA (videojuego).
 * Extiende {@link BotonBase} y define su apariencia y comportamiento al ser presionado.
 */
public class BotonFifa extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonFifa(JFrame frame) {
        super(frame);
    }

    /**
     * Configura las propiedades visuales del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen asociada al botón.
     * - Cursor tipo "mano" al pasar el mouse.
     * Este método se ejecuta automáticamente al construir la instancia.
     */
    @Override
    public void configurar() {
        setBounds(1100, 400, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/fifa_icono.png");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que ocurre al presionar el botón:
     * - Guarda el panel actual en el historial de navegación.
     * - Cambia al panel de selección de tipo de torneo.
     * - Establece la palabra clave "FIFA" en {@link Navegador#palabra}.
     * - Muestra un mensaje de seguimiento por consola.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelVideojuegos(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "FIFA";
        System.out.println("¡Botón Fifa presionado!");
    }
}