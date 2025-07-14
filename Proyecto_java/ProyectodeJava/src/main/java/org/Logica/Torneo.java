package org.Logica;

/**
 * Interfaz que define el comportamiento común para los distintos tipos de torneos.
 *
 * <p>Da los métodos necesarios para configurar, gestionar participantes,
 * iniciar el torneo y generar estructuras como calendario, tabla y enfrentamientos.</p>
 *
 * <p>Implementaciones típicas incluyen torneos de eliminación simple o liga.</p>
 *
 * @author LeoRGO19
 *
 */
public interface Torneo {

    /**
     * Configura los datos iniciales del torneo.
     *
     * @param nombre Nombre del torneo.
     * @param disciplina Disciplina del torneo (por ejemplo: FUTBOL, CSGO).
     * @param maxParticipantes Número máximo de participantes permitidos.
     */
    void configurar(String nombre, String disciplina, int maxParticipantes);

    /**
     * Agrega un participante al torneo.
     *
     * @param p Participante a agregar.
     * @throws TorneoException Si el torneo ya ha comenzado o si el participante no puede ser agregado.
     */
    void agregarParticipante(Participante p) throws TorneoException;

    /**
     * Elimina un participante del torneo.
     *
     * @param p Participante a eliminar.
     * @throws TorneoException Si el torneo ya ha comenzado o si no se puede eliminar el participante.
     */
    void eliminarParticipante(Participante p) throws TorneoException;

    /**
     * Inicia el torneo, bloqueando la modificación de participantes.
     *
     * @throws TorneoException Si no hay suficientes participantes para iniciar el torneo.
     */
    void iniciarTorneo() throws TorneoException;

    /**
     * Genera el bracket del torneo. Normalmente usado en formatos de eliminación.
     *
     * @throws TorneoException Si no se puede generar el bracket.
     */
    void generarBracket() throws TorneoException;

    /**
     * Genera el calendario de enfrentamientos del torneo.
     */
    void generarCalendario();

    /**
     * Genera los enfrentamientos entre participantes según el formato del torneo.
     *
     * @throws TorneoException Si ocurre un error al generar los enfrentamientos.
     */
    void generarEnfrentamientos() throws TorneoException;

    /**
     * Genera la tabla de posiciones o resultados (útil en torneos de tipo liga).
     *
     * @throws TorneoException Si ocurre un error al generar la tabla.
     */
    void generarTabla() throws TorneoException;

    /**
     * Muestra el estado actual del torneo, incluyendo participantes y enfrentamientos.
     */
    void verEstado();
}