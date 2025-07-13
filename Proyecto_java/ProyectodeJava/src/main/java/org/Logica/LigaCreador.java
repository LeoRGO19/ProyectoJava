package org.Logica;

public class LigaCreador extends Creador {
    @Override
    public Torneo crearTorneo(String nombre, String disciplina, int maxParticipantes) throws TorneoException {
        validarParametros(nombre, disciplina, maxParticipantes);
        return new TorneoLiga(nombre, disciplina, maxParticipantes);
    }
}