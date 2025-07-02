package org.Logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class TorneoEliminacionDoble implements Torneo {
    String nombre;
    LocalDate fecha;
    Calendario calendario;
    ArrayList<Participante> participantes;

    public void agregarParticipante(Participante p){}
    public void eliminarParticipante(Participante p){}
    public void crearEsquema(int tipo){}
    public void verEstadisticas(){}
    private void iniciarTorneo(){}

    @Override
    public String toString() {
        return "TorneoEliminacionDoble creado.";
    }
}