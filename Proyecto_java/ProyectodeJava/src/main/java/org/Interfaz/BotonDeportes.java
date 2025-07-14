package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Botón específico para acceder al panel de selección de deportes.
 * Extiende {@link BotonBase} y configura su apariencia e interacción.
 */
public class BotonDeportes extends BotonBase {

    /**
     * Constructor que inicializa el botón con el JFrame principal.
     *
     * @param frame el JFrame donde se insertará este botón y donde se realizarán los cambios de panel.
     */
    public BotonDeportes(JFrame frame) {
        super(frame);
    }

    /**
     * Configura las propiedades visuales del botón:
     * - Posición y tamaño.
     * - Colores de fondo y texto.
     * - Imagen como ícono del botón.
     * - Cursor en forma de mano al pasar el mouse.
     * Este método se llama automáticamente al crear la instancia.
     */
    @Override
    public void configurar() {
        setBounds(1115, 320, 200, 200);
        setBackground(Color.BLACK);
        setForeground(Color.BLACK);
        setImagen("/deportes.jpg");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Define la acción al presionar el botón:
     * - Agrega el panel actual (Disciplina) al historial de navegación.
     * - Cambia el contenido del JFrame al panel de deportes.
     * - Imprime un mensaje en consola como seguimiento.
     */
    @Override
    public void alPresionar() {
        Navegador.historial.push(new PanelDisciplina(frame));
        cambiarPanel(new PanelDeportes(frame));
        System.out.println("¡Botón Deportes presionado!");
    }
}