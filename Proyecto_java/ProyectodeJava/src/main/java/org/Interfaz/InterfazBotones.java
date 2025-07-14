package org.Interfaz;

import org.Logica.TorneoException;

/**
 * Interfaz que deben implementar todos los botones personalizados
 * en la aplicación, definiendo métodos para configurar la apariencia
 * y para definir la acción al ser presionados.
 */
public interface InterfazBotones {

    /**
     * Método para configurar la apariencia y propiedades iniciales del botón.
     * Se llama típicamente en el constructor de la clase que implementa esta interfaz.
     */
    void configurar();

    /**
     * Método que define la acción a ejecutar cuando el botón es presionado.
     * Puede lanzar una {@link TorneoException} si ocurre algún error relacionado con torneos.
     *
     * @throws TorneoException si ocurre un error en la lógica al presionar el botón.
     */
    void alPresionar() throws TorneoException;
}