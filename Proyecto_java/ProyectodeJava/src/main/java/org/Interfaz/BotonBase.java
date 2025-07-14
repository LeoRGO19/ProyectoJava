package org.Interfaz;

import org.Logica.TorneoException;

import javax.swing.*;
import java.awt.*;

/**
 * Clase base abstracta para botones personalizados en la interfaz gráfica.
 * Extiende JButton y define métodos básicos para configuración,
 * asignación de imagen e interacción con el JFrame padre.
 * Implementa la interfaz InterfazBotones que define el contrato de comportamiento.
 */

public abstract class BotonBase extends JButton implements InterfazBotones {

    /** Frame principal en el que se mostrará el botón y se cambiarán paneles */
    protected JFrame frame;

    /**
     * Constructor que inicializa el botón y lo configura.
     * También registra el listener para la acción del botón que llama a {@link #alPresionar()}.
     *
     * @param frame el JFrame principal que contiene el botón
     */

    public BotonBase(JFrame frame) {
        super();
        this.frame = frame;
        configurar();
        addActionListener(e -> {
            try {
                alPresionar();
            } catch (TorneoException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    /**
     * Configura propiedades básicas del botón, como tamaño y estilo de foco.
     * Puede ser sobrescrito por las subclases para configuración personalizada.
     */
    public void configurar() {
        setSize(new Dimension(100, 50));
        setFocusPainted(false);
    }

    /**
     * Carga una imagen desde los recursos y la escala al tamaño 200x200 píxeles,
     * luego la asigna como icono del botón.
     *
     * @param ruta la ruta del recurso de imagen dentro del classpath (ejemplo: "/imagenes/icono.png")
     */
    public void setImagen(String ruta){
        int ancho = 200;
        int alto = 200;
        ImageIcon imagen = new ImageIcon(getClass().getResource(ruta));
        Image imagenEscalada = imagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        ImageIcon imagenFinal = new ImageIcon(imagenEscalada);
        setIcon(imagenFinal);
    }

    /**
     * Cambia el contenido del JFrame al panel especificado.
     * Si el JFrame o el panel son nulos, no hace nada.
     *
     * @param nuevoPanel el nuevo PanelBase que se mostrará en el JFrame
     */
    protected void cambiarPanel(PanelBase nuevoPanel) {
        if (frame == null || nuevoPanel == null) return;

        frame.setContentPane(nuevoPanel.obtenerPanel());
        frame.revalidate();
        frame.repaint();
    }
}
