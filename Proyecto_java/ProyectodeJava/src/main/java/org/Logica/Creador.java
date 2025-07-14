package org.Logica;

/**
 * Clase abstracta que define el comportamiento base para los creadores de torneos.
 * Proporciona validaciones comunes para los parámetros de creación de torneos.
 *
 * Subclases concretas deberán implementar el método {@link #crearTorneo}.
 */
public abstract class Creador {

    /**
     * Valida los parámetros necesarios para crear un torneo.
     *
     * @param nombre El nombre del torneo.
     * @param disciplina La disciplina del torneo (debe coincidir con una constante del enum {@link Disciplina}).
     * @param maxParticipantes El número máximo de participantes (mínimo 2).
     * @throws TorneoException Si algún parámetro es inválido.
     */
    protected void validarParametros(String nombre, String disciplina, int maxParticipantes) throws TorneoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new TorneoException("El nombre del torneo no puede ser nulo o vacío.");
        }
        if (maxParticipantes < 2) {
            throw new TorneoException("El número máximo de participantes debe ser al menos 2.");
        }
        try {
            Disciplina.valueOf(disciplina);
        } catch (IllegalArgumentException e) {
            throw new TorneoException("Disciplina no válida: " + disciplina);
        }
    }

    /**
     * Crea un nuevo torneo con los parámetros especificados.
     * Este método debe ser implementado por las subclases que definan el tipo concreto de torneo.
     *
     * @param nombre El nombre del torneo.
     * @param disciplina La disciplina asociada al torneo.
     * @param maxParticipantes El número máximo de participantes.
     * @return Una instancia de {@link Torneo} específica del tipo de torneo creado.
     * @throws TorneoException Si hay errores durante la creación.
     */
    public abstract Torneo crearTorneo(String nombre, String disciplina, int maxParticipantes) throws TorneoException;

}