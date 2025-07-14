package org.Logica;

/**
 * Interfaz para observadores que desean recibir notificaciones de eventos en un torneo.
 * <p>
 * Las clases que implementen esta interfaz pueden ser registradas para recibir
 * actualizaciones cuando ocurra un {@link EventoTorneo}.
 * </p>
 */
public interface ObservadorTorneo {

    /**
     * Método que se llama para notificar al observador sobre un nuevo evento del torneo.
     *
     * @param evento El evento del torneo que se ha producido y debe ser procesado por el observador.
     */
    void actualizar(EventoTorneo evento);
}
