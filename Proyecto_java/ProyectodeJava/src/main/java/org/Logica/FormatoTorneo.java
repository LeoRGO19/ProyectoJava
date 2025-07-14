package org.Logica;

/**
 * Enumeración que representa los distintos formatos disponibles para la organización de un torneo.
 *
 * <p>Los formatos posibles son:</p>
 * <ul>
 *     <li>{@code ELIMINACION_SIMPLE}: Formato en el que los participantes son eliminados tras perder un enfrentamiento.
 *     Solo un ganador avanza por ronda hasta que queda un único campeón.</li>
 *     <li>{@code LIGA}: Formato en el que todos los participantes compiten entre sí (todos contra todos) y
 *     se determina un ganador en base a puntajes acumulados.</li>
 * </ul>
 *
 * <p>Esta enumeración puede ser utilizada para instanciar torneos con reglas específicas de desarrollo.</p>
 *
 * @author Canito301, LeoRGO19
 */
public enum FormatoTorneo {
    /**
     * Torneo de eliminación simple: los participantes son eliminados al perder.
     */
    ELIMINACION_SIMPLE,

    /**
     * Torneo tipo liga: todos los participantes se enfrentan entre sí.
     */
    LIGA
}
