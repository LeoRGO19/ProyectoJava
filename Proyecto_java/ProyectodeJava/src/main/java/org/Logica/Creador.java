package org.Logica;

public abstract class Creador {
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
    public abstract Torneo crearTorneo(String nombre, String disciplina, int maxParticipantes) throws TorneoException;

}