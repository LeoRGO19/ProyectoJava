package org.Logica;

/**
 * Representa un evento dentro de un torneo.
 * Un evento tiene un tipo, un torneo asociado y datos adicionales
 * que pueden representar participantes, enfrentamientos u otra información relevante.
 */

public class EventoTorneo {
    private TipoEvento tipo;
    private Torneo torneo;
    private Object datos; // Datos adicionales, como participante o enfrentamientos

    /**
     * Construye un evento de torneo con un tipo, el torneo asociado y datos adicionales.
     *
     * @param tipo  El tipo de evento que se está creando.
     * @param torneo El torneo al que pertenece este evento.
     * @param datos Datos adicionales relacionados con el evento (por ejemplo, participante o enfrentamientos).
     */

    public EventoTorneo(TipoEvento tipo, Torneo torneo, Object datos) {
        this.tipo = tipo;
        this.torneo = torneo;
        this.datos = datos;
    }

    /**
     * Obtiene el tipo de evento.
     *
     * @return El tipo de evento.
     */

    public TipoEvento getTipo() {
        return tipo;
    }

    /**
     * Obtiene el torneo asociado a este evento.
     *
     * @return El torneo al que pertenece el evento.
     */

    public Torneo getTorneo() {
        return torneo;
    }

    /**
     * Obtiene los datos adicionales del evento.
     *
     * @return Los datos adicionales asociados al evento (por ejemplo, participante o enfrentamientos).
     */

    public Object getDatos() {
        return datos;
    }
}