package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón para regresar al panel anterior en la navegación.
 * Extiende {@link BotonBase} y permite volver al último panel almacenado en el historial.
 */
public class BotonVolver extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se mostrará este botón.
     */
    public BotonVolver(JFrame frame) {
        super(frame);
    }

    /**
     * Configura la apariencia visual del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen de flecha que representa la acción de "volver".
     * - Cursor tipo mano para interacción.
     * Este método se invoca automáticamente al crear la instancia.
     */
    @Override
    public void configurar() {
        setBounds(200, 150, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/flecha.png");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Cambia el panel visible en el JFrame al panel recibido.
     *
     * @param nuevoPanel el panel que se debe mostrar.
     */
    @Override
    protected void cambiarPanel(PanelBase nuevoPanel) {
        if (frame == null || nuevoPanel == null) return;

        frame.setContentPane(nuevoPanel.obtenerPanel());
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Acción que se ejecuta al presionar el botón:
     * - Si el historial de navegación no está vacío, obtiene el último panel almacenado.
     * - Cambia el contenido del JFrame al panel anterior.
     * - Imprime un mensaje de seguimiento en consola.
     */
    @Override
    public void alPresionar() {
        if (!Navegador.historial.isEmpty()) {
            PanelBase anterior = Navegador.historial.pop();
            cambiarPanel(anterior);
            System.out.println("Boton Volver presionado");
        }
    }
}