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

    @Override
    public void configurar(String nombre, Disciplina disciplina, int maxParticipantes) {

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
    public void verEstado(){}
    public void iniciarTorneo(){}

    @Override
    public void crearBracked(FormatoTorneo tipo) {

    }

    @Override
    public void generarEnfrentamientos() {

    }

    @Override
    public String toString() {
        return "TorneoEliminacionSimple creado.";
    }

}