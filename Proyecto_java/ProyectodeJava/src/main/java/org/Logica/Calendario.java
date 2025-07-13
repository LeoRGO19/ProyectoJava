package org.Logica;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Calendario {

    private ArrayList<Enfrentamiento> enfrentamientos;
    private LocalDateTime fechaInicio;
    private ArrayList<Integer> indices;

    public Calendario() {
        this.enfrentamientos = new ArrayList<>();
        this.fechaInicio = LocalDateTime.now();
        this.indices = new ArrayList<>();
    }

    public void agregarEnfrentamiento(Enfrentamiento enfrentamiento, int indice) {
        enfrentamientos.add(enfrentamiento);
        indices.add(indice);
        LocalDateTime fecha = fechaInicio.plusSeconds(indice * 60L);
        enfrentamiento.establecerFecha(fecha);
    }

    public ArrayList<Enfrentamiento> obtenerEnfrentamientos() {
        return enfrentamientos;
    }

    public void mostrarCalendario() {
        System.out.println("Calendario del Torneo:");
        for (Enfrentamiento enf : enfrentamientos) {
            System.out.println(enf);
        }
    }
}
