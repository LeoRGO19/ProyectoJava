package org.Logica;

import java.util.ArrayList;

/**
 * Representa un equipo como tipo de {@link Participante} dentro de un torneo.
 * Un equipo está compuesto por una lista de {@link IndividuoParticipante}s.
 *
 * <p>Hereda de {@link Participante}, lo que le permite ser tratado de forma
 * uniforme junto a individuos dentro del sistema del torneo.</p>
 *
 * <p>Se puede usar en torneos que permitan participación grupal (por ejemplo, LoL, Valorant, fútbol, etc.).</p>
 *
 * @author Canito301, LeoRGO19
 *
 */

public class Equipo extends Participante {

    /** Lista de miembros individuales que componen el equipo. */
    private ArrayList<IndividuoParticipante> listaEquipo;

    /**
     * Crea un equipo con un nombre y un contacto responsable.
     *
     * @param nombre   Nombre del equipo.
     * @param contacto Información de contacto del equipo (puede ser correo, teléfono, etc.).
     */

    public Equipo(String nombre, String contacto) throws TorneoException {
        super(nombre, contacto);
        this.listaEquipo = new ArrayList<>();
    }

    /**
     * Agrega un nuevo miembro al equipo.
     *
     * @param miembro Objeto {@link IndividuoParticipante} a añadir.
     * @throws TorneoException Si el miembro es nulo.
     */

    public void agregarMiembro(IndividuoParticipante miembro) throws TorneoException {
        if (miembro == null) {
            throw new TorneoException("El miembro del equipo no puede ser nulo.");
        }
        listaEquipo.add(miembro);
    }

    /**
     * Devuelve la lista completa de integrantes del equipo.
     *
     * @return Lista de {@link IndividuoParticipante}s.
     */

    public ArrayList<IndividuoParticipante> obtenerListaEquipo() {
        return listaEquipo;
    }

    /**
     * Retorna el puntaje del equipo.
     * Inicialmente retorna 0 por defecto; se puede sobreescribir según la lógica del torneo.
     *
     * @return Puntaje del equipo (Inicia en 0).
     */

    @Override
    public int obtenerPuntaje() {
        return 0; // Implementar lógica de puntaje si es necesario
    }
}