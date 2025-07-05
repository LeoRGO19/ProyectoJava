package org.Logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class TorneoEliminacionSimple implements Torneo, SujetoTorneo {
    String nombre;
    LocalDate fecha;
    Calendario calendario;
    ArrayList<ObservadorTorneo> observadores;
    Disciplina disciplina;
    ArrayList<Participante> participantes;
    boolean torneoIniciado;

    public TorneoEliminacionSimple(String nombre, Disciplina disciplina){
        this.nombre = nombre;
        this.disciplina = disciplina;
        this.participantes = new ArrayList<>();
        this.calendario = new Calendario();
        this.observadores = new ArrayList<>();
        this.torneoIniciado = false;
    }

    @Override
    public void configurar(String nombre, Disciplina disciplina, int maxParticipantes) {

    }

    public void agregarParticipante(Participante p){
        participantes.add(p);
    }
    public void eliminarParticipante(Participante p){
        participantes.remove(p);
    }
    public void verEstado(){

    }
    public void iniciarTorneo(){
        System.out.println("Inicio torneo");
    }

    @Override
    public void crearBracked(FormatoTorneo tipo) {

    }

    @Override
    public void generarCalendario() {

    }

    @Override
    public void generarEnfrentamientos() {

    }
    @Override
    public void registrarObservador(ObservadorTorneo observador) {observadores.add(observador);}

    @Override
    public void eliminarObservador(ObservadorTorneo observador) {observadores.remove(observador);}

    @Override
    public void notificarObservadores() {
        for (ObservadorTorneo observador : observadores) {
            observador.actualizar((Torneo) this);
        }
    }
    @Override
    public String toString() {
        return "TorneoEliminacionSimple creado.";
    }
}