package org.Logica;

public class EliminacionDobleCreador extends Creador {

    public Torneo crearTorneo(String nombre, Disciplina disciplina) {
        return new TorneoEliminacionDoble(nombre, disciplina);
    }
}
