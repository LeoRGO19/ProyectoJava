package org.Logica;

/**
 * Interfaz que define el comportamiento de un sujeto observador en el patrón
 * Observer para torneos.
 * <p>
 * Permite registrar, eliminar y notificar observadores que implementan
 * {@link ObservadorTorneo} sobre eventos que ocurren en el torneo.
 * </p>
 */
public interface SujetoTorneo {

    /**
     * Registra un observador para recibir notificaciones de eventos del torneo.
     *
     * @param observador El observador que desea recibir actualizaciones.
     */
    void registrarObservador(ObservadorTorneo observador);

    /**
     * Elimina un observador para que deje de recibir notificaciones.
     *
     * @param observador El observador que se desea eliminar.
     */
    void eliminarObservador(ObservadorTorneo observador);

    /**
     * Notifica a todos los observadores registrados sobre un evento ocurrido en el torneo.
     *
     * @param tipo  El tipo de evento que ocurrió.
     * @param datos Datos adicionales relacionados con el evento (puede ser null).
     */
    void notificarObservadores(TipoEvento tipo, Object datos);
}
