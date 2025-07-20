package org.Logica;

public class GestorNotificaciones {
    private final SujetoTorneo torneo;

    public GestorNotificaciones(SujetoTorneo torneo) {
        this.torneo = torneo;
    }

    public void notificarResultadosActualizados(Enfrentamiento e) {
        torneo.notificarObservadores(TipoEvento.RESULTADOS_ACTUALIZADOS, e);
    }
}
