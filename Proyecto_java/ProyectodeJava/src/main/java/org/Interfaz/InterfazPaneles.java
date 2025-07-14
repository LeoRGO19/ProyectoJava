package org.Interfaz;

import javax.swing.*;

/**
 * Interfaz que deben implementar todos los paneles personalizados
 * en la aplicación, definiendo métodos para configurar el panel,
 * agregar sus componentes, y obtener el objeto JPanel correspondiente.
 */
public interface InterfazPaneles {

    /**
     * Método para configurar las propiedades iniciales del panel,
     * como tamaño, layout, colores, etc.
     */
    void configurar();

    /**
     * Método para agregar los componentes gráficos (botones, etiquetas, etc.)
     * al panel.
     */
    void agregarComponentes();

    /**
     * Método para obtener el objeto {@link JPanel} asociado a este panel.
     *
     * @return el JPanel que representa la interfaz gráfica de este panel.
     */
    JPanel obtenerPanel();
}