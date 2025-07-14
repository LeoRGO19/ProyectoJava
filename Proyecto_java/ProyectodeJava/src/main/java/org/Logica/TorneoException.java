package org.Logica;

/**
 * Excepción personalizada para errores relacionados con la lógica de torneos.
 * <p>
 * Se utiliza para indicar condiciones inválidas o errores específicos dentro
 * del manejo y ejecución de torneos.
 * </p>
 */
public class TorneoException extends Exception {

    /**
     * Construye una nueva excepción con un mensaje detallado.
     *
     * @param message Mensaje que describe la causa de la excepción.
     */
    public TorneoException(String message) {
        super(message);
    }
}