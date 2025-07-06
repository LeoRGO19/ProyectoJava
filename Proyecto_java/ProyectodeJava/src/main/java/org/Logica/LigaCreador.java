package org.Logica;

public class LigaCreador extends Creador {
    @Override
    public Torneo crearTorneo(String nombre, String disciplina) {
        return new TorneoLiga(nombre, disciplina);
    }
}