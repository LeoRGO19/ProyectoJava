package org.Logica;

public class EventoTorneo {
    private TipoEvento tipo;
    private Torneo torneo;
    private Object datos; // Datos adicionales, como participante o enfrentamientos

    public EventoTorneo(TipoEvento tipo, Torneo torneo, Object datos) {
        this.tipo = tipo;
        this.torneo = torneo;
        this.datos = datos;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public Object getDatos() {
        return datos;
    }
}