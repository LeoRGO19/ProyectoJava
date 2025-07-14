package org.Logica;

/**
 * Clase que implementa el patrón creador para torneos de tipo Liga.
 * <p>
 * Se encarga de crear instancias de {@link TorneoLiga} validando previamente los parámetros.
 * </p>
 */
public class LigaCreador extends Creador {
    /** Constructor por defecto */
    public LigaCreador(){}

    /**
     * Crea un torneo de tipo Liga con el nombre, disciplina y máximo de participantes especificados.
     * Valida los parámetros antes de crear la instancia.
     *
     * @param nombre           El nombre del torneo.
     * @param disciplina       La disciplina del torneo.
     * @param maxParticipantes El máximo número de participantes permitidos en el torneo.
     * @return Una instancia de {@link TorneoLiga} creada con los parámetros proporcionados.
     * @throws TorneoException Si alguno de los parámetros no es válido según las reglas internas.
     */
    @Override
    public Torneo crearTorneo(String nombre, String disciplina, int maxParticipantes) throws TorneoException {
        validarParametros(nombre, disciplina, maxParticipantes);
        return new TorneoLiga(nombre, disciplina, maxParticipantes);
    }
}