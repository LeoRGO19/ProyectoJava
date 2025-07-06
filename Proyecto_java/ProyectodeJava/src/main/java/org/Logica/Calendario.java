package org.Logica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Calendario {
    private ArrayList<Enfrentamiento> enfrentamientos;
    private LocalDateTime fechaInicio;

    public Calendario() {
        this.enfrentamientos = new ArrayList<>();
        this.fechaInicio = LocalDateTime.now();
    }

    public void agregarEnfrentamiento(Enfrentamiento enfrentamiento, int indice) {
        enfrentamiento.establecerFecha(fechaInicio.plusHours(indice * 2));
        enfrentamientos.add(enfrentamiento);
    }

    public ArrayList<Enfrentamiento> obtenerEnfrentamientos() {
        return enfrentamientos;
    }

    public void mostrarCalendario() {
        System.out.println("Calendario del Torneo:");
        if (enfrentamientos.isEmpty()) {
            System.out.println("No hay enfrentamientos programados.");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        for (Enfrentamiento enf : enfrentamientos) {
            String p2Nombre = enf.obtenerParticipante2() != null ? enf.obtenerParticipante2().obtenerNombre() : "BYE";
            System.out.println(enf.obtenerParticipante1().obtenerNombre() + " vs " + p2Nombre +
                    " - Programado para: " + enf.obtenerFecha().format(formatter));
        }
    }
}
