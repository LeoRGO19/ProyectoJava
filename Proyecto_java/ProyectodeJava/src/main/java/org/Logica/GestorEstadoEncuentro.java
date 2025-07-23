package org.Logica;

/**
 * La clase {@code GestorEstadoEncuentro} gestiona el estado de un encuentro en el torneo.
 * Define tres estados posibles: pendiente, en curso y terminado.
 * Proporciona métodos para cambiar y consultar el estado actual.
 * <p>
 * Los estados están representados como constantes enteras:
 * <ul>
 *     <li>{@link #PENDIENTE}</li>
 *     <li>{@link #EN_CURSO}</li>
 *     <li>{@link #TERMINADO}</li>
 * </ul>
 *
 * @author Canito301
 */
public class GestorEstadoEncuentro {

    /** Estado que representa un encuentro que aún no ha iniciado. */
    public static final int PENDIENTE = 0;

    /** Estado que representa un encuentro que está en curso. */
    public static final int EN_CURSO = 1;

    /** Estado que representa un encuentro que ha finalizado. */
    public static final int TERMINADO = 2;

    /** Estado actual del encuentro. Por defecto es {@link #PENDIENTE}. */
    private int estado = PENDIENTE;

    /**
     * Cambia el estado del encuentro a {@link #EN_CURSO}.
     */
    public void iniciar() {
        estado = EN_CURSO;
    }

    /**
     * Cambia el estado del encuentro a {@link #TERMINADO}.
     */
    public void terminar() {
        estado = TERMINADO;
    }

    /**
     * Obtiene el estado actual del encuentro.
     *
     * @return el valor entero que representa el estado actual.
     * Puede ser {@link #PENDIENTE}, {@link #EN_CURSO} o {@link #TERMINADO}.
     */
    public int obtenerEstado() {
        return estado;
    }

    /**
     * Verifica si el estado actual es {@link #PENDIENTE}.
     *
     * @return {@code true} si el estado es pendiente, {@code false} en caso contrario.
     */
    public boolean estaPendiente() {
        return estado == PENDIENTE;
    }

    /**
     * Verifica si el estado actual es {@link #TERMINADO}.
     *
     * @return {@code true} si el estado es terminado, {@code false} en caso contrario.
     */
    public boolean estaTerminado() {
        return estado == TERMINADO;
    }
}