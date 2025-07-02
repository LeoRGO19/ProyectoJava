package org.Logica;

import java.util.ArrayList;
import java.util.List;

public class TorneoClase implements Torneo{
    private int cantidadJugadores;
    private List<Participante> participantes;

    public TorneoClase(int cantidadJugadores, int tiempo) {
        this.cantidadJugadores = cantidadJugadores;
        this.participantes = new ArrayList<>();
    }

    public void agregarParticipante(Participante p) {
        if (participantes.size() < cantidadJugadores) {
            participantes.add(p);
        } else {
            System.out.println("Ya se alcanzó el máximo de jugadores.");
        }
    }

    public int getCantidadJugadores() {
        return cantidadJugadores;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }
    public void eliminarParticipante(Participante p){}
    public void crearEsquema(int tipo){}
    public void verEstadisticas(){}
    private void iniciarTorneo(){}

    @Override
    public String toString() {
        return "TorneoEliminacionSimple creado.";
    }

}