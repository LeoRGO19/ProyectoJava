package org.Interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Clase base para paneles en la aplicación.
 * Implementa la interfaz {@link InterfazPaneles} y provee un JPanel con soporte
 * para establecer una imagen de fondo personalizada.
 */
public class PanelBase implements InterfazPaneles {

    /** Panel principal que contiene los componentes gráficos */
    protected JPanel panel;

    /** JFrame padre donde se agregará este panel */
    protected JFrame frame;

    /** Imagen de fondo que se dibuja en el panel */
    private Image imagenFondo;

    /**
     * Constructor que inicializa el panel base con el JFrame dado.
     * Crea un JPanel con layout nulo y sobreescribe paintComponent para pintar la imagen de fondo.
     *
     * @param frame el JFrame contenedor donde se mostrará este panel.
     */
    public PanelBase(JFrame frame) {
        this.frame = frame;

        // Creamos un JPanel con layout nulo para posicionar componentes manualmente,
        // y sobrescribimos paintComponent para dibujar la imagen de fondo.
        this.panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
    }

    /**
     * Establece la imagen de fondo que se dibujará en el panel.
     * La imagen se carga desde el recurso indicado por la ruta.
     *
     * @param ruta la ruta del recurso de la imagen de fondo (relativa al classpath).
     */
    public void setImagenFondo(String ruta) {
        ImageIcon icon = new ImageIcon(getClass().getResource(ruta));
        imagenFondo = icon.getImage();
        panel.repaint(); // Forzar repintado para que se vea la imagen nueva
    }

    /**
     * Método para configurar propiedades del panel.
     * Está vacío aquí para que las subclases lo sobreescriban.
     */
    @Override
    public void configurar() {
        // Implementar en subclases
    }

    /**
     * Método para agregar componentes gráficos al panel.
     * Está vacío aquí para que las subclases lo sobreescriban.
     */
    @Override
    public void agregarComponentes() {
        // Implementar en subclases
    }

    /**
     * Obtiene el objeto JPanel asociado a este panel base.
     *
     * @return el JPanel con todos los componentes y fondo.
     */
    @Override
    public JPanel obtenerPanel() {
        return panel;
    }

    /**
     * Método para obtener un tipo numérico del panel.
     * Actualmente retorna 0, puede ser sobreescrito para identificar paneles específicos.
     *
     * @return un entero representando el tipo del panel (por defecto 0).
     */
    public int getTipo() {
        return 0;
    }
}