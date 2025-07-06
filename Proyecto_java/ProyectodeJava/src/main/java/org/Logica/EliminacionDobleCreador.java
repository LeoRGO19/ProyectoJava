package org.Logica;

public class EliminacionDobleCreador extends Creador {

    public Torneo crearTorneo(String nombre, String disciplina) {
        return new TorneoEliminacionDoble(nombre, disciplina);
    }
}
