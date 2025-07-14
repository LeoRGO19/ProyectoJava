package org.Logica;

/**
 * Enumeración que representa los distintos tipos de eventos
 * que pueden ocurrir durante la vida de un torneo.
 */
public enum TipoEvento {
    /** Evento que indica que se ha agregado un participante al torneo. */
    PARTICIPANTE_AGREGADO,

    /** Evento que indica que se ha eliminado un participante del torneo. */
    PARTICIPANTE_ELIMINADO,

    /** Evento que indica que el torneo ha sido iniciado. */
    TORNEO_INICIADO,

    /** Evento que indica que se han generado los enfrentamientos del torneo. */
    ENFRENTAMIENTOS_GENERADOS,

    /** Evento que indica que se han actualizado los resultados del torneo. */
    RESULTADOS_ACTUALIZADOS,

    /** Evento que indica que el torneo ha finalizado. */
    TORNEO_FINALIZADO;
}