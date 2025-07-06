package org.Logica;

public class EliminacionSimpleCreador extends Creador {
    @Override
    public Torneo crearTorneo(String nombre, String disciplina) {
        return new TorneoEliminacionSimple(nombre, disciplina);
    }
}
