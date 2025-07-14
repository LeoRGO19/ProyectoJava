package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para seleccionar la disciplina de Fútbol.
 * Extiende {@link BotonBase} y define su configuración visual
 * y comportamiento al ser presionado.
 */
public class BotonFutbol extends BotonBase {

    /** Nombre de la disciplina (actualmente no utilizado en la lógica interna). */
    String futbol;

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará el botón.
     */
    public BotonFutbol(JFrame frame) {
        super(frame);
    }

    /**
     * Configura las propiedades visuales del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen asociada al botón.
     * - Cursor personalizado.
     * Este método es invocado automáticamente al construir el botón.
     */
    @Override
    public void configurar() {
        setBounds(650, 150, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/pelota_futbol.jpg");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Acción que se ejecuta al presionar el botón:
     * - Guarda el panel actual en el historial de navegación.
     * - Cambia al panel de selección de tipo de torneo.
     * - Asigna la palabra clave "FUTBOL" en {@link Navegador#palabra}.
     * - Imprime un mensaje en la consola para seguimiento.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelDeportes(frame));
        cambiarPanel(new PanelTipoTorneo(frame));
        Navegador.palabra = "FUTBOL";

        System.out.println("¡Botón Futbol presionado!");
    }
}