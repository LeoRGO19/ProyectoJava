package org.Logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class TorneoEliminacionSimple implements Torneo {
    String nombre;
    LocalDate fecha;
    Calendario calendario;
    int numParticipantes;
    ArrayList<Participante> participantes;

    public TorneoEliminacionSimple(int numero){
        this.numParticipantes = numero;
    }

    public int cantidadParticipantes(int numero){
        return numParticipantes;
    }
    public void agregarParticipante(Participante p){
        participantes.add(p);
    }
    public void eliminarParticipante(Participante p){
        participantes.remove(p);
    }
    public void crearEsquema(int tipo){

    }
    public void verEstadisticas(){

    }
    private void iniciarTorneo(){
        System.out.println("Inicio torneo");
    }

    @Override
    public String toString() {
        return "TorneoEliminacionSimple creado.";
    }
}