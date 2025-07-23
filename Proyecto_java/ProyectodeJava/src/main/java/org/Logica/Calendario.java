package org.Logica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Representa un calendario de enfrentamientos en un torneo.
 * Cada enfrentamiento es agendado en función de un índice de turno y una fecha de inicio base.
 */
public class Calendario {

    /** Lista de enfrentamientos programados en el calendario. */
    private ArrayList<Enfrentamiento> enfrentamientos;

    /** Fecha y hora de inicio del calendario. */
    private LocalDateTime fechaInicio;

    /** Lista de índices que representan el orden o turno de los enfrentamientos. */
    private ArrayList<Integer> indices;

    /**
     * Crea un nuevo calendario con la hora actual como fecha de inicio.
     * Inicializa las listas de enfrentamientos e índices.
     */
    public Calendario() {
        this.enfrentamientos = new ArrayList<>();
        this.fechaInicio = LocalDateTime.now();
        this.indices = new ArrayList<>();
    }
    /**
     * Retorna la fecha inicial.
     *
     * @return la variable fechaInicio.
     */
    public LocalDateTime obtenerFechaInicio(){
        return fechaInicio;
    }
    /**
     * Retorna la lista de indices guardada.
     *
     * @return la lista indices.
     */
    public ArrayList<Integer> obtenerIndices(){
        return indices;
    }

    /**
     * Agrega un enfrentamiento al calendario con un índice determinado.
     * La fecha del enfrentamiento se calcula como {@code fechaInicio + índice * 60 segundos}.
     *
     * @param enfrentamiento El enfrentamiento a agregar.
     * @param indice El índice de turno del enfrentamiento.
     */
    public void agregarEnfrentamiento(Enfrentamiento enfrentamiento, int indice) {
        enfrentamientos.add(enfrentamiento);
        indices.add(indice);
        LocalDateTime fecha = fechaInicio.plusSeconds(indice * 60L);
        enfrentamiento.establecerFecha(fecha);
    }

    /**
     * Retorna la lista de enfrentamientos registrados en el calendario.
     *
     * @return Una lista de objetos {@link Enfrentamiento}.
     */
    public ArrayList<Enfrentamiento> obtenerEnfrentamientos() {
        return enfrentamientos;
    }

    /**
     * Muestra por consola los enfrentamientos programados en el calendario,
     * incluyendo sus fechas estimadas.
     */
    public void mostrarCalendario() {
        System.out.println("Calendario del Torneo:");
        for (Enfrentamiento enf : enfrentamientos) {
            System.out.println(enf);
        }
    }
}
