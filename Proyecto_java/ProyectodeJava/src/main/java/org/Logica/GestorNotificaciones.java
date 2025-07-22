package org.Logica;

/**
 * La clase {@code GestorNotificaciones} se encarga de gestionar las notificaciones
 * relacionadas con los eventos del torneo. Actúa como intermediario para notificar
 * a los observadores registrados en el {@link SujetoTorneo}.
 * <p>
 * Actualmente, esta clase notifica cuando los resultados de un {@link Enfrentamiento}
 * han sido actualizados.
 * </p>
 *
 * @author Canito301
 */
public class GestorNotificaciones {

    /** Referencia al sujeto del torneo que gestiona los observadores. */
    private final SujetoTorneo torneo;

    /**
     * Crea una instancia de {@code GestorNotificaciones} con el torneo especificado.
     *
     * @param torneo el objeto {@link SujetoTorneo} que gestionará las notificaciones a los observadores.
     */
    public GestorNotificaciones(SujetoTorneo torneo) {
        this.torneo = torneo;
    }

    /**
     * Notifica a los observadores que los resultados de un enfrentamiento han sido actualizados.
     *
     * @param e el {@link Enfrentamiento} cuyos resultados han cambiado.
     */
    public void notificarResultadosActualizados(Enfrentamiento e) {
        torneo.notificarObservadores(TipoEvento.RESULTADOS_ACTUALIZADOS, e);
    }
}