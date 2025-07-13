package org.Logica;

public class EliminacionSimpleCreador extends Creador {
    @Override
    public Torneo crearTorneo(String nombre, String disciplina, int maxParticipantes) throws TorneoException {
        validarParametros(nombre, disciplina, maxParticipantes);
        return new TorneoEliminacionSimple(nombre, disciplina, maxParticipantes);
    }
}